package com.devsaul.mobilearchitecture.domain

import com.devsaul.mobilearchitecture.data.QuoteRepository
import com.devsaul.mobilearchitecture.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetQuotesUseCaseTest {

    @RelaxedMockK //Define automaticamente las respuestas
    //@MockK //Se tiene que definir todas las respuestas
    private lateinit var quoteRepository: QuoteRepository

    lateinit var getQuotesUseCase: GetQuotesUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getQuotesUseCase = GetQuotesUseCase(quoteRepository)
    }

    @Test
    fun `When the api doesnt return anything then get values from database`() = runBlocking {
        //Given
        /** * Cada vez que el caso de uso reall llame a @param .getAllQuotesFromApi() se retornara una lista vacia */
        /** * Siempre que haya una corrutina se debe de poner coEvery si no every */
        coEvery { quoteRepository.getAllQuotesFromApi() } returns emptyList()

        //When
        getQuotesUseCase()

        //Then
        /** * Siempre que haya una corrutina se debe de poner coVerify si no verify */
        /** * @param exactly permite confirma que solo se haya llamado una vez dicha funcion  */
        coVerify(exactly = 1) {
            quoteRepository.getAllQuotesFromDatabase()
        }
        coVerify(exactly = 0) {
            quoteRepository.clearQuotes()
            quoteRepository.insertQuotes(any())
        }
    }

    @Test
    fun `When the api return something then get values from api`() = runBlocking {
        val list = listOf(
            Quote(
                quote = "Esto es una prueba de un test",
                author = "Saulito"
            )
        )
        //Given
        coEvery { quoteRepository.getAllQuotesFromApi() } returns list

        //When
        val response = getQuotesUseCase()

        //Then
        coVerify(exactly = 1) {
            quoteRepository.clearQuotes()
            quoteRepository.insertQuotes(any())
        }
        coVerify(exactly = 0) {
            quoteRepository.getAllQuotesFromDatabase()
        }
        assert(list == response)

    }
}