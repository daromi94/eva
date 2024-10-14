package com.daromi.eva.interpreter.expressions

import com.daromi.eva.interpreter.Environment

final case class While(
    condition: Expression[Boolean],
    body: Block[Unit]
) extends Expression[Unit]:

  override def evaluate(environment: Environment): Unit =
    while this.condition.evaluate(environment) do this.body.evaluate(environment)

  override def toString: String = s"(while ${this.condition} ${this.body})"
