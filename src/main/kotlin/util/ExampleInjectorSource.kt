package util

import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.Stage
import cucumber.api.guice.CucumberModules
import cucumber.runtime.java.guice.InjectorSource

class ExampleInjectorSource : InjectorSource {
    override fun getInjector(): Injector {
        return Guice.createInjector(Stage.PRODUCTION, CucumberModules.createScenarioModule(), ExampleTestModule())
    }
}
