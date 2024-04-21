package source4jk.json

import source4jk.json.collections.MutableKsonMap

class KsonBuilder {
    private val map: MutableKsonMap = MutableKsonMap()

    infix fun String.set(value: String?) = this@KsonBuilder.map.put(this@set, value)
    infix fun String.set(value: Number?) = this@KsonBuilder.map.put(this@set, value)
    infix fun String.set(value: Kson?) = this@KsonBuilder.map.put(this@set, value)
    infix fun String.set(value: Karray?) = this@KsonBuilder.map.put(this@set, value)

    fun build(): Kson {
        return Kson(this.map)
    }
}

fun main() {
}