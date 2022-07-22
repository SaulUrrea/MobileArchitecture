package com.devsaul.mobilearchitecture.domain

import com.devsaul.mobilearchitecture.data.QuoteRepository
import com.devsaul.mobilearchitecture.data.model.QuoteModel
import com.devsaul.mobilearchitecture.domain.model.Quote
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(
    private val repository: QuoteRepository
) {

    suspend operator fun invoke(): Quote? {
        val quotes =
            repository.getAllQuotesFromDatabase()
        if (!quotes.isEmpty()) {
            val randomNumber = (quotes.indices).random()
            return quotes[randomNumber]
        }
        return null
    }
}