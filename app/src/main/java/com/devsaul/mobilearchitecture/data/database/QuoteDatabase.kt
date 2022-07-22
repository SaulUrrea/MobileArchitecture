package com.devsaul.mobilearchitecture.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devsaul.mobilearchitecture.data.database.dao.QuoteDao
import com.devsaul.mobilearchitecture.data.database.entities.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1, exportSchema = false)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun getQuoteDao(): QuoteDao
}