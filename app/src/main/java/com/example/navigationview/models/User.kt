package com.example.navigationview.models

import java.io.Serializable

class User (
    val id: Int, val name: String, val password: String, val role: String, val img: String
) : Serializable {

}