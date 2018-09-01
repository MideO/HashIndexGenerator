package com.github.mideo.hashindex

class RandomGeneratorSpec extends HashIndexTableSpec {

  it should "generate random number from any value" in {
    RandomGenerator.generateNumber("abcs").isInstanceOf[BigInt] should be(true)
  }

  it should "generate the same number for same argument always" in {
    RandomGenerator.generateNumber("abcs") should equal(RandomGenerator.generateNumber("abcs"))
  }

}
