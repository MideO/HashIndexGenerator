package com.github.mideo

import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

package object hashindex {
  trait  HashIndexTableSpec
    extends FlatSpec
      with Matchers
      with TableDrivenPropertyChecks

}