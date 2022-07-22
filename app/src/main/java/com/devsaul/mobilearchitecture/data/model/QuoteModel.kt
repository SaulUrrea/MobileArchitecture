package com.devsaul.mobilearchitecture.data.model

import com.google.gson.annotations.SerializedName

//Modelo de respuesta del API si no se llaman igual los datos
// es requerido hacer el @SerializadName nombrando la variable
// con su nombre de la respuesta por el back
data class QuoteModel(
    @SerializedName("quote") val quote: String,
    @SerializedName("author") val author: String
)
