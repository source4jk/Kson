package cord.json

class KsonBuilder {
    private val map: MutableMap<String, Any?> = mutableMapOf()

    infix fun String.set(value: String?) = this@KsonBuilder.putToMap(this@set, value)
    infix fun String.set(value: Number?) = this@KsonBuilder.putToMap(this@set, value)
    infix fun String.set(value: Kson?) = this@KsonBuilder.putToMap(this@set, value)
    infix fun String.set(value: KsonArray?) = this@KsonBuilder.putToMap(this@set, value)

    private fun putToMap(key: String, value: Any?) {
        this.map[key] = value
    }

    fun build(): Kson {
        return Kson(this.map)
    }
}