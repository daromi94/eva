package com.daromi.eva.core.expressions

import com.daromi.eva.core.Environment

final case class If[T](
    predicate: Expression[Boolean],
    consequent: Expression[T],
    alternative: Expression[T]
) extends Expression[T]:

  override def apply(environment: Environment): T =
    val result = this.predicate.apply(environment)

    if result then
      this.consequent.apply(environment)
    else
      this.alternative.apply(environment)

  override def toString: String = s"(if ${this.predicate} ${this.consequent} ${this.alternative})"

final case class While(
    condition: Expression[Boolean],
    body: Block[Unit]
) extends Expression[Unit]:

  override def apply(environment: Environment): Unit =
    while this.condition.apply(environment) do this.body.apply(environment)

  override def toString: String = s"(while ${this.condition} ${this.body})"
