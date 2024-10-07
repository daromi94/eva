package com.daromi.eva.core.expressions

import com.daromi.eva.core.Environment

type Identifier = String

final case class VariableDeclaration[T](identifier: Identifier, expression: Expression[T]) extends Expression[T]:
  override def evaluate(environment: Environment): T =
    if environment.exists(identifier) then throw new RuntimeException(s"'${this.identifier}' already defined in scope")
    else
      val value = this.expression.evaluate(environment)
      environment.set(this.identifier, value)
      value

  override def toString: String = s"(var ${this.identifier} ${this.expression})"

final case class VariableAssignment[T](identifier: Identifier, expression: Expression[T]) extends Expression[T]:
  override def evaluate(environment: Environment): T =
    val value = this.expression.evaluate(environment)
    environment.set(this.identifier, value)
    value

  override def toString: String = s"(set ${this.identifier} ${this.expression})"

final case class VariableLookup[T](identifier: Identifier) extends Expression[T]:
  override def evaluate(environment: Environment): T =
    environment.get(this.identifier) match
      case Some(value) => value.asInstanceOf[T]
      case None        => throw new RuntimeException(s"'${this.identifier}' not defined in scope")

  override def toString: String = this.identifier
