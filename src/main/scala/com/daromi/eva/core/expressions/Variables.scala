package com.daromi.eva.core.expressions

type Identifier = String

final case class VariableDeclare[T](
    identifier: Identifier,
    rhs: Expression[T]
) extends Expression[T]:

  override def evaluate(environment: Environment): T =
    val right = this.rhs.evaluate(environment)

    environment.assign(this.identifier, right)

    right

  override def toString: String = s"(var ${this.identifier} ${this.rhs})"

final case class VariableLookup[T](identifier: Identifier)
    extends Expression[T]:

  override def evaluate(environment: Environment): T =
    environment.lookup(this.identifier) match
      case Some(value) => value.asInstanceOf[T]

      case None =>
        throw new RuntimeException(s"'${this.identifier}' not defined in scope")

  override def toString: String = this.identifier
