package com.daromi.eva.core.expression

import com.daromi.eva.core.scope.Environment

final case class Int32Add(
    lhs: Expression[Int],
    rhs: Expression[Int]
) extends Expression[Int]:

  override def apply(environment: Environment): Int =
    val left  = lhs.apply(environment)
    val right = rhs.apply(environment)

    left + right

  override def toString: String = s"(+ $lhs $rhs)"

final case class Int32Subtract(
    lhs: Expression[Int],
    rhs: Expression[Int]
) extends Expression[Int]:

  override def apply(environment: Environment): Int =
    val left  = lhs.apply(environment)
    val right = rhs.apply(environment)

    left - right

  override def toString: String = s"(- $lhs $rhs)"

final case class Int32Multiply(
    lhs: Expression[Int],
    rhs: Expression[Int]
) extends Expression[Int]:

  override def apply(environment: Environment): Int =
    val left  = lhs.apply(environment)
    val right = rhs.apply(environment)

    left * right

  override def toString: String = s"(* $lhs $rhs)"

final case class Int32Divide(
    lhs: Expression[Int],
    rhs: Expression[Int]
) extends Expression[Int]:

  override def apply(environment: Environment): Int =
    val left  = lhs.apply(environment)
    val right = rhs.apply(environment)

    left / right

  override def toString: String = s"(/ $lhs $rhs)"
