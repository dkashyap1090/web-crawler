package api

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import json.JsonHelper._
import logger.Logging
import model.{Result, Results, Urls}
import org.jsoup.Jsoup
trait WebCrawlerApi {

  val routes: Route = crawlRouter


  private def homeRoute: Route = pathSingleSlash {
    complete("Welcome to web crawler  api!!")
  }

  def crawlRouter: Route = pathPrefix("api" / "crawler") {
    pathEnd {
      post {
        entity(as[Urls]) { urls =>
          val response= getResponse(urls)
          complete(Results(response.map(_._1),response.map(_._2).mkString(",")))
        }
      }
    }
  }

  def getResponse(urls: Urls): List[(Result, String)] ={
    for {
      url <- urls.urls
      response = Jsoup.connect(url).followRedirects(false).execute()
      doc = Jsoup.connect(url).get()
      (data,error) = if(response.statusCode() == 200) (doc, "") else ("",doc)
    } yield {
      (Result(s"""$url""", s"""$data"""), s"""$error""")
    }
  }

  def apiRoute: Route = homeRoute ~ crawlRouter
}
