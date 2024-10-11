package com.daromi.eva.interpreter.expressions

import com.daromi.eva.interpreter.Environment

final case class BooleanLiteral(
    value: Boolean
) extends Expression[Boolean]:

  override def evaluate(environment: Environment): Boolean = this.value

  override def toString: String = this.value.toString

final case class IntegerLiteral(
    value: Int
) extends Expression[Int]:

  override def evaluate(environment: Environment): Int = this.value

  override def toString: String = this.value.toString

final case class StringLiteral(
    value: String
) extends Expression[String]:

  override def evaluate(environment: Environment): String = this.value

  override def toString: String = s"\"${this.value}\""
