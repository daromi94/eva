package com.daromi.eva.interpreter.expressions

import com.daromi.eva.interpreter.Environment

trait Expression[+T]:
  def evaluate(environment: Environment): T
