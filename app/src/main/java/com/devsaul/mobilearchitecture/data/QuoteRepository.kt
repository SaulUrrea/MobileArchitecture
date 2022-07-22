package com.devsaul.mobilearchitecture.data

import com.devsaul.mobilearchitecture.data.database.dao.QuoteDao
import com.devsaul.mobilearchitecture.data.database.entities.QuoteEntity
import com.devsaul.mobilearchitecture.data.network.QuoteService
import com.devsaul.mobilearchitecture.domain.model.Quote
import com.devsaul.mobilearchitecture.domain.model.toDomain
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteDao: QuoteDao
) {


    suspend fun getAllQuotesFromApi(): List<Quote> {
        val response = api.getQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun getAllQuotesFromDatabase(): List<Quote>? {
        val response = quoteDao.getAllQuote()
        return response.map { it.toDomain() }
    }

    suspend fun insertQuotes(quotes: List<QuoteEntity>) {
        quoteDao.insetAll(quotes)
    }

    suspend fun clearQuotes() {
        quoteDao.deleteAllQuotes()
    }
}