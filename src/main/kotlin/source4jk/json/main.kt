package source4jk.json

fun main() {
    val kson = ksonOf {
        "key1" set 123
        "key2" set "123"
        "key3" set ksonArrayOf("asdsa", "sdasd", "sdasd", null)
    }

    kson.get<KsonArray>("key3")?.add("213123")?.forEach {
        println(it)
    }
}