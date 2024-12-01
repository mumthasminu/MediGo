package com.example.MediGo.model.Local

data class Doctor(
    val availability: List<String>,
    val contact: String,
    val email: String,
    val id: Int,
    val name: String,
    val specialty: String
)