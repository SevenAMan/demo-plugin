package org.qldmj.runconfiguration

import com.intellij.execution.RunManager
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.execution.remote.RemoteConfiguration
import com.intellij.execution.remote.RemoteConfigurationType
import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project

object RunConfigurationUtils {

    fun addRemoteRunConfiguration(module: Module, name: String, host: String, port: String) {
        val project = module.project
        val runManager = RunManager.getInstance(project)
        val existConfiguration = runManager.findConfigurationByName(name)
        if (existConfiguration != null) {
            val configuration = existConfiguration.configuration
            if (configuration is RemoteConfiguration) {
                configuration.PORT = port
                configuration.HOST = host
                runManager.selectedConfiguration = existConfiguration
            }
            return
        }
        val factory = MyRemoteConfigurationFactory(module, name, host, port)
        val settings = runManager.createConfiguration(name, factory)
        runManager.addConfiguration(settings)
        runManager.selectedConfiguration = settings
    }

}

private class MyRemoteConfigurationFactory(
    private val module: Module,
    private val name: String,
    private val host: String,
    private val port: String
) :
    ConfigurationFactory(RemoteConfigurationType.getInstance()) {
    override fun createTemplateConfiguration(project: Project): RunConfiguration {
        val configuration = RemoteConfiguration(project, this)
        configuration.HOST = host
        configuration.PORT = port
        configuration.setModule(module)
        return configuration
    }

    override fun getId() = name

    override fun getName() = name
}