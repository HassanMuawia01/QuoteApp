package com.example.quotesapp

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context: Context):ViewModel() {
    private var quoteList: Array<QuoteDataClass> = emptyArray()
    private var index=0

    init {
        quoteList=loadQuoteFromAssets()
    }

    private fun loadQuoteFromAssets(): Array<QuoteDataClass> {
        val inputStream = context.assets.open("quotes.json")
        val size:Int = inputStream.available()
        //ye buffer mein save kary ga
        val buffer = ByteArray(size)
        //json ko read karny k liye
        inputStream.read(buffer)
        inputStream.close()
        //json ka format hamesa UTF_8 ka hota h
        val json=String(buffer, Charsets.UTF_8)
        val gson=Gson()
        return gson.fromJson(json,Array<QuoteDataClass>::class.java)
    }

    fun getQuote()=quoteList[index]

    fun nextQuote()=quoteList[++index]

    fun perviousQuote()=quoteList[--index]
}