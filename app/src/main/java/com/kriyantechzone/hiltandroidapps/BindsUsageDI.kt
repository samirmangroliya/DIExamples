package com.kriyantechzone.hiltandroidapps

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Singleton

class BindsUsageDI {
    interface Logger {
        fun log(message: String)
    }

    class FileLogger @Inject constructor() : Logger {
        override fun log(message: String) {
            // Implementation for logging to a file
        }
    }

    class DatabaseLogger @Inject constructor() : Logger {
        override fun log(message: String) {
            // Implementation for logging to a database
        }
    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class FileLoggerQualifier

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class LoggerModule {
        @Binds
        @FileLoggerQualifier
        abstract fun bindLogger(fileLogger: FileLogger): Logger
    }
}