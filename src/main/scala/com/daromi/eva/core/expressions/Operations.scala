package com.daromi.eva.core.expressions

case class IntegerAddition(lhs: Expression, rhs: Expression) extends Expression:
  override def value: Int =
    val (left, right) = (this.lhs.value, this.rhs.value)

    (left, right) match
      case (l: Int, r: Int) => l + r

      case (l, r) => throw new IllegalArgumentException(s"expected integer operands, but got: ($l, $r)")

  override def toString: String = s"(+ ${this.lhs} ${this.rhs})"
