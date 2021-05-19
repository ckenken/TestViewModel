package com.kotklin.ckenken.testviewmodel

data class MainData(var _id: Int, var content: String) {
    fun print()= "$_id：$content"
}