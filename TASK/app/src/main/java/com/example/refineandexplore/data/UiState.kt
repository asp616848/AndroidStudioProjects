package com.example.refineandexplore.data

data class UiState(
    val userName: String = "Abhijeet Shashwat",
    val address: String = "Chandigarh, Punjab, India",
    var expanded : Boolean = false,
    private var currentPage : Int = 0

){
    fun getPage(): Int{
        return currentPage
    }
}
