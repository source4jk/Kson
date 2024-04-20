package cord.json

import java.util.function.Consumer

fun ksonArrayOf(vararg values: Any?): KsonArray {
    return KsonArray(values.toMutableList())
}

class KsonArray(private val list: MutableList<Any?>): MutableIterable<Any?> {

    @Suppress("UNCHECKED_CAST")
    fun <T> get(index: Int): T? {
        return this.list.getOrNull(index) as? T
    }

    fun add(value: Any?, index: Int = this.list.lastIndex + 1): KsonArray {
        this.list.add(index, value)
        return this
    }

    fun remove(index: Int): KsonArray {
        this.list.removeAt(index)
        return this
    }

    fun remove(value: Any?): KsonArray {
        this.list.remove(value)
        return this
    }

    override fun forEach(action: Consumer<in Any?>?) {
        super.forEach(action)
    }

    override fun iterator(): MutableIterator<Any?> {
        return this.list.listIterator()
    }

}

