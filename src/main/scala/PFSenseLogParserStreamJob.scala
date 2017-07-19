//import logs.ApacheAccessLog
import com.typesafe.config
import com.typesafe.config._
import org.apache.spark._
import org.apache.spark.streaming._
import spark.jobserver.{SparkJob, SparkJobValid, SparkJobValidation, SparkStreamingJob}

/**
  * Created by mdb on 7/19/17.
  */
object PFSenseLogParserStreamJob extends SparkStreamingJob{
  override def runJob(sc: StreamingContext, jobConfig: com.typesafe.config.Config): Any = ???

  override def validate(sc: StreamingContext, config: com.typesafe.config.Config): SparkJobValidation = ???
}
