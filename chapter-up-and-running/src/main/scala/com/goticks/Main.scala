package com.goticks


import scala.concurrent.Future

import akka.actor.{ ActorSystem , Actor, Props }
import akka.event.Logging
import akka.util.Timeout

import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import com.typesafe.config.{ Config, ConfigFactory }
import scala.util.{ Failure, Success }




object Main extends App
    with RequestTimeout {

  val config = ConfigFactory.load() 
  val host = config.getString("http.host") // Gets the host and a port from the configuration
  val port = config.getInt("http.port")

  implicit val system = ActorSystem() 
  implicit val ec = system.dispatcher  //bindAndHandle requires an implicit ExecutionContext

  val api = new RestApi(system, requestTimeout(config)).routes // the RestApi provides a Route

  implicit val materializer = ActorMaterializer()
  val bindingFuture: Future[ServerBinding] =
    Http().bindAndHandle(api, host, port) //Starts the HTTP server
 
  val log =  Logging(system.eventStream, "go-ticks")
  bindingFuture.map { serverBinding =>
    log.info(s"RestApi bound to ${serverBinding.localAddress} ")
  }.onComplete {
    case Success(_) =>
      log.info("Success to bind to {}:{}", host, port)
    case Failure(ex) =>
      log.error(ex, "Failed to bind to {}:{}!", host, port)
      system.terminate()
  }
}




trait RequestTimeout {
  import scala.concurrent.duration._
  def requestTimeout(config: Config): Timeout = {
    val t = config.getString("akka.http.server.request-timeout")
    val d = Duration(t)
    FiniteDuration(d.length, d.unit)
  }
}


