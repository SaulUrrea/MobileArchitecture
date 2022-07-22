package com.devsaul.mobilearchitecture.core.di

import android.content.Context
import androidx.room.Room
import com.devsaul.mobilearchitecture.data.database.QuoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val QUOATE_DATABASE_NAME = "quote_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, QuoteDatabase::class.java, QUOATE_DATABASE_NAME)
            .build()

    @Singleton
    @Provides
    fun provideQuoteDao(db:QuoteDatabase) = db.getQuoteDao()

}