package com.daromi.eva.interpreter.expressions

import com.daromi.eva.interpreter.Environment

final case class IntegerAddition(lhs: Expression[Int], rhs: Expression[Int]) extends Expression[Int]:
  override def evaluate(environment: Environment): Int =
    val left = this.lhs.evaluate(environment)
    val right = this.rhs.evaluate(environment)
    left + right

  override def toString: String = s"(+ ${this.lhs} ${this.rhs})"

final case class IntegerSubtraction(lhs: Expression[Int], rhs: Expression[Int]) extends Expression[Int]:
  override def evaluate(environment: Environment): Int =
    val left = this.lhs.evaluate(environment)
    val right = this.rhs.evaluate(environment)
    left - right

  override def toString: String = s"(- ${this.lhs} ${this.rhs})"

final case class IntegerMultiplication(lhs: Expression[Int], rhs: Expression[Int]) extends Expression[Int]:
  override def evaluate(environment: Environment): Int =
    val left = this.lhs.evaluate(environment)
    val right = this.rhs.evaluate(environment)
    left * right

  override def toString: String = s"(* ${this.lhs} ${this.rhs})"

final case class IntegerDivision(lhs: Expression[Int], rhs: Expression[Int]) extends Expression[Int]:
  override def evaluate(environment: Environment): Int =
    val left = this.lhs.evaluate(environment)
    val right = this.rhs.evaluate(environment)
    left / right

  override def toString: String = s"(/ ${this.lhs} ${this.rhs})"
