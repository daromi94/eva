package com.daromi.eva.core.lexer

import com.daromi.eva.core.lexer.RawSymbols.*

import scala.collection.mutable.ArrayBuffer

private type Lexeme = String
private type Buffer = Array[Char]
private type Cursor = Int

final private class Scanner private (
    private val buffer: Buffer,
    private var cursor: Cursor = 0
):
  def scan(): Seq[Lexeme] =
    val lexemes = ArrayBuffer.empty[Lexeme]

    while cursor < buffer.length do
      advance().foreach { lexemes += _ }

    lexemes.toSeq

  private def current: Char = buffer(cursor)

  private def advance(): Option[Lexeme] =
    current match
      case OpenParenthesis | CloseParenthesis => munchOne()
      case Plus | Minus | Multiply | Divide   => munchOne()
      case DoubleQuote                        => munchString()
      case c if c.isDigit                     => None
      case c if c.isUnicodeIdentifierStart    => None
      case c if c.isWhitespace                => ignore()

  private def munchOne(): Option[Lexeme] =
    val lexeme = current.toString
    cursor += 1
    Some(lexeme)

  private def munchString(): Option[Lexeme] =
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
      Some(lexeme)
    else
      None

  private def ignore(): Option[Lexeme] =
    cursor += 1
    None

private object Scanner:

  def scan(source: Source): Seq[Lexeme] =
    if source.isBlank then
      Seq.empty
    else
      val buffer  = source.toCharArray
      val scanner = Scanner(buffer)
      scanner.scan()
