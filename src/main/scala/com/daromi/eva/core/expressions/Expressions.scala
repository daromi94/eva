package com.daromi.eva.core.expressions

trait Expression[T]:
  def value: T

trait BooleanExpression extends Expression[Boolean]

trait IntegerExpression extends Expression[Int]

trait StringExpression extends Expression[String]
