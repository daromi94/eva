package com.daromi.eva.interpreter.expressions

import com.daromi.eva.interpreter.Environment

type Identifier = String

final case class VariableDeclaration[T](
    identifier: Identifier,
    rhs: Expression[T]
) extends Expression[T]:

  override def evaluate(environment: Environment): T =
    if environment.exists(this.identifier) then
      throw new RuntimeException(s"'${this.identifier}' already defined in scope")
    else
      val value = this.rhs.evaluate(environment)
      environment.set(this.identifier, value)
      value

  override def toString: String = s"(var ${this.identifier} ${this.rhs})"

final case class VariableAssignment[T](
    identifier: Identifier,
    rhs: Expression[T]
) extends Expression[T]:

  override def evaluate(environment: Environment): T =
    environment.resolve(this.identifier) match
      case Some(scope) =>
        val value = this.rhs.evaluate(environment)
        scope.set(this.identifier, value)
        value

      case None => throw new RuntimeException(s"'${this.identifier}' not defined in scope chain")

  override def toString: String = s"(set ${this.identifier} ${this.rhs})"

final case class VariableLookup[T](
    identifier: Identifier
) extends Expression[T]:

  override def evaluate(environment: Environment): T =
    environment.get(this.identifier) match
      case Some(value) => value.asInstanceOf[T]
      case None        => throw new RuntimeException(s"'${this.identifier}' not defined in scope chain")

  override def toString: String = this.identifier
