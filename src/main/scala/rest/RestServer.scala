package rest

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import api.WebCrawlerApi
import logger.Logging
import util.ConfigUtility

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

object RestServer extends App with Logging{
  def startApplication(): Unit = {
    implicit val actorSystem: ActorSystem = ActorSystem()
    implicit val executor: ExecutionContext      = actorSystem.dispatcher

    val httpRoute = new WebCrawlerApi {}

    val host = ConfigUtility.getStringConf("http.host")
    val port = ConfigUtility.getIntConf("http.port")

    val futureBinding = Http().newServerAt(host, port).bindFlow(httpRoute.routes)
      futureBinding.onComplete {
          case Success(binding) =>
              val address = binding.localAddress
            info(s"Server online at : http://${address.getHostString}:${address.getPort}/")
          case Failure(ex) =>
              error("Failed to bind HTTP endpoint, terminating system", ex)
              actorSystem.terminate()
      }
  }

  startApplication()

}
