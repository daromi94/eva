package com.daromi.eva.core.expressions

final case class IntegerLiteral(i: Int) extends IntegerExpression:
  override def value: Int = this.i

  override def toString: String = s"${this.i}"

final case class BooleanLiteral(b: Boolean) extends Expression:
  override def value: Boolean = this.b

  override def toString: String = s"${this.b}"

final case class StringLiteral(s: String) extends Expression:
  override def value: String = this.s

  override def toString: String = s"\"${this.s}\""
