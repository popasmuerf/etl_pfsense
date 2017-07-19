import com.sun.org.apache.bcel.internal.classfile.LineNumber

import scala.util.matching.Regex
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

import scala.io.Source
/**
  * Created by mdb on 7/12/17.
  */

object Main{
  var lineNumber = 1
  val filePath = "/Users/mdb/data/logs/pfsense/pfsenselogs.txt"

  def main(args:Array[String]): Unit = {
    println("Thread: Main")
    val sparkMaster = "local[*]"
    val sparkAppName = "etl_pfsense test driver"
    val sparkConf = new SparkConf()
    sparkConf.setAppName(sparkAppName)
    sparkConf.setMaster(sparkMaster)
    val sc = new SparkContext(sparkConf)
    val logFile = sc.textFile(filePath)
    val processedLog = logFile.map(x => PFsenseParser.parseRecord(x))
    val collectedProcessedLog: Array[Option[String]] = processedLog.collect()
    if(collectedProcessedLog.length > 0){
        for(elem <- collectedProcessedLog){
          println(elem.toString)
        }
    }else{
      println("list empty")
    }
    //for (elem <- collectedLogFile) {println(elem)}
  }
}