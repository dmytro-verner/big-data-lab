# big-data-lab
Application that pipelines data through Apache Kafka, Apache Cassandra, and Apache Spark.

There are two main classes:

ConsumerApp - listens to a partical set of Kafka brokers and stores user visit data to Cassandra table respectively.
UserVisitsStatSparkJob - gets data from Cassandra table and returns results based on provided cql.
      The given solution returns top 10 most popular users' countries.

Needs the following setup in order to have data pipelined through Apache Kafka, Apache Cassandra, Apache Spark:


      1. Start up zookeeper
      2. Start up Kafka broker on port 9092
      3. Create Kafka topic called "user_visits"
      4. Create a keyspace in Cassandra named as "user_stats"
      5. Create a table in Cassandra named as "user_visits"
      6. Package UserVisit class into jar file and put it into jars folder of Spark
      7. Start up Spark master
      8. Start up Spark slave
      9. Start ConsumerApp 
      10. Create Kafka producer
      11. Put data compatible with user visit format(see UserVisit model class)
        11.1 You can see data from Kafka producer in Cassandra table by querying the created database
      12. Stop ConsumerApp
      13. Start UserVisitsStatSparkJob
        13.1 Top 10 most popular countries are displayed on console
