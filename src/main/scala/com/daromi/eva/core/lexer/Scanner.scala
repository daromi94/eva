package com.daromi.eva.core.lexer

import com.daromi.eva.core.lexer.RawSymbols.*

import scala.collection.mutable.ArrayBuffer

private type Lexeme     = String
private type ScanBuffer = Array[Char]
private type Cursor     = Int

final private class Scanner private (
    private val buffer: ScanBuffer,
    private var cursor: Cursor = 0
):
  def scan(): Seq[Lexeme] =
    val lexemes = ArrayBuffer.empty[Lexeme]

    while cursor < buffer.length do
      if current.isWhitespace then
        skipOne()
      else
        val lexeme = advance()
        lexemes += lexeme

    lexemes.toSeq

  private def current: Char = buffer(cursor)

  private def skipOne(): Unit =
    assume(0 <= cursor && cursor < buffer.length, s"cursor $cursor out of bounds")

    cursor += 1

  private def advance(): Lexeme =
    assume(0 <= cursor && cursor < buffer.length, s"cursor $cursor out of bounds")

    current match
      case OpenParenthesis | CloseParenthesis => takeOne()
      case Plus | Minus | Multiply | Divide   => takeOne()
      case DoubleQuote                        => munchString()
      case c if c.isDigit                     => munchNumber()
      case c                                  => throw RuntimeException(s"invalid character '$c' at cursor $cursor")

  private def takeOne(): Lexeme =
    assume(0 <= cursor && cursor < buffer.length, s"cursor $cursor out of bounds")

    val lexeme = current.toString
    cursor += 1
    lexeme

  private def munchString(): Lexeme =
    assume(0 <= cursor && cursor < buffer.length, s"cursor $cursor out of bounds")
    assume(current == DoubleQuote, s"double quote expected at start of string, at cursor $cursor")

    val start = cursor
    var i     = start + 1
    var found = false

    while !found && i < buffer.length do
      if buffer(i) == DoubleQuote && buffer(i - 1) != Escape then
        found = true
      i += 1

    if found then
      val lexeme = buffer.slice(start, i).mkString
      cursor = i
      lexeme
    else
      throw RuntimeException(s"unterminated string at cursor $cursor")

  private def munchNumber(): Lexeme =
    assume(0 <= cursor && cursor < buffer.length, s"cursor $cursor out of bounds")
    assume(current.isDigit, s"digit expected at start of number, at cursor $cursor")

    val partial = ArrayBuffer(current)
    var decimal = false

    val start = cursor
    var i     = start + 1
    var found = false

    while !found && i < buffer.length do
      buffer(i) match
        case c if c.isDigit || c == Underscore => partial += c

        case c if c == DecimalPoint =>
          if !decimal then
            partial += c
            decimal = true
          else
            throw RuntimeException(s"multiple decimal points in number at cursor $cursor")

        case _ => found = true

      i += 1

    if partial.last == Underscore then
      throw RuntimeException(s"trailing underscore in number at cursor $cursor")

    val lexeme = partial.mkString
    cursor = if found then i - 1 else i
    lexeme

private object Scanner:

  def scan(source: Source): Seq[Lexeme] =
    require(!source.isBlank)

    val buffer  = source.toCharArray
    val scanner = Scanner(buffer)
    scanner.scan()
