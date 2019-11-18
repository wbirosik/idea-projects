import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object KafkaConsumer1 {



  def main(args: Array[String]): Unit = {

    //We need batch processing configuration
    val conf = new SparkConf().setAppName("Bill").setMaster("local[*]")
    val ssc = new StreamingContext(conf, Seconds(5))
    val topics = List("Bill").toSet // You have two topics; "israel" and "manny"



    val kafkaParams = Map( //Parameters to connect to Kafka
      "bootstrap.servers" -> "localhost:9092", //This's Al's IP. if you want local; "bootstrap.servers" -> "localhost:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "spark-streaming-something",
      "auto.offset.reset" -> "earliest"
    )

    val line = KafkaUtils.createDirectStream[String,String](
      //Parameter for Streaming Context
      ssc,
      PreferConsistent,
      ConsumerStrategies.Subscribe[String,String](topics, kafkaParams)
    )

    val tmp = line.map(record => record.value().toString)
    val _line = tmp.flatMap(_.split(",")



    //val sent = SentimentAnalyzer
    //how to filter?
    //val fuc = _line.filter
    //val fuc=_line.filter( bw => bw.contains("fuck"))
   // println(sent)

    //fuc.print
     _line.print
    ssc.start()
    ssc.awaitTermination








  }



}

