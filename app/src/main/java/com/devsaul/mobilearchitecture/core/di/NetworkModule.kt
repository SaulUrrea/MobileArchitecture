package com.devsaul.mobilearchitecture.core.di

import com.devsaul.mobilearchitecture.data.network.QuoteApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) //Establece el tiempo de la instancia o alcance
object NetworkModule {

    //Este modulo provee dependencias de librerias o clases que contienen interfaces
    // y que no se dejan injectar con constructor o que no podemos modificar

    @Singleton //Mantiene una unica instancia del objeto
    @Provides //Etiqueta para declarar algo que se puede proveer
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://drawsomething-59328-default-rtdb.europe-west1.firebasedatabase.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideQuoteApiClient(retrofit: Retrofit):QuoteApiClient{
        return retrofit.create(QuoteApiClient::class.java)
    }
}