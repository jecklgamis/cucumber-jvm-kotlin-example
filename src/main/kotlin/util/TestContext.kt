package util


class TestContext {
    private val context: ThreadLocal<MutableMap<String, Any>> = ThreadLocal.withInitial { mutableMapOf<String, Any>() }

    fun put(key: String, value: Any) {
        context.get()[key] = value
    }

    operator fun get(key: String): Any? {
        return context.get()[key]
    }

}
