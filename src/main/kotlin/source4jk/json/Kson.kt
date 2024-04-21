package source4jk.json

import source4jk.json.collections.MutableKsonMap

fun ksonOf(buildAction: KsonBuilder.() -> Unit): Kson {
    val builder = KsonBuilder()
    builder.buildAction()
    return builder.build()
}

class Kson internal constructor(
    private val map: MutableKsonMap
): MutableIterable<MutableMap.MutableEntry<String, Any?>> {

    val entries: Set<Map.Entry<String, Any?>>
        get() = this.map.entries.toSet()

    val keys: Set<String>
        get() = this.map.keys.toSet()

    val values: List<Any?>
        get() = this.map.values.toList()

    fun <T> get(key: String): T? = this.map.get<T>(key)
    fun set(key: String, value: String?) = this.map.put(key, value)
    fun set(key: String, value: Number?) = this.map.put(key, value)
    fun set(key: String, value: Kson?) = this.map.put(key, value)
    fun set(key: String, value: Karray?) = this.map.put(key, value)

    override fun iterator(): MutableIterator<MutableMap.MutableEntry<String, Any?>> {
        return this.map.iterator()
    }
}



