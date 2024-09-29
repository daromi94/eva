package com.daromi.eva.core.expressions

final case class IntegerAddition(
    lhs: IntegerExpression,
    rhs: IntegerExpression
) extends IntegerExpression:

  override def value: Int = this.lhs.value + this.rhs.value

  override def toString: String = s"(+ ${this.lhs} ${this.rhs})"

final case class IntegerSubtraction(
    lhs: IntegerExpression,
    rhs: IntegerExpression
) extends IntegerExpression:

  override def value: Int = this.lhs.value - this.rhs.value

  override def toString: String = s"(- ${this.lhs} ${this.rhs})"

final case class IntegerMultiplication(
    lhs: IntegerExpression,
    rhs: IntegerExpression
) extends IntegerExpression:

  override def value: Int = this.lhs.value * this.rhs.value

  override def toString: String = s"(* ${this.lhs} ${this.rhs})"
