package com.daromi.eva.core.expressions

import com.daromi.eva.core.Environment

final case class Block(expressions: Seq[Expression[Any]]) extends Expression[Any]:
  override def evaluate(environment: Environment): Any =
    if this.expressions.isEmpty then throw new RuntimeException("empty block not allowed")
    else
      val scope = Environment.withParent(environment)
      this.expressions.map(_.evaluate(scope)).last

  override def toString: String = s"(begin ${this.expressions.mkString(" ")})"
