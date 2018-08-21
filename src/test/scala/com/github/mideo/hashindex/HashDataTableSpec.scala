package com.github.mideo.hashindex

class HashDataTableSpec extends HashIndexGeneratorSpec {
  it should "put and get entry" in {
    val table: HashDataTable = HashDataTable(Dimension(3, 10))

    table.put(15, Coordinates(3, 10))
    table.get(Coordinates(3, 10)) should equal(15)
  }

  it should "should not allow updating coordinate" in {
    val table: HashDataTable = HashDataTable(Dimension(3, 10))

    table.put(15, Coordinates(3, 10))

    the[CoordinateAlreadyPopulatedException] thrownBy {
      table.put(15, Coordinates(3, 10))
    } should have message "Coordinate Already Populated"
  }

}
