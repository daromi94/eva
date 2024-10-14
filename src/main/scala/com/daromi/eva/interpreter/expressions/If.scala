package com.daromi.eva.interpreter.expressions

import com.daromi.eva.interpreter.Environment

final case class If[T](
    predicate: Expression[Boolean],
    consequent: Expression[T],
    alternative: Expression[T]
) extends Expression[T]:

  override def evaluate(environment: Environment): T =
    val result = this.predicate.evaluate(environment)

    if result then this.consequent.evaluate(environment) else this.alternative.evaluate(environment)

  override def toString: String = s"(if ${this.predicate} ${this.consequent} ${this.alternative})"
