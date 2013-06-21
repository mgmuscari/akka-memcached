/**
 * Copyright (C) 2012 Klout Inc. <http://www.klout.com>
 */

package com.klout.akkamemcached

import akka.util.ByteString
import org.jboss.serial.io._
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable
import java.io.IOException
import java.util.Calendar
import scala.collection.JavaConversions._

/**
 * This object contains serializers that can be used by the Memcached client
 * to serialize and deserialize data.
 */
object Serialization {
}
/**
 * Helper object used by RealMemcachedClient for serialization. Requires an implicit
 * serializer within it's scope
 */
object Serializer {
    def serialize[T: Serializer](t: T): ByteString = implicitly[Serializer[T]] serialize t
    def deserialize[T: Serializer](bytes: Array[Byte]): T = implicitly[Serializer[T]] deserialize bytes
}

/**
 * You can define your own serializer by mixing in this trait
 */
trait Serializer[T] {

    /**
     * Converts an object of type T into an akka.util.ByteString for storage
     * into Memcached.
     */
    def serialize(t: T): ByteString

    /**
     * Converts a bytearray from Memcached into an object of type T
     */
    def deserialize(bytes: Array[Byte]): T
}
