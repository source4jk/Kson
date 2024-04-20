package source4jk.json

import java.util.function.Consumer

fun ksonOf(buildAction: KsonBuilder.() -> Unit): Kson {
    val builder = KsonBuilder()
    builder.buildAction()
    return builder.build()
}

class Kson(private val map: MutableMap<String, Any?>): MutableIterable<Any?> {

    fun asString(key: String) = this.map[key] as? String
    fun asNumber(key: String) = this.map[key] as? Number
    fun asKson(key: String) = this.map[key] as? Kson
    fun asKsonArray(key: String) = this.map[key] as? KsonArray

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



