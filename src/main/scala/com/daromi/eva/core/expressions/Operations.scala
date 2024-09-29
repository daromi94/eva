package com.daromi.eva.core.expressions

case class IntegerAddition(
    lhs: IntegerExpression,
    rhs: IntegerExpression
) extends IntegerExpression:

  override def value: Int = this.lhs.value + this.rhs.value

  override def toString: String = s"(+ ${this.lhs} ${this.rhs})"
