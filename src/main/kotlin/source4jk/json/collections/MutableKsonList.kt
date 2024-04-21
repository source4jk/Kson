package source4jk.json.collections

import source4jk.json.Karray
import source4jk.json.Kson

class MutableKsonList(): MutableIterable<Any> {
    constructor(list: MutableList<Any>): this() {
        this.values.addAll(filter(list))
    }

    constructor(vararg values: Any): this() {
        this.values.addAll(filter(values.toMutableList()))
    }

    internal val values: MutableList<Any> = mutableListOf()

    @Suppress("UNCHECKED_CAST")
    fun <T> get(index: Int): T? {
        if (index in this.values.indices) {
            return this.values[index] as? T
        }
        return null
    }

    fun add(index: Int, value: Any) {
        validateType(value)
        if (index in this.values.indices) {
            this.values.add(index, value)
        } else {
            this.values.add(value)
        }
    }

    fun add(value: Any) {
        validateType(value)
        this.values.add(value)
    }

    fun removeAt(index: Int): Any? {
        if (index in this.values.indices) {
            return this.values.removeAt(index)
        }
        return null
    }

    fun remove(value: Any): Boolean {
        return this.values.remove(value)
    }

    override fun iterator(): MutableIterator<Any> {
        return this.values.iterator()
    }

    fun MutableList<Any>.toMutableKsonList(): MutableKsonList {
        return MutableKsonList(this)
    }

    internal companion object Static {
        private fun filter(list: MutableList<Any>): MutableList<Any> {
            return list.filter { validateType(it) }.toMutableList()
        }

        private fun validateType(value: Any): Boolean {
            return when (value) {
                is String -> true
                is Number -> true
                is Boolean -> true
                is Kson -> true
                is Karray -> true
                else -> throw IllegalArgumentException("this data type is not valid!")
            }
        }
    }
}