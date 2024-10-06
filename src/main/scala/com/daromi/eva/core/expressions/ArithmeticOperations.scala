package com.daromi.eva.core.expressions

final case class IntegerAdd(
    lhs: IntegerExpression,
    rhs: IntegerExpression
) extends IntegerExpression:

  override def value: Int = this.lhs.value + this.rhs.value

  override def toString: String = s"(+ ${this.lhs} ${this.rhs})"

final case class IntegerSubtract(
    lhs: IntegerExpression,
    rhs: IntegerExpression
) extends IntegerExpression:

  override def value: Int = this.lhs.value - this.rhs.value

  override def toString: String = s"(- ${this.lhs} ${this.rhs})"

final case class IntegerMultiply(
    lhs: IntegerExpression,
    rhs: IntegerExpression
) extends IntegerExpression:

  override def value: Int = this.lhs.value * this.rhs.value

  override def toString: String = s"(* ${this.lhs} ${this.rhs})"

final case class IntegerDivide(
    lhs: IntegerExpression,
    rhs: IntegerExpression
) extends IntegerExpression:

  override def value: Int = this.lhs.value / this.rhs.value

  override def toString: String = s"(/ ${this.lhs} ${this.rhs})"
