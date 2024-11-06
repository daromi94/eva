package com.daromi.eva.core.expression

import com.daromi.eva.core.scope.Environment

trait Expression[+T] extends (Environment => T)

type Value = Any
