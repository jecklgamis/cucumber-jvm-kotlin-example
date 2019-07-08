package util

import com.google.inject.AbstractModule

internal class ExampleTestModule : AbstractModule() {
    override fun configure() {
        bind(TestContext::class.java)
    }
}
