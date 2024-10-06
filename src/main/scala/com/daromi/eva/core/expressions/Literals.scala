package com.daromi.eva.core.expressions

final case class BooleanLiteral(b: Boolean) extends Expression[Boolean]:
  override def evaluate(environment: Environment): Boolean = this.b

  override def toString: String = s"${this.b}"

final case class IntegerLiteral(n: Int) extends Expression[Int]:
  override def evaluate(environment: Environment): Int = this.n

  override def toString: String = s"${this.n}"

final case class StringLiteral(str: String) extends Expression[String]:
  override def evaluate(environment: Environment): String = this.str

  override def toString: String = s"\"${this.str}\""
