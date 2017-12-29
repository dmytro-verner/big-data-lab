package edu.university.bigdata.model;

import com.datastax.spark.connector.japi.rdd.CassandraTableScanJavaRDD;
import edu.university.bigdata.model.model.UserVisit;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static com.datastax.spark.connector.japi.CassandraJavaUtil.javaFunctions;
import static com.datastax.spark.connector.japi.CassandraJavaUtil.mapRowTo;

public class UserVisitsStatSparkJob {

    public static void main(String[] args) {
        SparkSession sparkSession = initSparkSession();
        Dataset<Row> dataFrame = getDataFrame(sparkSession);
        Dataset<Row> topTenPopularCountries = getTopTenPopularCountries(sparkSession, dataFrame);

        topTenPopularCountries.show();
    }

    private static Dataset<Row> getTopTenPopularCountries(SparkSession sparkSession, Dataset<Row> dataFrame) {
        String query = "SELECT countrycode AS country, COUNT(sourceip) AS visits " +
                "FROM user_visits " +
                "GROUP BY countrycode " +
                "ORDER BY visits DESC";

        dataFrame.createOrReplaceTempView("user_visits");
        return sparkSession.sql(query).limit(10);
    }

    private static Dataset<Row> getDataFrame(SparkSession sparkSession) {
        CassandraTableScanJavaRDD<UserVisit> rdd = javaFunctions(sparkSession.sparkContext())
                .cassandraTable("user_stats", "user_visits", mapRowTo(UserVisit.class));
        return sparkSession.createDataFrame(rdd.rdd(), UserVisit.class);
    }

    private static SparkSession initSparkSession() {
        SparkConf conf = new SparkConf(true)
                .set("spark.cassandra.connection.host", "localhost")
                .setAppName("Reading data from Cassandra")
                .setMaster("spark://dima-VirtualBox:7077");
        return SparkSession.builder().sparkContext(new SparkContext(conf)).getOrCreate();
    }

}
