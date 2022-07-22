package com.devsaul.mobilearchitecture.domain

import com.devsaul.mobilearchitecture.data.QuoteRepository
import com.devsaul.mobilearchitecture.data.database.entities.toDatabase
import com.devsaul.mobilearchitecture.data.model.QuoteModel
import com.devsaul.mobilearchitecture.domain.model.Quote
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(
    private val repository: QuoteRepository
) {

    suspend operator fun invoke(): List<Quote> {
        val quotes = repository.getAllQuotesFromApi()

        return if (quotes.isNotEmpty()) {
            repository.clearQuotes()
            repository.insertQuotes(quotes.map { it.toDatabase() })
            quotes
        } else {
            repository.getAllQuotesFromDatabase()
        }
    }
}