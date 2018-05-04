package me.nelsoncastro.pdmparcial1

import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

class SecondActivity : AppCompatActivity() {

    var contact: Contact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tooly = findViewById<Toolbar>(R.id.toolbar)
        val colly = findViewById<CollapsingToolbarLayout>(R.id.collapsingtoolbar)
        colly.title = contact!!.surnom


    }

}