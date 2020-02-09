package com.example.infrastructure.feature.notes.model


import com.google.gson.annotations.SerializedName

data class NoteDto(

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String
)