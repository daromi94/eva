package com.daromi.eva.core.expressions

final case class IntegerAdd(
    lhs: Expression[Int],
    rhs: Expression[Int]
) extends Expression[Int]:

  override def evaluate(environment: Environment): Int =
    val left = this.lhs.evaluate(environment)

    val right = this.rhs.evaluate(environment)

    left + right

  override def toString: String = s"(+ ${this.lhs} ${this.rhs})"

final case class IntegerSubtract(
    lhs: Expression[Int],
    rhs: Expression[Int]
) extends Expression[Int]:

  override def evaluate(environment: Environment): Int =
    val left = this.lhs.evaluate(environment)

    val right = this.rhs.evaluate(environment)

    left - right

  override def toString: String = s"(- ${this.lhs} ${this.rhs})"

final case class IntegerMultiply(
    lhs: Expression[Int],
    rhs: Expression[Int]
) extends Expression[Int]:

  override def evaluate(environment: Environment): Int =
    val left = this.lhs.evaluate(environment)

    val right = this.rhs.evaluate(environment)

    left * right

  override def toString: String = s"(* ${this.lhs} ${this.rhs})"

final case class IntegerDivide(
    lhs: Expression[Int],
    rhs: Expression[Int]
) extends Expression[Int]:

  override def evaluate(environment: Environment): Int =
    val left = this.lhs.evaluate(environment)

    val right = this.rhs.evaluate(environment)

    left / right

  override def toString: String = s"(/ ${this.lhs} ${this.rhs})"
