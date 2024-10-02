package com.daromi.eva.core.expressions

final case class BooleanLiteral(b: Boolean) extends BooleanExpression:
  override def value: Boolean = this.b

  override def toString: String = s"${this.b}"

final case class IntegerLiteral(n: Int) extends IntegerExpression:
  override def value: Int = this.n

  override def toString: String = s"${this.n}"

final case class StringLiteral(str: String) extends StringExpression:
  override def value: String = this.str

  override def toString: String = s"\"${this.str}\""
