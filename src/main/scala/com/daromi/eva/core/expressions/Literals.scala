package com.daromi.eva.core.expressions

case class IntegerLiteral(i: Int) extends Expression:
  override def value: Int = this.i

  override def toString: String = s"${this.i}"

case class BooleanLiteral(b: Boolean) extends Expression:
  override def value: Boolean = this.b

  override def toString: String = s"${this.b}"

case class StringLiteral(s: String) extends Expression:
  override def value: String = this.s

  override def toString: String = s"\"${this.s}\""
