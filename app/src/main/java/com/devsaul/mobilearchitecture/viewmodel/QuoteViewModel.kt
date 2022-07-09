package com.devsaul.mobilearchitecture.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devsaul.mobilearchitecture.model.QuoteModel
import com.devsaul.mobilearchitecture.model.QuoteProvider

class QuoteViewModel : ViewModel(){
    val quoteModel = MutableLiveData<QuoteModel>()

    fun randomQuote(){
        val currentQuote : QuoteModel = QuoteProvider.random()
        quoteModel.postValue(currentQuote)
    }
}