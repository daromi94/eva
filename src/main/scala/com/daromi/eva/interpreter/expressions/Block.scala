package com.daromi.eva.interpreter.expressions

import com.daromi.eva.interpreter.Environment

final case class Block[T](
    expressions: Seq[Expression[Any]]
) extends Expression[T]:

  override def evaluate(environment: Environment): T =
    if this.expressions.isEmpty then throw new RuntimeException("empty block not allowed")
    else
      val scope  = Environment.childOf(environment)
      val values = this.expressions.map(_.evaluate(scope))
      values.last.asInstanceOf[T]

  override def toString: String = s"(begin ${this.expressions.mkString(" ")})"
