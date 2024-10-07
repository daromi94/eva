package com.daromi.eva.core

import com.daromi.eva.core.expressions.Identifier

import scala.collection.mutable

final class Environment private (
    private val record: mutable.Map[Identifier, Any] = mutable.Map.empty,
    private val parent: Option[Environment] = None
):
  def set(identifier: Identifier, value: Any): Unit =
    this.record.update(identifier, value)

  def get(identifier: Identifier): Option[Any] =
    this.record.get(identifier) orElse this.parent.flatMap(_.get(identifier))

  def exists(identifier: Identifier): Boolean =
    this.record.contains(identifier)

object Environment:
  def empty: Environment = Environment()

  def withParent(parent: Environment): Environment = Environment(parent = Some(parent))
