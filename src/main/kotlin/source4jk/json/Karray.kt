package source4jk.json

import source4jk.json.collections.MutableKsonList

fun karrayOf(vararg values: Any): Karray {
    return Karray(MutableKsonList(values.toMutableList()))
}

class Karray(private val list: MutableKsonList): MutableIterable<Any?> {
    val values: List<Any>
        get() = this.list.values.toList()


    fun removeAt(index: Int) = this.list.removeAt(index)

    fun remove(value: Any) = this.list.remove(value)

    override fun iterator(): MutableIterator<Any?> {
        return this.list.iterator()
    }
}