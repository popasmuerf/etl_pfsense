import com.sun.org.apache.bcel.internal.classfile.LineNumber

import scala.util.matching.Regex
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.streaming.dstream._



import scala.io.Source
/**
  * Created by mdb on 7/12/17.
  */

object Main{
  /*
  var lineNumber = 1
  val filePath = "/Users/mdb/data/logs/pfsense/pfsenselogs.txt"
  val filePathPass = "/Users/mdb/data/logs/pfsense/pfsenselogs_pass.txt"
  def main(args:Array[String]): Unit = {
    println("Thread: Main")
    val sparkMaster = "local[*]"
    val sparkAppName = "etl_pfsense test driver"
    val sparkConf = new SparkConf()
    sparkConf.setAppName(sparkAppName)
    sparkConf.setMaster(sparkMaster)
    val sc = new SparkContext(sparkConf)
    val logFile = sc.textFile(filePathPass)
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
  */

    def main(args:Array[String]): Unit ={
      val sparkMaster = "local[*]"
      val sparkAppName = "etl_pfsense test driver"
      val opts = new Config(1000,
      1000,
      "/tmp/logs",
      "/tmp/checkpoint",
      "/tmp/log_stats.html",
      "/tmp/outPF",
      "./src/main/resources/index.html.template")

      val conf = new SparkConf()
      conf.setAppName(sparkAppName)
      conf.setMaster(sparkMaster)
      //val ssc = new StreamingContext(conf,)


    }

}