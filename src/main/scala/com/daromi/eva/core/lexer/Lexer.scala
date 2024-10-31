package com.daromi.eva.core.lexer

type Source = String

object Lexer:
  def lex(source: Source): Unit =
    val lexemes = Scanner.scan(source)
    ()
