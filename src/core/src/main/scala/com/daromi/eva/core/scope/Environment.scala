package com.daromi.eva.core.scope

import com.daromi.eva.core.expression.{ Identifier, Value }

import scala.collection.mutable

final class Environment private (
    private val bindings: mutable.Map[Identifier, Value] = mutable.Map.empty,
    private val parent: Option[Environment] = None
):
  def get(identifier: Identifier): Option[Value] =
    require(!identifier.isBlank)

    locate(identifier).flatMap { _.bindings.get(identifier) }

  def put(identifier: Identifier, value: Value): Unit =
    require(!identifier.isBlank)

    bindings.put(identifier, value)

  def contains(identifier: Identifier): Boolean =
    require(!identifier.isBlank)

    bindings.contains(identifier)

  def locate(identifier: Identifier): Option[Environment] =
    require(!identifier.isBlank)

    if contains(identifier) then
      Some(this)
    else
      parent.flatMap { _.locate(identifier) }

object Environment:

  def empty: Environment = Environment()

  def childOf(parent: Environment): Environment = Environment(parent = Some(parent))
