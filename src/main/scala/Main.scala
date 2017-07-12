import scala.util.matching.Regex

/**
  * Created by mdb on 7/12/17.
  */

object Main{

  val record:String = "Jan 11 07:28:30 141.102.4.254 pf: 000145 rule 141/0(match): block in on bge0: (tos 0x0, ttl 128, id 58078, offset 0, flags [none], proto UDP (17), length 1052) 141.102.12.99.1137 > 188.40.123.111.24460: UDP, length 1024"


  def main(args:Array[String]): Unit ={
    println("Thread: Main")
    parseRecord
  }
  def parseRecord:Unit={
    val dayPttrn = "(^\\w{3}\\s{1}\\d{1,2})".r
    val timePttrn = "\\d{2}:\\d{2}:\\d{2}".r
    val dateTimePttrn = "\\w{3}\\s{1}\\d{1,2}\\s\\d{2}:\\d{2}:\\d{2}".r
    val ipAddrPttrn = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}".r
    val socketPttrn = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.(6553[0-5]|655[0-2][0-9]\\d|65[0-4](\\d){2}|6[0-4](\\d){3}|[1-5](\\d){4}|[1-9](\\d){0,3})".r
    val actionPttrn = "pass|block".r
    val protocolPttrn = "TCP|UDP|IGMP|ICMP".r


    println(dayPttrn.findFirstIn(record).get)
    val day: Option[String] = Option(dayPttrn.findFirstIn(record).get)
    println(timePttrn.findFirstIn(record).get)
    val time:Option[String] = Option(timePttrn.findFirstIn(record).get)
    println(dateTimePttrn.findFirstIn(record).get)
    val dateTime: Option[String] = Option(dateTimePttrn.findFirstIn(record).get)
    println(ipAddrPttrn.findFirstIn(record).get)
    val ipAddress: Option[String] = Option(ipAddrPttrn.findFirstIn(record).get)
    socketPttrn.findAllIn(record).foreach(println)
    val socket: Option[List[String]] = Option(socketPttrn.findAllIn(record).toList)
    println(actionPttrn.findFirstIn(record).get)
    val action:Option[String] = Option(actionPttrn.findFirstIn(record).get)
    println(protocolPttrn.findFirstIn(record).get)
    val protocol: Option[String] = Option(protocolPttrn.findFirstIn(record).get)
    val recordStr = buildRecordStr(day,time,dateTime,ipAddress,socket,action,protocol)
    if(recordStr != None)
      println(recordStr.get)
    else
      println("Error!!!! mangled record!!!!")

    }
   def buildRecordStr(day:Option[String],time:Option[String],dateTime:Option[String],ipAddress:Option[String],socket:Option[List[String]],action:Option[String],protocol:Option[String]): Option[String] = {
     var recordStr:String = ""
     day match{
       case None => {//println("Error") ;
                      return None
                    }
       case _ => recordStr = day.get
     }
     time match{
       case None => {//println("Error") ;
                      return None
                    }
       case _ => {recordStr += " " ; recordStr += time.get}
     }
     dateTime match{
       case None => {//println("Error") ;
                      return None
                    }
       case _ => {recordStr += " " ; recordStr += dateTime.get}
     }
     ipAddress match{
       case None => {//println("Error") ;
                      return None
                    }
       case _ => {recordStr += " " ; recordStr += ipAddress.get}
     }
     socket match{
       case None => {//println("Error") ;
                      return None
                    }
       case _ => {recordStr += " " ; val socketList = socket.get ; recordStr += socketList(0) ; recordStr += " "  ;recordStr += socketList(1) }
     }
     action match{
       case None => {//println("Error") ;
                      return None
                    }
       case _ => {recordStr += " " ; recordStr += action.get}
     }
     protocol match{
       case None => {//println("Error") ;
                      return None
                    }
       case _ => {recordStr += " " ; recordStr += protocol.get}
     }

     return Option(recordStr)
   }
  def writeToSwimeLane(): Unit ={}
}