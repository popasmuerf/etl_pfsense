

import scala.util.matching.Regex
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.Duration
import org.apache.spark.streaming.Seconds

import scala.io.Source
/**
  * Created by mdb on 7/12/17.
  */

object Driver{
  var lineNumber = 1
  val recordtest:String = "Jan 11 07:28:30 141.102.4.254 pf: 000145 rule 141/0(match): block in on bge0: (tos 0x0, ttl 128, id 58078, offset 0, flags [none], proto UDP (17), length 1052) 141.102.12.99.1137 > 188.40.123.111.24460: UDP, length 1024"
  val recordtest1:String = "Jul 13 17:38:20 pfSense filterlog: 5,16777216,,1000000103,xn0,match,block,in,4,0x0,,110,5032,0,none,17,udp,131,187.123.4.107,10.0.0.56,47507,45050,111"
  val recordtest2:String = "Jul 13 17:40:42 pfSense filterlog: 7,16777216,,1000000105,ovpns1,match,block,in,6,0x00,0xaf8ff,1,UDP,17,48,fd6f:826b:ed1e::1000,ff02::fb,5353,5353,48"

  val filePath = "/home/mikeyb/data/text_files/logs/pfsense/pfsenselogs.txt"
  val filePathPass = "/home/mikeyb/data/text_files/logs/pfsense/pfsenselogs_pass.txt"
  val  syslogPath = "/var/log/syslog"
  //val filePath = "/Users/mdb/data/logs/pfsense/pfsenselogs.txt"

  def main(args:Array[String]): Unit = {
    val sconf = new SparkConf().setAppName("streaming-app").setMaster("local[*]")
    val ssc = new StreamingContext(sconf,Seconds(1))
    val lines = ssc.textFileStream(syslogPath)
    val errorLines: DStream[String] = lines.filter(_.contains("error"))
    errorLines.print()
    ssc.start()
    ssc.awaitTermination()

  }
}