package com.example.networkrecyle.model

import java.io.Serializable

data class News (
    var id:Int,
    var emailId:String,
    var fName:String,
    var lName:String,
    var image:String
    ): Serializable