package source4jk.json.collections

import source4jk.json.Kson
import source4jk.json.Karray
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

    fun put(key: String, value: String?) = this._put(key, value)
    fun put(key: String, value: Number?) = this._put(key, value)
    fun put(key: String, value: Kson?) = this._put(key, value)
    fun put(key: String, value: Karray?) = this._put(key, value)

    private fun _put(key: String, value: Any?): Any? {
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
        val entry = this.entries.find { it.key == key }

        return if (entry == null) {
            null
        } else {
            this.entries.remove(entry)
            entry.value
        }
    }

    override fun iterator(): MutableIterator<MutableMap.MutableEntry<String, Any?>> {
        return this.entries.iterator()
    }

}