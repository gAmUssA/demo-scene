package org.apache.kafka.streams.kotlin

import org.apache.kafka.streams.kstream.KGroupedStream
import org.apache.kafka.streams.kstream.KStream

data class KGroupedWithParent<K, V>(val grouped: KGroupedStream<K, V>, val parent: KStream<K, V>) :
        KGroupedStream<K, V> by grouped, KStream<K, V> by parent

fun <K, V> KGroupedStream<K, V>.withParent(parent: KStream<K, V>) = KGroupedWithParent(this, parent)