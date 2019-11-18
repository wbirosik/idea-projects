import org.apache.spark
import org.apache.spark.{ SparkConf, SparkContext }
import org.apache.spark.sql.functions.broadcast
import org.apache.spark.sql.types._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import sqlContext.implicits._
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.DataFrame
object sqlContext {


  def load(args: Array[String]): Unit = {
    val spark = org.apache.spark.sql.SparkSession.builder
      .master(master ="local")
      .appName(name = "Spark CSV Reader")
      .getOrCreate;
    val df = sqlContext.load(args = "hdfs:///csv/file/dir/file.csv" : String)
    val s = spark.read.option("multiLine", "true").json("/tmp/data.json")

  }
}