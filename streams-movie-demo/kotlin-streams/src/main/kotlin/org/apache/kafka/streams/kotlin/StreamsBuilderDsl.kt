package org.apache.kafka.streams.kotlin

import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.Topology
import org.apache.kafka.streams.kstream.KGroupedStream
import org.apache.kafka.streams.kstream.KStream
import org.apache.kafka.streams.kstream.KTable

@DslMarker
annotation class KStreamsDsl

@KStreamsDsl
inline fun createTopology(block: StreamsBuilder.() -> Unit): Topology = StreamsBuilder().also(block).build()

@KStreamsDsl
inline fun <T, U> StreamsBuilder.kstream(topic: Collection<String>, block: KStream<T, U>.() -> Unit = {}): KStream<T, U> =
        stream<T, U>(topic).also(block)

@KStreamsDsl
inline fun <K, V> KStream<K, V>.groupByKey(block: KGroupedStream<K, V>.() -> Unit): KGroupedWithParent<K, V> =
        groupByKey().also(block).withParent(this)

@KStreamsDsl
inline fun <K, V> KGroupedStream<K, V>.count(block: KTable<K, Long>.() -> Unit): KTable<K, Long> = count().also(block)