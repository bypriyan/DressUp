package com.socialseller.clothcrew.models.address.post

data class AddressAddReq(
    val addressLine1: String?,
    val addressLine2: String?,
    val city: String?,
    val country: String?,
    val houseNumber: String?,
    val name: String?,
    val phone: String?,
    val email: String?,
    val area:String?,
    val pincode: String?,
    val state: String?
)