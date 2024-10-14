package com.daromi.eva.interpreter

import com.daromi.eva.interpreter.expressions.Identifier

import scala.collection.mutable

final class Environment private (
    private val bindings: mutable.Map[Identifier, Any] = mutable.Map.empty,
    private val parent: Option[Environment] = None
):
  def exists(identifier: Identifier): Boolean =
    this.bindings.contains(identifier)

  def resolve(identifier: Identifier): Option[Environment] =
    if exists(identifier) then Some(this)
    else this.parent.flatMap(_.resolve(identifier))

  def set(identifier: Identifier, value: Any): Unit =
    this.bindings.update(identifier, value)

  def get(identifier: Identifier): Option[Any] =
    resolve(identifier).flatMap(_.bindings.get(identifier))

object Environment:
  def empty: Environment = Environment()

  def childOf(parent: Environment): Environment = Environment(parent = Some(parent))
