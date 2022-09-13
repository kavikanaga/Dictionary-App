package com.cloudin.myapplication.model


import com.google.gson.annotations.SerializedName

data class NoDefinition(
    @SerializedName("message")
    val message: String,
    @SerializedName("resolution")
    val resolution: String,
    @SerializedName("title")
    val title: String,
    val WordItem: ArrayList<WordModelItem>
)