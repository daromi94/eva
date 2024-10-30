package com.daromi.eva.core

import com.daromi.eva.core.expressions.Identifier

import scala.collection.mutable

type Value = Any

final class Environment private (
    private val bindings: mutable.Map[Identifier, Value] = mutable.Map.empty,
    private val parent: Option[Environment] = None
):
  def get(identifier: Identifier): Option[Value] = locate(identifier).flatMap { _.bindings.get(identifier) }

  def put(identifier: Identifier, value: Value): Unit = this.bindings.put(identifier, value)

  def contains(identifier: Identifier): Boolean = this.bindings.contains(identifier)

  def locate(identifier: Identifier): Option[Environment] =
    if contains(identifier) then
      Some(this)
    else
      this.parent.flatMap { _.locate(identifier) }

object Environment:
  def empty: Environment = Environment()

  def childOf(parent: Environment): Environment = Environment(parent = Some(parent))
