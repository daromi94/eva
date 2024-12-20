package com.daromi.eva.core.expression

import com.daromi.eva.core.scope.Environment

final case class Block[T](expressions: Seq[Expression[Value]]) extends Expression[T]:

  override def apply(environment: Environment): T =
    if expressions.isEmpty then
      throw new RuntimeException("empty block not allowed")
    else
      val scope  = Environment.childOf(environment)
      val values = expressions.map { _.apply(scope) }
      values.last.asInstanceOf[T]

  override def toString: String = s"(begin ${expressions.mkString(" ")})"
