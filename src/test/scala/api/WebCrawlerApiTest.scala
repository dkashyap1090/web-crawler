package api


import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.util.ByteString
import akka.http.scaladsl.model._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class WebCrawlerApiTest extends AnyWordSpec with Matchers with ScalatestRouteTest {
  val webCrawlerApi = new WebCrawlerApi{}
  "WebCrawlerApi routes" should {
    "Get response form urls" in {

      val jsonRequest = ByteString(
        s"""
           |{
           |    "urls":["https://google.com"]
           |}
        """.stripMargin)

      val postRequest = HttpRequest(
        HttpMethods.POST,
        uri = "/api/crawler",
        entity = HttpEntity(MediaTypes.`application/json`, jsonRequest))


      postRequest ~> webCrawlerApi.routes ~> check {
        status.isSuccess() shouldEqual true
      }
    }

  }
}