package com.daromi.eva.core.expression

import com.daromi.eva.core.scope.Environment

final case class If[T](
    predicate: Expression[Boolean],
    consequent: Expression[T],
    alternative: Expression[T]
) extends Expression[T]:

  override def apply(environment: Environment): T =
    val result = predicate.apply(environment)

    if result then
      consequent.apply(environment)
    else
      alternative.apply(environment)

  override def toString: String = s"(if $predicate $consequent $alternative)"

final case class While(
    condition: Expression[Boolean],
    body: Block[Unit]
) extends Expression[Unit]:

  override def apply(environment: Environment): Unit =
    while condition.apply(environment) do body.apply(environment)

  override def toString: String = s"(while $condition $body)"
