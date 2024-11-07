package com.daromi.eva.core.lexer

import org.scalatest.flatspec.AnyFlatSpec

import scala.language.postfixOps

final class ScannerSpec extends AnyFlatSpec:

  "A scanner" should "fail on empty input" in:
    // Given
    val source = ""

    // Expecting
    assertThrows[RuntimeException] { Scanner.scan(source) }

  it should "fail on single whitespace" in:
    // Given
    val source = " "

    // Expecting
    assertThrows[RuntimeException] { Scanner.scan(source) }

  it should "fail on only whitespace characters" in:
    // Given
    val source = " \t\n\f\r"

    // Expecting
    assertThrows[RuntimeException] { Scanner.scan(source) }

  it should "scan an open parenthesis" in:
    // Given
    val source = "("

    // When
    val lexemes = Scanner.scan(source)

    // Then
    val expected = Seq("(")

    assert(lexemes == expected)

  it should "scan a close parenthesis" in:
    // Given
    val source = ")"

    // When
    val lexemes = Scanner.scan(source)

    // Then
    val expected = Seq(")")

    assert(lexemes == expected)

  it should "scan a plus sign" in:
    // Given
    val source = "+"

    // When
    val lexemes = Scanner.scan(source)

    // Then
    val expected = Seq("+")

    assert(lexemes == expected)

  it should "scan a minus sign" in:
    // Given
    val source = "-"

    // When
    val lexemes = Scanner.scan(source)

    // Then
    val expected = Seq("-")

    assert(lexemes == expected)

  it should "scan a multiplication sign" in:
    // Given
    val source = "*"

    // When
    val lexemes = Scanner.scan(source)

    // Then
    val expected = Seq("*")

    assert(lexemes == expected)

  it should "scan a division sign" in:
    // Given
    val source = "/"

    // When
    val lexemes = Scanner.scan(source)

    // Then
    val expected = Seq("/")

    assert(lexemes == expected)

  it should "scan nested parentheses" in:
    // Given
    val source = "((()))"

    // When
    val lexemes = Scanner.scan(source)

    // Then
    val expected = Seq("(", "(", "(", ")", ")", ")")

    assert(lexemes == expected)

  it should "scan spaced parentheses" in:
    // Given
    val source = " ( ) "

    // When
    val lexemes = Scanner.scan(source)

    // Then
    val expected = Seq("(", ")")

    assert(lexemes == expected)

  it should "scan multiple operators and parentheses" in:
    // Given
    val source = "( + - * / )"

    // When
    val lexemes = Scanner.scan(source)

    // Then
    val expected = Seq("(", "+", "-", "*", "/", ")")

    assert(lexemes == expected)

  it should "scan a string" in:
    // Given
    val source = "\"foo\""

    // When
    val lexemes = Scanner.scan(source)

    // Then
    val expected = Seq("\"foo\"")

    assert(lexemes == expected)

  it should "scan multiple strings" in:
    // Given
    val source = "\"foo\" \"bar\" \"baz\""

    // When
    val lexemes = Scanner.scan(source)

    // Then
    val expected = Seq("\"foo\"", "\"bar\"", "\"baz\"")

    assert(lexemes == expected)

  it should "scan a string enclosed in parentheses" in:
    // Given
    val source = "(\"foo\")"

    // When
    val lexemes = Scanner.scan(source)

    // Then
    val expected = Seq("(", "\"foo\"", ")")

    assert(lexemes == expected)

  it should "scan strings separated by an operator" in:
    // Given
    val source = "\"foo\" + \"bar\""

    // When
    val lexemes = Scanner.scan(source)

    // Then
    val expected = Seq("\"foo\"", "+", "\"bar\"")

    assert(lexemes == expected)

  it should "fail on unclosed double quote" in:
    // Given
    val source = "\""

    // Expecting
    assertThrows[RuntimeException] { Scanner.scan(source) }

  it should "fail on unclosed escaped double quote" in:
    // Given
    val source = "\"foo\\\""

    // Expecting
    assertThrows[RuntimeException] { Scanner.scan(source) }

  it should "scan a natural number" in:
    // Given
    val source = "42"

    // When
    val lexemes = Scanner.scan(source)

    // Then
    val expected = Seq("42")

    assert(lexemes == expected)

  it should "scan multiple natural numbers" in:
    // Given
    val source = "42 73 144"

    // When
    val lexemes = Scanner.scan(source)

    // Then
    val expected = Seq("42", "73", "144")

    assert(lexemes == expected)

  it should "scan a natural number enclosed in parentheses" in:
    // Given
    val source = "(42)"

    // When
    val lexemes = Scanner.scan(source)

    // Then
    val expected = Seq("(", "42", ")")

    assert(lexemes == expected)

  it should "scan natural numbers separated by an operator" in:
    // Given
    val source = "42 + 73"

    // When
    val lexemes = Scanner.scan(source)

    // Then
    val expected = Seq("42", "+", "73")

    assert(lexemes == expected)

  it should "scan a negative number" in:
    // Given
    val source = "-1"

    // When
    val lexemes = Scanner.scan(source)

    // Then
    val expected = Seq("-1")

    assert(lexemes == expected)

  it should "scan multiple negative numbers" in:
    // Given
    val source = "-1 -17 -1001"

    // When
    val lexemes = Scanner.scan(source)

    // Then
    val expected = Seq("-1", "-17", "-1001")

    assert(lexemes == expected)

  it should "scan a negative number enclosed in parentheses" in:
    // Given
    val source = "(-1)"

    // When
    val lexemes = Scanner.scan(source)

    // Then
    val expected = Seq("(", "-1", ")")

    assert(lexemes == expected)

  it should "scan negative numbers separated by an operator" in:
    // Given
    val source = "-1 + -17"

    // When
    val lexemes = Scanner.scan(source)

    // Then
    val expected = Seq("-1", "+", "-17")

    assert(lexemes == expected)

  it should "scan a decimal number" in:
    // Given
    val source = "2.7182"

    // When
    val lexemes = Scanner.scan(source)

    // Then
    val expected = Seq("2.7182")

    assert(lexemes == expected)

  it should "scan multiple decimal numbers" in:
    // Given
    val source = "2.7182 3.1415 1.6180"

    // When
    val lexemes = Scanner.scan(source)

    // Then
    val expected = Seq("2.7182", "3.1415", "1.6180")

    assert(lexemes == expected)

  it should "scan a decimal number enclosed in parentheses" in:
    // Given
    val source = "(2.7182)"

    // When
    val lexemes = Scanner.scan(source)

    // Then
    val expected = Seq("(", "2.7182", ")")

    assert(lexemes == expected)

  it should "scan decimal numbers separated by an operator" in:
    // Given
    val source = "2.7182 + 3.1415"

    // When
    val lexemes = Scanner.scan(source)

    // Then
    val expected = Seq("2.7182", "+", "3.1415")

    assert(lexemes == expected)

  it should "scan a number with underscores" in:
    // Given
    val source = "1_000_000_000"

    // When
    val lexemes = Scanner.scan(source)

    // Then
    val expected = Seq("1_000_000_000")

    assert(lexemes == expected)

  it should "scan multiple numbers with underscores" in:
    // Given
    val source = "1_000_000_000 4_096 8_388_608"

    // When
    val lexemes = Scanner.scan(source)

    // Then
    val expected = Seq("1_000_000_000", "4_096", "8_388_608")

    assert(lexemes == expected)

  it should "scan a number with underscores enclosed in parentheses" in:
    // Given
    val source = "(1_000_000_000)"

    // When
    val lexemes = Scanner.scan(source)

    // Then
    val expected = Seq("(", "1_000_000_000", ")")

    assert(lexemes == expected)

  it should "scan numbers with underscores separated by an operator" in:
    // Given
    val source = "1_000_000_000 + 4_096"

    // When
    val lexemes = Scanner.scan(source)

    // Then
    val expected = Seq("1_000_000_000", "+", "4_096")

    assert(lexemes == expected)

  it should "scan a complex arithmetic expression" in:
    // Given
    val source = "(42 + (-1 * 2.7182) / 1_000_000_000) - 0"

    // When
    val lexemes = Scanner.scan(source)

    // Then
    val expected = Seq("(", "42", "+", "(", "-1", "*", "2.7182", ")", "/", "1_000_000_000", ")", "-", "0")

    assert(lexemes == expected)

  it should "fail on multiple decimal points" in:
    // Given
    val source = "2..7182"

    // Expecting
    assertThrows[RuntimeException] { Scanner.scan(source) }

  it should "fail on trailing underscore" in:
    // Given
    val source = "1_000_000_000_"

    // Expecting
    assertThrows[RuntimeException] { Scanner.scan(source) }
