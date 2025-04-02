package com.socialseller.clothcrew.models.address.get

data class Attributes(
    val addressLine1: String,
    val addressLine2: String?,
    val city: String,
    val area:String,
    val country: String,
    val createdAt: String,
    val houseNumber: String,
    val name: String,
    val phone: String,
    val email: String,
    val pincode: String,
    val state: String,
    val updatedAt: String
)