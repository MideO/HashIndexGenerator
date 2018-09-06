package com.github.mideo.hashtable

package object sharding {

  trait CollisionDetection

  trait Hashing

  trait Sharding
    extends Hashing
    with CollisionDetection

}
