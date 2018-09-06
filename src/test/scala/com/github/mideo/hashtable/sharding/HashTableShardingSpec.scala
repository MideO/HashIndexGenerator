package com.github.mideo.hashtable.sharding

import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.prop.TableDrivenPropertyChecks

trait HashTableShardingSpec
  extends FlatSpec
    with Matchers
    with TableDrivenPropertyChecks