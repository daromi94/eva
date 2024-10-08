package com.daromi.eva.interpreter.expressions

import com.daromi.eva.interpreter.Environment

final case class Block(
    expressions: Seq[Expression[Any]]
) extends Expression[Any]:

  override def evaluate(environment: Environment): Any =
    if this.expressions.isEmpty then throw new RuntimeException("empty block not allowed")
    else
      val scope = Environment.childOf(environment)
      this.expressions.map(_.evaluate(scope)).last

  override def toString: String = s"(begin ${this.expressions.mkString(" ")})"
