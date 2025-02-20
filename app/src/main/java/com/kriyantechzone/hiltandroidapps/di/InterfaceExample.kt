package com.kriyantechzone.hiltandroidapps.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import timber.log.Timber

import javax.inject.Qualifier

interface ExampleInterface {
    fun sayHello()
}

class ExampleInterfaceImp : ExampleInterface {
    override fun sayHello() {
        Timber.tag("Test1").d("Test Message 1")
    }
}

class ExampleInterfaceImpo : ExampleInterface {
    override fun sayHello() {
        Timber.tag("Test2").d("Test Message 2")
    }
}


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class FirstImplementation

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class SecondImplementation

@Module
@InstallIn(ActivityComponent::class)
object InterfaceExample {
    @FirstImplementation
    @Provides
    //@Named("Test")
    fun provideExampleInterface(): ExampleInterface = ExampleInterfaceImp()

    @SecondImplementation
    @Provides
   // @Named("Second")
    fun provideExampleInterface2(): ExampleInterface = ExampleInterfaceImpo()

}

