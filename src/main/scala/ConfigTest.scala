/**
  * Created by mdb on 7/19/17.
  */
case class ConfigTest(windowLength: Int = 10000, slideInterval: Int = 10000, logsDirectory: String = "/tmp/logs",
                      checkpointDirectory: String = "/tmp/checkpoint",
                      outputHTMLFile: String = "/tmp/log_stats.html",
                      outputDirectory: String = "/tmp/outpandas",
                      indexHTMLTemplate :String ="./src/main/resources/index.html.template")


