package source4jk.json

import java.util.function.Consumer

fun ksonOf(buildAction: KsonBuilder.() -> Unit): Kson {
    val builder = KsonBuilder()
    builder.buildAction()
    return builder.build()
}

class Kson(private val map: MutableMap<String, Any?>): MutableIterable<Any?> {

    @Suppress("UNCHECKED_CAST")
    fun <T> get(key: String): T? {
        if (!this.map.containsKey(key) || this.map[key] == null) return null
        return this.map[key] as? T?
    }

    fun set(key: String, value: String?) = this.putToMap(key, value)
    fun set(key: String, value: Number?) = this.putToMap(key, value)
    fun set(key: String, value: Kson?) = this.putToMap(key, value)
    fun set(key: String, value: KsonArray?) = this.putToMap(key, value)

    private fun putToMap(key: String, value: Any?): Kson {
        this.map[key] = value
        return this
    }

    override fun forEach(action: Consumer<in Any?>?) {
        super.forEach(action)
    }

    override fun iterator(): MutableIterator<Any?> {
        return this.map.iterator()
    }

}



