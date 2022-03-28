package json

import model.{Urls, Results, Result}
import spray.json.DefaultJsonProtocol

object JsonHelper {
  // import the default encoders for primitive types (Int, String, Lists etc)

  import DefaultJsonProtocol._

  implicit val inputUrlsJsonFormat = jsonFormat1(Urls)
  implicit val resultJsonFormat = jsonFormat2(Result)
  implicit val responseJsonFormat = jsonFormat2(Results)

}
