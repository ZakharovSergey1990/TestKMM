package com.example.testkmm.di

import com.example.testkmm.KMMContext
import com.example.testkmm.Platform
import com.example.testkmm.api.TestHttpApi
import com.example.testkmm.api.TestHttpApiImpl
import com.example.testkmm.data.DriverFactory
import com.example.testkmm.data.UserDataSource
import com.example.testkmm.data.UserDataSourceImpl
import com.example.testkmm.repository.UserRepository
import com.example.testkmm.repository.UserRepositoryImpl
import org.kodein.di.*
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object MultiplatformSDK {
    internal val di: DirectDI
    get() = requireNotNull(_di)
    private var _di: DirectDI? = null

    fun init(context: KMMContext){
        print("init MultiplatformSDK")
        val configurationModule = DI.Module(
            name = "configurationModule",
            init = {
              //  bind<Configuration>() with singleton {configuration}
                bind <DriverFactory>() with singleton { DriverFactory(context) }
                bind <UserDataSource>() with singleton { UserDataSourceImpl(instance()) }
                bind <TestHttpApi>() with singleton { TestHttpApiImpl() }
                bind <UserRepository>() with singleton { UserRepositoryImpl(instance(), instance()) }
            }
        )

        if(_di != null){
            _di = null
        }

        val direct = DI{
            importAll(configurationModule)
        }.direct
        print("init MultiplatformSDK direct = ")
        _di = direct
    }
}

val MultiplatformSDK.userRepository: UserRepository
get() = MultiplatformSDK.di.instance()

//data class Configuration(val configurationType: ConfigurationType)
//
//sealed class ConfigurationType{
//    data class iOS(val version: String):ConfigurationType()
//    data class Android(val version: String, val context: Context)
//}