package com.daromi.eva.core.lexer

type Lexeme = String

object Scanner:
  private type Buffer = Array[Char]
  private type Cursor = Int
  private type Index  = Int

  private val OpenParenthesis  = '('
  private val CloseParenthesis = ')'
  private val Quote            = '"'
  private val Escape           = '\\'
  private val DecimalPoint     = '.'
  private val Underscore       = '_'

  final private case class LexemeResult(lexeme: Option[Lexeme], cursor: Cursor)

  def scan(source: Source): Seq[Lexeme] =
    if source.isBlank then
      Seq.empty[Lexeme]
    else
      val buffer  = source.toCharArray
      var lexemes = List.empty[Lexeme]
      var cursor  = 0

      while cursor < buffer.length do
        val result = peek(buffer, cursor)
        result.lexeme.foreach { l => lexemes = l :: lexemes }
        cursor = result.cursor

      lexemes.reverse

  private def peek(buffer: Buffer, cursor: Cursor): LexemeResult =
    val munch = muncher(buffer, cursor)

    val first = buffer(cursor)

    first match
      case OpenParenthesis | CloseParenthesis => munch(delimiterEndsAt)
      case Quote                              => munch(stringEndsAt)
      case c if c.isDigit                     => munch(numberEndsAt)
      case c if c.isUnicodeIdentifierStart    => munch(symbolEndsAt)
      case c if c.isWhitespace                => ignore(cursor)

  private def muncher(buffer: Buffer, cursor: Cursor)(endsAt: (Buffer, Cursor) => Index): LexemeResult =
    val end = endsAt(buffer, cursor)
    if end == -1 then
      throw new RuntimeException(s"scanner: invalid lexeme: no end boundary from cursor $cursor")

    val next = end + 1

    val lexeme = buffer.slice(cursor, next)

    LexemeResult(Some(lexeme.mkString), next)

  private def delimiterEndsAt(buffer: Buffer, cursor: Cursor): Index = cursor

  private def stringEndsAt(buffer: Buffer, cursor: Cursor): Index =
    if cursor > 0 && buffer(cursor - 1) == Escape then
      -1
    else
      var i = cursor + 1

      while i < buffer.length do
        if buffer(i - 1) != Escape && buffer(i) == Quote then
          return i
        i += 1

      -1

  private def numberEndsAt(buffer: Buffer, cursor: Cursor): Index =
    val next = buffer.indexWhere(c => !c.isDigit && c != DecimalPoint && c != Underscore, cursor)
    if next == -1 then
      -1
    else
      next - 1

  private def symbolEndsAt(buffer: Buffer, cursor: Cursor): Index =
    val next = buffer.indexWhere(c => c.isWhitespace || c == CloseParenthesis, cursor)
    if next == -1 then
      -1
    else
      next - 1

  private def ignore(cursor: Cursor): LexemeResult = LexemeResult(None, cursor + 1)
