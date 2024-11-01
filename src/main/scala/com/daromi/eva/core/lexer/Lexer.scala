package com.daromi.eva.core.lexer

type Source = String

object Lexer:

  def lex(source: Source): Unit =
    require(!source.isBlank)

    val lexemes = Scanner.scan(source)
    ()
