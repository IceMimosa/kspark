package io.patamon.kt.spark

import org.apache.spark.sql.SparkSession

/**
 * Desc:
 *
 * Mail: chk19940609@gmail.com
 * Created by IceMimosa
 * Date: 2019-01-02
 */
fun main(args: Array<String>) {
    val spark = SparkSession.builder()
        .appName("TEST-JOB")
        .master("local[*]")
        .config("spark.sql.warehouse.dir", "hdfs://127.0.0.1:9000/user/hive/warehouse/")
        .config("hive.metastore.uris", "thrift://localhost:9083")
        .config("spark.ui.port", "4088")
        .enableHiveSupport()
        .orCreate
    spark.sparkContext().hadoopConfiguration().set("fs.defaultFS", "hdfs://127.0.0.1:9000")

    val ps = mutableListOf(
        Persion(1L, "mimosa", 22)
    )
    val df = spark.createDataFrame(ps, Persion::class.java)
    df.show()
}

data class Persion(val id: Long, val name: String, val age: Int)

/*


System.setProperty("HADOOP_USER_NAME", "hadoop")
val spark = SparkSession.builder()
  .appName("TEST-JOB")
  .master("local[*]")
  .config("spark.sql.warehouse.dir", s"hdfs://127.0.0.1:9000/user/hive/warehouse/")
  .config("hive.metastore.uris", "thrift://localhost:9083")
  .config("spark.ui.port", "4088")
  .enableHiveSupport()
  .getOrCreate()
spark.sparkContext.hadoopConfiguration.set("fs.defaultFS", "hdfs://127.0.0.1:9000")
import spark.implicits._

val df = Seq(
  (1L, "mimosa", 22),(1L, "mimosa", 22),
  (2L, "mimosa", 22),(1L, "mimosa", 22),
  (3L, "mimosa", 22),(1L, "mimosa", 22),
  (4L, "mimosa", 22),(1L, "mimosa", 22),
  (5L, "mimosa", 22),(1L, "mimosa", 22),
  (5L, "mimosa", 22),(1L, "mimosa", 22),
  (5L, "mimosa", 22),(1L, "mimosa", 22),
  (5L, "mimosa", 22),(1L, "mimosa", 22),
  (5L, "mimosa", 22),(1L, "mimosa", 22),
  (5L, "mimosa", 22),(1L, "mimosa", 22)
).toDF("id", "name", "age")

df.show()
 */