package io.patamon.spark.kt.simple

import io.patamon.spark.kt.base.Person
import io.patamon.spark.kt.base.TestSparkBase
import io.patamon.spark.kt.sql.udf
import io.patamon.spark.kt.utils.getString
import org.junit.jupiter.api.Test

/**
 * Desc: Hello Spark Test
 */
object TestHello : TestSparkBase("Test Hello") {

    private val simpleData = listOf(
        Person(1L, "hello", 22),
        Person(2L, "world", 23)
    )

    @Test
    fun test_create_dataFrame() {
        val df = spark.createDataFrame(simpleData)
        assert(df.collect().size == 2)
        assert(df.head().getString("name") == "hello")
    }

    @Test
    fun test_udf() {
        // create udf
        val udf = udf { s: String -> "hello udf $s" }
        val df = spark.createDataFrame(simpleData)
        // invoke udf
        val row = df.select(udf(df("name")) `as` "udf_name").head()
        assert(row.getString("udf_name") == "hello udf hello")
    }

    @Test
    fun test_sql() {
        spark.createDataFrame(simpleData).createOrReplaceTempView("test_tables")
        val df = spark.sql("""
            select
              id, name, age, concat_ws("_", name, age) name_age
            from test_tables
        """.trimIndent()
        )
        assert(df.collect().size == 2)
        assert(df.head().getString("name_age") == "hello_22")
    }
}
