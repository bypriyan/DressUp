package com.socialseller.clothcrew.models.address.post

data class AddressAddResponse(
    val addressLine1: String,
    val addressLine2: String,
    val city: String,
    val country: String,
    val createdAt: String,
    val houseNumber: String,
    val id: Int,
    val name: String,
    val pincode: String,
    val state: String,
    val updatedAt: String
)