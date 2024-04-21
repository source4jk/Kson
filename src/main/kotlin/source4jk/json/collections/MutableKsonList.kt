package source4jk.json.collections

import source4jk.json.Karray
import source4jk.json.Kson

class MutableKsonList private constructor(list: MutableList<Any>): MutableIterable<Any> {

    val values: MutableList<Any> = mutableListOf()

    init {
        values.addAll(list)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> get(index: Int): T? {
        val entry = this.values[index]

        return entry as? T
    }

    fun add(index: Int, value: String) = this._add(index, value)
    fun add(value: String) = this._add(this.values.lastIndex - 1, value)

    fun add(index: Int, value: Number) = this._add(index, value)
    fun add(value: Number) = this._add(this.values.lastIndex - 1, value)

    fun add(index: Int, value: Kson) = this._add(index, value)
    fun add(value: Kson) = this._add(this.values.lastIndex - 1, value)

    fun add(index: Int, value: Karray) = this._add(index, value)
    fun add(value: Karray) = this._add(this.values.lastIndex - 1, value)


    fun _add(index: Int, value: Any): Any {
        this.values.add(index, value)
        return value
    }

    fun removeAt(index: Int): Any? {
        val entry = this.values[index]

        return run {
            this.values.remove(entry)
            entry
        }
    }

    fun remove(value: String) = this._remove(value)
    fun remove(value: Number) = this._remove(value)
    fun remove(value: Kson) = this._remove(value)
    fun remove(value: Karray) = this._remove(value)

    private fun _remove(value: Any): Any? {
        val entry = this.values.find { it == value }

        return if (entry == null) {
            null
        } else {
            this.values.remove(entry)
            entry
        }
    }

    override fun iterator(): MutableIterator<Any> {
        return this.values.iterator()
    }

    companion object Static {
        fun fromList(list: MutableList<Any>): MutableKsonList {
            return MutableKsonList(
                list.filter {
                    it is String || it is Number || it is Kson || it is Karray
                }.toMutableList()
            )
        }
    }

}