package com.github.mideo.hashindex


case class RowLimitReachedException(private val message: String = "",
                                    private val cause: Throwable = None.orNull) extends Exception(message, cause)

case class OutOfRangeException(private val message: String = "",
                               private val cause: Throwable = None.orNull) extends Exception(message, cause)

case class InvalidCoordinateException(private val message: String = "",
                               private val cause: Throwable = None.orNull) extends Exception(message, cause)
