package source4jk.json

import source4jk.json.collections.MutableKsonMap

class KsonBuilder {
    private val map: MutableKsonMap = MutableKsonMap()

    infix fun String.set(value: Any?) = this@KsonBuilder.map.put(this@set, value)

    fun build(): Kson {
        return Kson(this.map)
    }
}