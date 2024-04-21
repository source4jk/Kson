package source4jk.json.collections

import source4jk.json.Karray
import source4jk.json.Kson
import java.util.AbstractMap.SimpleEntry

class MutableKsonMap: MutableIterable<MutableMap.MutableEntry<String, Any?>> {
    val entries: MutableSet<MutableMap.MutableEntry<String, Any?>> = mutableSetOf()

    val keys: MutableSet<String>
        get() = this.entries.mapTo(mutableSetOf()) { it.key }

    val values: MutableList<Any?>
        get() = this.entries.mapTo(mutableListOf()) { it.value }

    @Suppress("UNCHECKED_CAST")
    fun <T> get(key: String): T? {
        val entry = this.entries.find { it.key == key }

        return if (entry == null) {
            null
        } else {
            entry.value as? T
        }
    }

    fun put(key: String, value: Any?): Any? {
        validateType(value)
        val entry = this.entries.find { it.key == key }

        return if (entry == null) {
            this.entries.add(SimpleEntry(key, value))
            value
        } else {
            this.entries.remove(entry)
            this.entries.add(SimpleEntry(key, value))
            value
        }
    }

    fun remove(key: String): Any? {
        val iterator = this.iterator()

        while (iterator.hasNext()) {
            val entry = iterator.next()
            if (entry.key == key) {
                iterator.remove()
                return entry.value
            }
        }
        return null
    }

    override fun iterator(): MutableIterator<MutableMap.MutableEntry<String, Any?>> {
        return this.entries.iterator()
    }

    internal companion object Static {
        private fun validateType(value: Any?): Boolean {
            return when (value) {
                is String -> true
                is Number -> true
                is Boolean -> true
                is Kson -> true
                is Karray -> true
                null -> true
                else -> throw IllegalArgumentException("this data type is not valid!")
            }
        }
    }
}