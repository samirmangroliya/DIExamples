package com.kriyantechzone.hiltandroidapps.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import timber.log.Timber
import javax.inject.Qualifier

interface Employee {
    fun employeeType()
}

class Developer: Employee {
    override fun employeeType() {
        Timber.tag("Test").d("Developer is Called...%s", this.hashCode())

    }
}

class Manager : Employee {
    override fun employeeType() {
        Timber.tag("Test").d("Manager is Called...%s", this.hashCode())
    }
}


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Dev

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Management

@Module
@InstallIn(ActivityComponent::class)
object InterfaceExample {
    @Dev
    @Provides
    // @Named("First")
    fun provideDeveloper(): Employee = Developer()

    @Management
    @Provides
    // @Named("Second")
    fun provideManager(): Employee = Manager()
}

