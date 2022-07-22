package com.devsaul.mobilearchitecture.domain

import com.devsaul.mobilearchitecture.data.QuoteRepository
import com.devsaul.mobilearchitecture.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetRandomQuoteUseCaseTest {
    @RelaxedMockK
    private lateinit var quoteRepository: QuoteRepository

    lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getRandomQuoteUseCase = GetRandomQuoteUseCase(quoteRepository)
    }

    @Test
    fun `When the database doesnt return anything then fun return null`() = runBlocking {
        //Given
        coEvery { quoteRepository.getAllQuotesFromDatabase() } returns null

        //When
        val response = getRandomQuoteUseCase()

        //Then
        assert(response == null)
    }

    @Test
    fun `When database is not empy return quoate`() = runBlocking {
        //Given
        val list = listOf(Quote(author = "Saulito", quote = "Prueba"))
        coEvery { quoteRepository.getAllQuotesFromDatabase() } returns list

        val response = getRandomQuoteUseCase()

        assert(response == list.first())
    }
}