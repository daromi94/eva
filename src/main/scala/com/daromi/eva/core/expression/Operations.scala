package com.daromi.eva.core.expression

import com.daromi.eva.core.scope.Environment

final case class Int32Add(
    lhs: Expression[Int],
    rhs: Expression[Int]
) extends Expression[Int]:

  override def apply(environment: Environment): Int =
    val left  = this.lhs.apply(environment)
    val right = this.rhs.apply(environment)

    left + right

  override def toString: String = s"(+ ${this.lhs} ${this.rhs})"

final case class Int32Subtract(
    lhs: Expression[Int],
    rhs: Expression[Int]
) extends Expression[Int]:

  override def apply(environment: Environment): Int =
    val left  = this.lhs.apply(environment)
    val right = this.rhs.apply(environment)

    left - right

  override def toString: String = s"(- ${this.lhs} ${this.rhs})"

final case class Int32Multiply(
    lhs: Expression[Int],
    rhs: Expression[Int]
) extends Expression[Int]:

  override def apply(environment: Environment): Int =
    val left  = this.lhs.apply(environment)
    val right = this.rhs.apply(environment)

    left * right

  override def toString: String = s"(* ${this.lhs} ${this.rhs})"

final case class Int32Divide(
    lhs: Expression[Int],
    rhs: Expression[Int]
) extends Expression[Int]:

  override def apply(environment: Environment): Int =
    val left  = this.lhs.apply(environment)
    val right = this.rhs.apply(environment)

    left / right

  override def toString: String = s"(/ ${this.lhs} ${this.rhs})"
