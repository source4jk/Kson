package source4jk.json

import source4jk.json.collections.MutableKsonList

fun karrayOf(vararg values: Any): Karray {
    return Karray(MutableKsonList.fromList(values.toMutableList()))
}

class Karray(private val list: MutableKsonList): MutableIterable<Any?> {

    val values: List<Any>
        get() = this.list.values.toList()


    fun removeAt(index: Int) = this.list.removeAt(index)

    fun remove(value: String) = this.list.remove(value)
    fun remove(value: Number) = this.list.remove(value)
    fun remove(value: Kson) = this.list.remove(value)
    fun remove(value: Karray) = this.list.remove(value)

    override fun iterator(): MutableIterator<Any?> {
        return this.list.iterator()
    }

}