import org.apache.spark._
import org.apache.spark.streaming._
import com.datastax.spark.connector.streaming._
import org.apache.spark.rdd._

import scala.collection.mutable.Queue

object UserVisitStats {

  case class Cdr(sourceIP: String, destURL: String, visitDate: String,
      adRevenue: Float, userAgent: String, countryCode: String,
      languageCode: String, searchWord: String, duration: Int)

  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("top_10_popular_countries_from_user_visits")
      .set("spark.cassandra.connection.host", "127.0.0.1")

    val ssc = new StreamingContext(conf, Seconds(10))

    val rdd = ssc.cassandraTable("user_stats", "user_visits") //.select("countrycode")

    //SQLContext is droped after 2.0
    val sqlC = new SparkContext(conf)
    var cdrs =
//    val cdrs = sqlC.createDataFrame(seqToCdr(rdd))
    cdrs.select("countrycode").groupBy("countrycode").sum("countrycode").orderBy(desc("SUM(countrycode)"))

    //rdd.collect().foreach(println)

    val rddQueue = new Queue[RDD[com.datastax.spark.connector.CassandraRow]]()


    val dstream = ssc.queueStream(rddQueue)

    dstream.print()

    ssc.start()
    cdrs.collect().foreach(println)
    rddQueue += cdrs
    ssc.awaitTermination()
  }

  def seqToCdr(rdd: RDD[Array[String]]): RDD[Cdr] = {
    rdd.map(c => c.map {
      case x if x.isEmpty => "0"
      case x => x
    }).map(c => Cdr(c(0).toString, c(1).toString, c(2).toString,
      c(3).toFloat, c(4).toString, c(5).toString,
      c(6).toString, c(7).toString, c(8).toInt))
  }
}