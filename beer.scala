import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}
import sqlContext.implicits
import scala.io.Source


object beer {

  def main(args: Array[String]): Unit = {


    val source = Source.fromFile("/home/hadoop/Downloads/beer.reviews.csv")

    val source2 = Source.fromFile("/home/hadoop/Downloads/beer.json")

  val sqlContext = SQLContext(sparkcontext)
    df = sqlContext.read.load("csv_file",
      format = '/home/hadoop/Downloads/beer.reviews.csv',
      header='true',
      inferSchema='true').select("text")