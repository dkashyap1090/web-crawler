package model

case class Result(url:String, data:String)

case class Results(results:List[Result], error: String)
