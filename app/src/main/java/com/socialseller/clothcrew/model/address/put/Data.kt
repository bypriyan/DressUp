package com.socialseller.clothcrew.models.address.put

data class Data(
    val name: String?=null,
    val phone: String?=null,
    val email: String?=null,
    val houseNumber: String?=null,
    val addressLine1: String?=null,
    val addressLine2: String?=null,
    val pincode: String?=null,
    val city: String?=null,
    val area: String?=null,
    val state: String?=null,
    val country: String?=null,
)