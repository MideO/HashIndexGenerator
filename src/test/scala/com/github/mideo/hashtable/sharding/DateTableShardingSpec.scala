package com.github.mideo.hashtable.sharding

import java.util.UUID

class  DateTableShardingSpec extends HashTableShardingSpec {

  it should "shard data across dataTable" in {

    DateTableSharding[String](DataTable(Dimension(2, 5)))
      .shard((1 to 10)
        map { _ => UUID.randomUUID().toString} toList:_*)
      .isInstanceOf[DataTable[String]] should be(true)
  }



  case class DateTableSharding[T](dataTable: DataTable[T])
    extends Sharding
      with HashFunction
      with MaxSizeCollision {
    def shard(t:T*): DataTable[T] = {
      dataTable
    }

  }

}
