package com.daromi.eva.interpreter.expressions

import com.daromi.eva.interpreter.Environment

trait Expression[+T] extends (Environment => T)
