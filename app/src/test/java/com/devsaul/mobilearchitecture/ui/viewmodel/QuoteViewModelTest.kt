package com.devsaul.mobilearchitecture.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.devsaul.mobilearchitecture.domain.GetQuotesUseCase
import com.devsaul.mobilearchitecture.domain.GetRandomQuoteUseCase
import com.devsaul.mobilearchitecture.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class QuoteViewModelTest {

    @RelaxedMockK
    private lateinit var getQuotesUseCase: GetQuotesUseCase

    @RelaxedMockK
    private lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    private lateinit var quoteViewModel: QuoteViewModel

    /** + Las reglas nos permiten ser más flexibles y reducir el boilerplate de nuestras clase,
    es básicamente es el mismo comportamiento que el @Before pero nos permite reutilizar el código,
    por ejemplo podríamos crear una regla para inicializar los mocks. */

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        quoteViewModel = QuoteViewModel(getQuotesUseCase, getRandomQuoteUseCase)

        /** Seteamos el hilo ya que en el viewmodel usamos viewModelScope y alli necesitamos cambiar el dispacher */
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel is created at the first time, get all quotes and set the first value`() = runTest{
        /** Para testear viewmodel se necesita agregar runTest en ves de runBlocking */
        //Given
        val quote = listOf(Quote("Holi", "saulito :)"), Quote("Esto es", "Una prueba"))
        coEvery { getQuotesUseCase() } returns quote

        //When
        quoteViewModel.onCreate()

        //Then
        assert(quoteViewModel.quoteModel.value == quote.first())
    }

    @Test
    fun `when randomQuoteUseCase return a quote set on the livedata`() = runTest {
        //Given
        val quote = Quote("Holi", "Saulito")
        coEvery { getRandomQuoteUseCase() } returns quote

        //When
        quoteViewModel.randomQuote()

        //Then
        assert(quoteViewModel.quoteModel.value == quote)
    }

    @Test
    fun `if randomQuoteUseCase return null keep the last value`() = runTest{
        //Given
        val quote = Quote("Saulito", "Saulito")
        quoteViewModel.quoteModel.value = quote
        coEvery { getRandomQuoteUseCase() } returns null

        //When
        quoteViewModel.randomQuote()

        //Then
        assert(quoteViewModel.quoteModel.value == quote)
    }




}