package com.github.mideo.hashtable.sharding

import com.github.mideo.hashtable.sharding.exceptions.{InvalidCoordinateException, OutOfRangeException}

class DataTableSpec extends HashIndexTableSpec {

  it should "not permit init with invalid coordinates x" in {

    the[InvalidCoordinateException] thrownBy {
      DataTable(Dimension(-1, 10))
    } should have message "Invalid Coordinates"

  }

  it should "not permit init with invalid coordinates y" in {

    the[InvalidCoordinateException] thrownBy {
      DataTable(Dimension(10, 0))
    } should have message "Invalid Coordinates"

  }

  it should "put and get entry" in {
    val table: DataTable[Int] = DataTable(Dimension(3, 10))

    val coordinate = table.put(15, ColumnSize(3))
    table.get(coordinate) should equal(15)
  }

  it should "should not allow insert in out of rage column" in {
    val table: DataTable[Int] = DataTable(Dimension(3, 10))

    the[OutOfRangeException] thrownBy {
      table.put(15, ColumnSize(4))
    } should have message "Coordinate of range"
  }


  it should "provide list of free Coordinates" in {
    val table: DataTable[Int] = DataTable(Dimension(3, 10))

    val free:List[Coordinate] = table.freeCoordinates()

    free.size should equal(3*10)
  }

  it should "provide list of free Coordinates when all cells are occupied " in {
    val table: DataTable[Int] = DataTable(Dimension(2, 2))
    table.put(15, ColumnSize(1))
    table.put(15, ColumnSize(1))
    table.put(15, ColumnSize(2))
    table.put(15, ColumnSize(2))

    val free:List[Coordinate] = table.freeCoordinates()

    free.size should equal(0)
  }

  it should "provide list of free Coordinates when some cells are occupied " in {
    val table: DataTable[Int] = DataTable(Dimension(3, 10))
    table.put(15, ColumnSize(1))
    table.put(15, ColumnSize(1))
    table.put(15, ColumnSize(2))
    table.put(15, ColumnSize(2))
    val free:List[Coordinate] = table.freeCoordinates()

    free.size should equal((3*10) - 4)
  }

}
