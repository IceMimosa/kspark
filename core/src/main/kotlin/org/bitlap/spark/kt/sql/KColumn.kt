package org.bitlap.spark.kt.sql

import org.bitlap.spark.kt.utils.toSeq
import org.apache.spark.sql.Column
import org.apache.spark.sql.types.DataType
import org.apache.spark.sql.types.Metadata
import scala.Symbol

/**
 * Desc: Column wrapper functions, and return Scala Spark [Column]
 */
open class KColumn(val column: Column) {

    operator fun unaryMinus() = column.`unary_$minus`().k()
    operator fun not() = column.`unary_$bang`().k()
    infix fun `===`(other: Any?) = column.`$eq$eq$eq`(other).k()
    infix fun `=!=`(other: Any?) = column.`$eq$bang$eq`(other).k()
    infix fun `!==`(other: Any?) = column.`$bang$eq$eq`(other).k()
    infix fun equalTo(other: Any?) = column.equalTo(other).k()
    infix fun notEqual(other: Any?) = column.notEqual(other).k()
    // infix fun `>`(other: Any?) = column.`$greater`(other)
    infix fun gt(other: Any?) = column.gt(other).k()
    // infix fun `<`(other: Any?) = column.`$less`(other)
    infix fun lt(other: Any?) = column.lt(other).k()
    // infix fun `>=`(other: Any?) = column.`$greater$eq`(other)
    infix fun geq(other: Any?) = column.geq(other).k()
    // infix fun `<=`(other: Any?) = column.`$less$eq`(other)
    infix fun leq(other: Any?) = column.leq(other).k()
    // infix fun `<=>`(other: Any?) = column.`$less$eq$greater`(other)
    infix fun eqNullSafe(other: Any?) = column.eqNullSafe(other).k()
    fun when_(condition: KColumn, value: Any) = column.`when`(condition.column, value).k()
    fun otherwise(value: Any) = column.otherwise(value).k()
    fun between(lowerBound: Any, upperBound: Any) = column.between(lowerBound, upperBound).k()
    fun isNaN() = column.isNaN.k()
    fun isNull() = column.isNull.k()
    fun isNotNull() = column.isNotNull.k()
    infix fun `||`(other: KColumn) = column.`$bar$bar`(other.column).k()
    infix fun or(other: KColumn) = column.or(other.column).k()
    infix fun `&&`(other: KColumn) = column.`$amp$amp`(other.column).k()
    infix fun and(other: KColumn) = column.and(other.column).k()
    infix operator fun plus(other: Any?) = column.`$plus`(other).k()
    infix operator fun minus(other: Any?) = column.`$minus`(other).k()
    infix operator fun times(other: Any?) = column.`$times`(other).k()
    infix fun multiply(other: Any?) = column.multiply(other).k()
    infix operator fun div(other: Any?) = column.`$div`(other).k()
    infix fun divide(other: Any?) = column.divide(other).k()
    infix operator fun rem(other: Any?) = column.`$percent`(other).k()
    infix fun mod(other: Any?) = column.mod(other).k()
    infix fun isin(list: Any?) = column.isin(list).k()
    infix fun isInCollection(values: Iterable<Any>) = column.isInCollection(values).k()
    infix fun like(literal: String) = column.like(literal).k()
    infix fun rlike(literal: String) = column.rlike(literal).k()
    fun getItem(key: Any) = column.getItem(key).k()
    fun getField(fieldName: String) = column.getField(fieldName).k()
    fun substr(startPos: KColumn, len: KColumn) = column.substr(startPos.column, len.column).k()
    fun substr(startPos: Int, len: Int) = column.substr(startPos, len).k()
    fun contains(other: Any) = column.contains(other).k()
    fun startsWith(other: KColumn) = column.startsWith(other.column).k()
    fun startsWith(literal: String) = column.startsWith(literal).k()
    fun endsWith(other: KColumn) = column.endsWith(other.column).k()
    fun endsWith(literal: String) = column.endsWith(literal).k()
    fun alias(alias: String) = column.alias(alias).k()
    infix fun `as`(alias: String) = column.`as`(alias).k()
    fun `as`(aliases: List<String>) = column.`as`(aliases.toSeq()).k()
    fun `as`(alias: Symbol) = column.`as`(alias).k()
    fun `as`(alias: String, metadata: Metadata) = column.`as`(alias, metadata).k()
    fun name(alias: String) = column.name(alias).k()
    infix fun cast(to: DataType) = column.cast(to).k()
    infix fun cast(to: String) = column.cast(to).k()
    fun desc() = column.desc().k()
    fun desc_nulls_first() = column.desc_nulls_first().k()
    fun desc_nulls_last() = column.desc_nulls_last().k()
    fun asc() = column.asc().k()
    fun asc_nulls_first() = column.asc_nulls_first().k()
    fun asc_nulls_last() = column.asc_nulls_last().k()
    fun explain(extended: Boolean) = column.explain(extended)
    fun bitwiseOR(other: Any) = column.bitwiseOR(other).k()
    fun bitwiseAND(other: Any) = column.bitwiseAND(other).k()
    fun bitwiseXOR(other: Any) = column.bitwiseXOR(other).k()
    fun over(window: org.apache.spark.sql.expressions.WindowSpec) = column.over(window).k()
    fun over() = column.over().k()
}
