import java.time.Duration

/**
  * Created by mdb on 7/28/17.
  */
class Config(Window:Int = 1000,
             slideInterval:Int = 1000,
             logsDir:String,
             checkPointDir:String="/tmp/checkpoint",
             outPutHTML:String="/tmp/log_stats.html",
             outPutDirectory:String="/tmp/outPF",
             indexHTMLTemplate:String="./src/main/resources/index.html.template") {

  def getWindowDuration(): Unit ={
    new Duration(Window)
  }

  def getSlideDuration(): Unit ={
    new Duration((slideInterval))
  }


}
