package com.daromi.eva.core.expressions

trait Expression:
  def value: Any

trait IntegerExpression extends Expression:
  def value: Int
