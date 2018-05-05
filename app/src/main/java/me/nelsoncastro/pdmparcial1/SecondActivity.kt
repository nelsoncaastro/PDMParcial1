package me.nelsoncastro.pdmparcial1

import android.content.Context
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.View

class SecondActivity : AppCompatActivity() {

    var contact: Contact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_contact_view)

        val tooly = findViewById<Toolbar>(R.id.toolbar)
        val colly = findViewById<CollapsingToolbarLayout>(R.id.collapsingtoolbar)
        colly.title = contact!!.surnom



    }

}