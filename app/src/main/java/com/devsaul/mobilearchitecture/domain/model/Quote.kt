package com.devsaul.mobilearchitecture.domain.model

import com.devsaul.mobilearchitecture.data.database.entities.QuoteEntity
import com.devsaul.mobilearchitecture.data.model.QuoteModel

data class Quote(
    val quote: String,
    val author: String
)

fun QuoteModel.toDomain() = Quote(quote, author)
fun QuoteEntity.toDomain() = Quote(quote, author)
