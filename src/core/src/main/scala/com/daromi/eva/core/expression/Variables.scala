package com.daromi.eva.core.expression

import com.daromi.eva.core.scope.Environment

type Identifier = String

final case class VariableDeclaration[T](
    identifier: Identifier,
    expression: Expression[T]
) extends Expression[T]:

  override def apply(environment: Environment): T =
    if environment.contains(identifier) then
      throw new RuntimeException(s"'$identifier' already defined in scope")
    else
      val value = expression.apply(environment)
      environment.put(identifier, value)
      value

  override def toString: String = s"(var $identifier $expression)"

final case class VariableLookup[T](
    identifier: Identifier
) extends Expression[T]:

  override def apply(environment: Environment): T =
    environment.get(identifier) match
      case Some(value) => value.asInstanceOf[T]
      case None        => throw new RuntimeException(s"'$identifier' not defined in scope chain")

  override def toString: String = identifier

final case class VariableAssignment[T](
    identifier: Identifier,
    expression: Expression[T]
) extends Expression[T]:

  override def apply(environment: Environment): T =
    environment.locate(identifier) match
      case Some(scope) =>
        val value = expression.apply(environment)
        scope.put(identifier, value)
        value

      case None => throw new RuntimeException(s"'$identifier' not defined in scope chain")

  override def toString: String = s"(set $identifier $expression)"
