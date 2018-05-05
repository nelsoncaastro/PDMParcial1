package me.nelsoncastro.pdmparcial1

import android.content.Context
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView

class SecondActivity : AppCompatActivity() {

    var contact: Contact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_contact_view)

        val receiveBundle = this.intent.extras
        contact = receiveBundle.getParcelable("KEEY")

        val tooly = findViewById<Toolbar>(R.id.toolbar)
        val colly = findViewById<CollapsingToolbarLayout>(R.id.collapsingtoolbar)
        val imgToolbar = findViewById<ImageView>(R.id.imagy_single)
        //imgToolbar.setImageResource(R.drawable.earth)
        setSupportActionBar(tooly)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        colly.title = contact!!.surnom
        colly.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
        colly.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)

        val rv: RecyclerView = findViewById(R.id.recycly_contact_view)
        rv.layoutManager = LinearLayoutManager(this)

        val adapter = SecondActivityAdapter(contact!!)
        rv.adapter = adapter

    }



}