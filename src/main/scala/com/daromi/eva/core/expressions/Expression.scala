package com.daromi.eva.core.expressions

trait Expression[+T]:
  def evaluate(environment: Environment): T
