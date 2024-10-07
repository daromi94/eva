package com.daromi.eva.interpreter.expressions

import com.daromi.eva.interpreter.Environment

final case class BooleanLiteral(b: Boolean) extends Expression[Boolean]:
  override def evaluate(environment: Environment): Boolean = this.b

  override def toString: String = this.b.toString

final case class IntegerLiteral(n: Int) extends Expression[Int]:
  override def evaluate(environment: Environment): Int = this.n

  override def toString: String = this.n.toString

final case class StringLiteral(str: String) extends Expression[String]:
  override def evaluate(environment: Environment): String = this.str

  override def toString: String = s"\"${this.str}\""
