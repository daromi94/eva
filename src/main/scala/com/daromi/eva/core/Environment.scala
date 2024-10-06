package com.daromi.eva.core

import com.daromi.eva.core.expressions.Identifier

import scala.collection.mutable

final class Environment private (
    private val record: mutable.Map[Identifier, Any] = mutable.Map.empty,
    private val parent: Option[Environment] = None
):
  def assign(identifier: Identifier, value: Any): Unit =
    this.record.update(identifier, value)

  def lookup(identifier: Identifier): Option[Any] =
    this.record.get(identifier) orElse this.parent.flatMap(_.lookup(identifier))

object Environment:
  def empty: Environment = Environment()