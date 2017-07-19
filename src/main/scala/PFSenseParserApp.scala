import com.typesafe.config.{Config, ConfigFactory}
import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.streaming.dstream._
import scala.util.Try
/**
  * Created by mdb on 7/19/17.
  * https://bitbucket.org/fractalindustries/spark-service-examples/src/26e21f2ef776e4102c34d816e88dfe0e229da632/src/main/?at=master
  */
object PFSenseParserApp {
  /*
  val defaultParams: Config = ConfigTest( 10000,  10000,  "/tmp/logs",
  "/tmp/checkpoint",
 "/tmp/log_stats.html",
  "/tmp/outpandas",
  "./src/main/resources/index.html.template")
  */
  def main(args: List[String]): Unit = {
    // val opts: Config = defaultParams
    val conf: SparkConf = new SparkConf()
    conf.setAppName("PFSenseParserApp")
    conf.setMaster("local[*]")

  }
}
  /*}
  case class ConfigTest(windowLength: Int = 10000, slideInterval: Int = 10000, logsDirectory: String = "/tmp/logs",
                    checkpointDirectory: String = "/tmp/checkpoint",
                    outputHTMLFile: String = "/tmp/log_stats.html",
                    outputDirectory: String = "/tmp/outpandas",
                    indexHTMLTemplate :String ="./src/main/resources/index.html.template"){
  }
  def getWindowDuration()={
    //return new Durantion(windowLength)
  }
  def getSlideDuration() ={
    //return new Duration(SlideInterval)
  }

}*/
