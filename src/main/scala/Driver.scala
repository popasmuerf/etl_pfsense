

import scala.util.matching.Regex
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import scala.io.Source
/**
  * Created by mdb on 7/12/17.
  */

object Driver{
  var lineNumber = 1
  val recordtest:String = "Jan 11 07:28:30 141.102.4.254 pf: 000145 rule 141/0(match): block in on bge0: (tos 0x0, ttl 128, id 58078, offset 0, flags [none], proto UDP (17), length 1052) 141.102.12.99.1137 > 188.40.123.111.24460: UDP, length 1024"
  val recordtest1:String = "Jul 13 17:38:20 pfSense filterlog: 5,16777216,,1000000103,xn0,match,block,in,4,0x0,,110,5032,0,none,17,udp,131,187.123.4.107,10.0.0.56,47507,45050,111"
  val recordtest2:String = "Jul 13 17:40:42 pfSense filterlog: 7,16777216,,1000000105,ovpns1,match,block,in,6,0x00,0xaf8ff,1,UDP,17,48,fd6f:826b:ed1e::1000,ff02::fb,5353,5353,48"
  val filePath = "/Users/mdb/data/logs/pfsense/pfsenselogs.txt"

  def main(args:Array[String]): Unit = {
    println("Thread: Main")

    for (line <- Source.fromFile(filePath).getLines) {
      parseRecord(line)
      lineNumber = lineNumber + 1
    }
    //println(line)
  }
  def procRecord(record:String): Unit ={}
  def parseRecord(record:String):Unit= {
    /*
    val dayPttrn = "(^\\w{3}\\s{1}\\d{1,2})".r  //day pattern
    val timePttrn = "\\d{2}:\\d{2}:\\d{2}".r  //time pattern
    val dateTimePttrn = "\\w{3}\\s{1}\\d{1,2}\\s\\d{2}:\\d{2}:\\d{2}".r  //nix -- DayTime
    val ipAddrPttrn = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}".r  //ipAddr
    val socketPttrn = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.(6553[0-5]|655[0-2][0-9]\\d|65[0-4](\\d){2}|6[0-4](\\d){3}|[1-5](\\d){4}|[1-9](\\d){0,3})".r
    val actionPttrn = "pass|block".r
    val protocolPttrn = "TCP|UDP|IGMP|ICMP".r
    */

    val dayPttrn = "(^\\w{3}\\s{1}\\d{1,2})".r //day pattern
    val timePttrn = "\\d{2}:\\d{2}:\\d{2}".r //time pattern
    val dateTimePttrn = "\\w{3}\\s{1}\\d{1,2}\\s\\d{2}:\\d{2}:\\d{2}".r //nix -- DayTime
    val ipAddrPttrn = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}".r //ipAddr
    val portPttrn = ",(6553[0-5]|655[0-2][0-9]\\d|65[0-4](\\d){2}|6[0-4](\\d){3}|[1-5](\\d){4}|[1-9](\\d){0,3}){2}".r
    val actionPttrn = "pass|block".r
    val protocolPttrn = "tcp|udp|igmp|icmp".r

    //    println(dayPttrn.findFirstIn(record).get)
    val day: Option[String] = dayPttrn.findFirstIn(record)
    // println(timePttrn.findFirstIn(record).get)
    val time: Option[String] = timePttrn.findFirstIn(record)
    // println(dateTimePttrn.findFirstIn(record).get)
    val dateTime: Option[String] = dateTimePttrn.findFirstIn(record)
    //ipAddrPttrn.findAllIn(record)).foreach(println _)
    var ipAddress = Option(ipAddrPttrn.findAllIn(record).toList)
    //portPttrn.findAllIn(record).foreach(println)
    val port: Option[List[String]] = Option(portPttrn.findAllIn(record).toList)
    //println(actionPttrn.findFirstIn(record).get)
    val action: Option[String] = actionPttrn.findFirstIn(record)
    //println(protocolPttrn.findFirstIn(record).get)
    val protocol: Option[String] = protocolPttrn.findFirstIn(record)
    val recordStr = buildRecordStr(day, time, dateTime, ipAddress, port, action, protocol)
    if (recordStr != None)
      println(s"line:${lineNumber}" + " " + recordStr.get)
    else
      println(s"Record at line:${lineNumber} does not conform to known format")
  }
  def buildRecordStr(day:Option[String],time:Option[String],dateTime:Option[String],ipAddress:Option[List[String]],port:Option[List[String]],action:Option[String],protocol:Option[String]): Option[String] = {
    var recordStr:String = ""
    day match{
      case None => {return None}
      case _ => recordStr = day.get
    }
    time match{
      case None => {return None}
      case _ => {recordStr += " " ; recordStr += time.get}
    }
    dateTime match{
      case None => {return None}
      case _ => {recordStr += " " ; recordStr += dateTime.get}
    }
    ipAddress match{
      case None => {return None}
      case _ => { val ipList = ipAddress.get
        if(ipList.length < 2 ){return None}
        recordStr += " "
        recordStr += ipList(0)
        recordStr += " "
        recordStr += ipList(1)
      }
    }
    port match{
      case None => {return None}
      case _ => {
        val portList = port.get
        recordStr += " "
        recordStr += portList(portList.length - 3 )
        recordStr += " "
        recordStr += portList(portList.length - 2 )
      }
    }
    action match{
      case None => {return None}
      case _ => {recordStr += " " ; recordStr += action.get}
    }
    protocol match{
      case None => {return None}
      case _ => {recordStr += " " ; recordStr += protocol.get}
    }

    return Option(recordStr)
  }
  def writeToSwimeLane(): Unit ={}
}