package com.daromi.eva.core.expressions

import com.daromi.eva.core.Environment

trait Expression[+T]:
  def evaluate(environment: Environment): T
