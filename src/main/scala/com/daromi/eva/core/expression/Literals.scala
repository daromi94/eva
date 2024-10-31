package com.daromi.eva.core.expression

import com.daromi.eva.core.scope.Environment

final case class BooleanLiteral(value: Boolean) extends Expression[Boolean]:

  override def apply(environment: Environment): Boolean = this.value

  override def toString: String = this.value.toString

final case class Int8Literal(value: Byte) extends Expression[Byte]:

  override def apply(environment: Environment): Byte = this.value

  override def toString: String = s"(i8 ${this.value})"

final case class Int16Literal(value: Short) extends Expression[Short]:

  override def apply(environment: Environment): Short = this.value

  override def toString: String = s"(i16 ${this.value})"

final case class Int32Literal(value: Int) extends Expression[Int]:

  override def apply(environment: Environment): Int = this.value

  override def toString: String = s"(i32 ${this.value})"

final case class Int64Literal(value: Long) extends Expression[Long]:

  override def apply(environment: Environment): Long = this.value

  override def toString: String = s"(i64 ${this.value})"

final case class StringLiteral(value: String) extends Expression[String]:

  override def apply(environment: Environment): String = this.value

  override def toString: String = s"\"${this.value}\""
