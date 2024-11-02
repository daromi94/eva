package com.daromi.eva.core.lexer

import org.scalatest.flatspec.AnyFlatSpec

import scala.language.postfixOps

final class ScannerSpec extends AnyFlatSpec:

  "A scanner" should "fail on empty source" in:
    // Given
    val source = ""

    // Expecting
    assertThrows[RuntimeException] { Scanner.scan(source) }

  it should "fail on single whitespace source" in:
    // Given
    val source = " "

    // Expecting
    assertThrows[RuntimeException] { Scanner.scan(source) }

  it should "fail on whitespace-only source" in:
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

  it should "scan parentheses with a string inside" in:
    // Given
    val source = "(\"foo\")"

    // When
    val lexemes = Scanner.scan(source)

    // Then
    val expected = Seq("(", "\"foo\"", ")")

    assert(lexemes == expected)

  it should "scan strings with an operator between them" in:
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
