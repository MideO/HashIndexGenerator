package com.github.mideo.hashtable.sharding

import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.prop.TableDrivenPropertyChecks

trait  HashIndexTableSpec
  extends FlatSpec
    with Matchers
    with TableDrivenPropertyChecks