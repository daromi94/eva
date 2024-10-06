package com.daromi.eva.core.expressions

final case class BooleanVariableDeclaration(
    identifier: String,
    rhs: BooleanExpression
) extends BooleanExpression:

  override def value: Boolean = this.rhs

  override def toString: String = s"(var ${this.identifier} ${this.rhs}"

final case class IntegerVariableDeclaration(
    identifier: String,
    rhs: IntegerExpression
) extends IntegerExpression:

  override def value: Boolean = this.rhs

  override def toString: String = s"(var ${this.identifier} ${this.rhs}"

final case class StringVariableDeclaration(
    identifier: String,
    rhs: StringExpression
) extends StringExpression:

  override def value: Boolean = this.rhs

  override def toString: String = s"(var ${this.identifier} ${this.rhs}"
