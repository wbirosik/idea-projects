package twitter.stream
//import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils}
import com.datastax.spark.connector.SomeColumns
import com.datastax.spark.connector.streaming._
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.sql.SparkSession
//import org.apache.spark.sql.SparkSession
//import org.apache.spark.sql.SparkSession
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming.{Duration, Seconds, StreamingContext, Time}
import org.apache.spark.{SparkConf, SparkContext}
import org.joda.time.{DateTime, DateTimeZone}
import twitter.stream.StreamingSchema.{KEYSPACE, TABLE}
import twitter4j.Status
import twitter4j.auth.OAuthAuthorization
import twitter4j.conf.ConfigurationBuilder

object TwitterStreaming {

  /*
   * Pass in Twitter properties as -D system properties:
   * -Dtwitter4j.oauth.consumerKey="value"
   * -Dtwitter4j.oauth.consumerSecret="value"
   * -Dtwitter4j.oauth.accessToken="value"
   * -Dtwitter4j.oauth.accessTokenSecret="value"
   */

  val StreamingBatchInterval = 5

  val Keywords = Seq("love","hate",":-)",":)",":-(",":(")

  def main (args: Array[String]): Unit = {

    val authorization: OAuthAuthorization = buildTwitterAuthorization()


    val conf = new SparkConf(true).set("spark.cassandra.connection.host", "127.0.0.1")
      .setAppName("Bill")
      .setMaster("local[*]")
      .set("spark.cassandra.connection.host", "localhost")
    val sc = new SparkContext(conf)





   // val conf = new SparkConf(true)

    //  .setSparkHome("/var/lib/spark")

     // .set("spark.cassandra.connection.host", "<cassandra-seed-ip>")

     // .setJars(Seq("/var/lib/spark/spark-cassandra-connector/spark-cassandra-connector-assembly-1.6.0-M1.jar"))

   // val sc = new SparkContext("spark://<spark-master-ip>:9042", "test", conf)
   // val conf = new SparkConf()
   //   .setAppName("Bill")
    //  .setMaster("local[^]")
   //   .set("spark.cassandra.connection.host", "localhost")
   // StreamingSchema.prepareSchemaAndCleanData(conf)
   // val ssc = new StreamingContext(conf, Seconds(5))
   // val topics = List("Bill").toSet // You have two topics; "israel" and "manny"

    val kafkaParams = Map( //Parameters to connect to Kafka
      "bootstrap.servers" -> "localhost:9092", //This's Al's IP. if you want local; "bootstrap.servers" -> "localhost:9092",
     "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "spark-streaming-something",
      "auto.offset.reset" -> "earliest")

   // val line = KafkaUtils.createDirectStream[String, String](
     // Parameter for Streaming Context
     // ssc,
     // PreferConsistent,
  //  ConsumerStrategies.Subscribe[String, String](topics, kafkaParams)
    //)



    val batchDuration: Duration = Seconds(StreamingBatchInterval)
    val ssc = new StreamingContext(sc, batchDuration)


    val stream: ReceiverInputDStream[Status] = TwitterUtils.createStream(ssc, Some(authorization), Nil, StorageLevel.MEMORY_ONLY_SER_2)

   // stream.flatMap(_.getText.toLowerCase.split("""\s+"""))
     // .filter(Keywords.contains(_))
     // .countByValueAndWindow(batchDuration, batchDuration)
     // .transform((rdd, time) => rdd.map { case (keyword, count) => (replaceSmiley(keyword), count, now(time))})
     // .saveToCassandra(KEYSPACE, TABLE, SomeColumns("keyword", "count", "interval"))





    // Create Spark Streaming Context
    val spark = SparkSession.builder.appName("Cassandra").getOrCreate()
    val sc = spark.sparkContext
    // Twitter App API Credentials - underlying twitter4j Library
    System.setProperty("twitter4j.oauth.consumerKey", "HEPneO1YX0o4oiKD8PQt1BCwv")
    System.setProperty("twitter4j.oauth.consumerSecret", "RCEshgBhhjsSFWT4xiS2tqsbrYgRcthOJHwcx1Gcji05WykSMs")
    System.setProperty("twitter4j.oauth.accessToken", "1172222860980625418-8ymnbJtJLFweKLl3Pi4gqlEpXJ9c40")
    System.setProperty("twitter4j.oauth.accessTokenSecret", "OWMEAAZcEvItDhSfuxIdFMN06jjYJbbjf9uCFF2s5EmlY")
    //val filters = Seq("iphone", "samsung galaxy,samsung note")
    //   val twitterStream = TwitterUtils.createStream(ssc,None,filters)
    val twitterStream = TwitterUtils.createStream(ssc, None)
    val englishTweets = twitterStream.filter(_.getLang == "en")
    englishTweets.map(_.getText)
     stream.flatMap(_.getText.toLowerCase.split("""\s+"""))
      .filter(Keywords.contains(_))
      .transform((rdd, time) => rdd.map { case (keyword, count) => (replaceSmiley(keyword), count, now(time))})
      .saveToCassandra(KEYSPACE, TABLE, SomeColumns("keyword", "count", "interval"))


   // ssc.checkpoint("/tmp/checkpoint")
    ssc.start()
    ssc.awaitTermination()



  }


  private def replaceSmiley(possibleSmiley: String): String = {
    possibleSmiley match {
      case ":)" => "joy"
      case ":-)" => "joy"
      case ":(" => "sadness"
      case ":-(" => "sadness"
      case whatever => whatever
    }
  }


  private def now(time: Time): String =
    new DateTime(time.milliseconds, DateTimeZone.UTC).toString("yyyy-MM-dd HH:mm:ss")

  private def buildTwitterAuthorization(): OAuthAuthorization = {
    val consumerKey: String = sys.env.getOrElse("TWITTER_CONSUMER_KEY", sys.props("twitter4j.oauth.consumerKey"))
    val consumerSecret: String = sys.env.getOrElse("TWITTER_CONSUMER_SECRET", sys.props("twitter4j.oauth.consumerSecret"))

    val accessToken: String = sys.env.getOrElse("TWITTER_ACCESS_TOKEN", sys.props("twitter4j.oauth.accessToken"))

    val accessTokenSecret: String = sys.env.getOrElse("TWITTER_ACCESS_TOKEN_SECRET", sys.props("twitter4j.oauth.accessTokenSecret"))

    val authorization: OAuthAuthorization = new OAuthAuthorization(new ConfigurationBuilder()
      .setOAuthConsumerKey(consumerKey)
      .setOAuthConsumerSecret(consumerSecret)
      .setOAuthAccessToken(accessToken)
      .setOAuthAccessTokenSecret(accessTokenSecret)
      .build)
    authorization
  }
}
