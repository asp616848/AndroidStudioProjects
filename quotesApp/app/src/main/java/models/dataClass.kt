package models

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.example.quotesapp.pages
import com.google.gson.Gson

data class dataClass( val text : String, val author : String)

object DataManager{

    var data = emptyArray<dataClass>()
    var currentPage = mutableStateOf(pages.LIST)
    var currentQuote : dataClass? = null

    val isDataLoaded = mutableStateOf(false)
    fun loadAssetsFromFile(context : Context){

        val inputStream = context.assets.open("quotes.txt")
//        val inputString = inputStream.bufferedReader().use { it.readText() }
//        val quotes = inputString.split("\n")
//        for (quote in quotes){
//            val quoteParts = quote.split(";")
//            val quoteText = quoteParts[0]
//            val quoteAuthor = quoteParts[1]
//            data += dataClass(quoteText, quoteAuthor)
//        }
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        data = gson.fromJson(json, Array<dataClass>::class.java)
        isDataLoaded.value = true
    }
    fun switchPage(quote : dataClass){
        if (currentPage.value == pages.LIST){
            currentQuote = quote
            currentPage.value = pages.DETAIL
        }
        else{
            currentPage.value = pages.LIST
        }
    }
}