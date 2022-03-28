package util

import com.typesafe.config.{Config, ConfigFactory}

object ConfigUtility {
  lazy val config: Config = ConfigFactory.load()
  def getStringConf(path: String): String = config.getString(path)
  def getIntConf(path: String): Int = config.getInt(path)
}
