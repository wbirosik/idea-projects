import.org.apache.kafka.common.serialization.StringDeserializer
import.org.apache.spark._
import.org.apache.spark.streaming.{Seconds, StreamingContext}
import.org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import.org.apache.spark.streaming.kafka010.{ConsumerStrategies}


object KafkaConsumer {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("Twitter").setMaster("local[*]")

    val ssc = new StreamingContext(conf, Seconds(5))

    val topics = List("Bill").toSet

    val setParams = Map(
      "bootstrap.servers" -> "localhost:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "spark-streaming-something",
      "auto.offsset.reset" -> "earliest")

    val line = KafkaUtils.createDirectStream[String,String](
      ssc,
      PreferConsistent,
      ConsumerStrategies.Subscribe[String,String](topics, kafkaParams)
    )

    val temp = line.map(record => record.value().toString)
    val _line = temp.flatMap(_.split(regex = ",))
    val fuc = = _line.filter(bq => bq.contains("fuck"))
    temp.print
      _line.print


  }
}

