package com.daromi.eva.core.expressions

import com.daromi.eva.core.Environment

type Identifier = String

final case class VariableAssign[T](
    identifier: Identifier,
    expression: Expression[T]
) extends Expression[T]:

  override def evaluate(environment: Environment): T =
    val value = this.expression.evaluate(environment)

    environment.assign(this.identifier, value)

    value

  override def toString: String = s"(set ${this.identifier} ${this.expression})"

final case class VariableLookup[T](identifier: Identifier)
    extends Expression[T]:

  override def evaluate(environment: Environment): T =
    environment.lookup(this.identifier) match
      case Some(value) => value.asInstanceOf[T]

      case None =>
        throw new RuntimeException(s"'${this.identifier}' not defined in scope")

  override def toString: String = this.identifier
