package com.example.testkmm.di

import com.example.testkmm.Platform
import org.kodein.di.*
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object MultiplatformSDK {
    internal val di: DirectDI
    get() = requireNotNull(_di)
    private var _di: DirectDI? = null

    fun init(configuration: Configuration){
        val configurationModule = DI.Module(
            name = "configurationModule",
            init = {
                bind<Configuration>() with singleton {configuration}
            }
        )

        if(_di != null){
            _di = null
        }

        val direct = DI{
            importAll(configurationModule)
        }.direct

        _di = direct
    }
}

data class Configuration(val platform: Platform)