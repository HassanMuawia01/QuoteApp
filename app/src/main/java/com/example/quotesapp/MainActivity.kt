package com.example.quotesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel

    private val quotoText:TextView
        get()=findViewById(R.id.quoteText)

    private val quotoAuthor:TextView
        get() = findViewById(R.id.authorName)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel=ViewModelProvider(this, MainViewModelFactory(application)).get(MainViewModel::class.java)
        setQuote(mainViewModel.getQuote())
    }

    fun setQuote(quote:QuoteDataClass){
        quotoText.text=quote.text
        quotoAuthor.text=quote.author
    }
    fun Onshare(view: View) {
        val intent=Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().text)
        startActivity(intent)
    }
    fun NextButton(view: View) {
        setQuote(mainViewModel.nextQuote())
    }
    fun PerviousButton(view: View) {
        setQuote(mainViewModel.perviousQuote())
    }


}