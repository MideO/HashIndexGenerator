package com.github.mideo.hashtable.sharding

case class DateTableSharding[T](dimension: Dimension)
  extends Sharding
    with HashFunction
    with MaxSizeCollision {
  private val dataTable = DataTable[T](dimension)

  def shard(t: List[T]): DataTable[T] = {
    t foreach (i => {
      val number = hash(i, dimension.x)
      if (detectCollision(number, dimension.y, dataTable.internal))
        dataTable.put(i, Column(dataTable.freeCoordinates().head.x+1))
      else
        dataTable.put(i, Column(number))
    })
    dataTable
  }

}