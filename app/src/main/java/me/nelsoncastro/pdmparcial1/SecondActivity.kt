package me.nelsoncastro.pdmparcial1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.MenuItem
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
        val imgToolbar = findViewById<ImageView>(R.id.app_bar_image)
        imgToolbar.setImageResource(contact!!.image)
        setSupportActionBar(tooly)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        colly.title = contact!!.prenom
        colly.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
        colly.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)

        val rv: RecyclerView = findViewById(R.id.recycly_contact_view)
        rv.layoutManager = LinearLayoutManager(this)

        val adapter = SecondActivityAdapter(contact!!, this)
        rv.adapter = adapter

        val butt = findViewById<FloatingActionButton>(R.id.floatty_view)
        butt.setOnClickListener { v ->
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.putExtra(Intent.EXTRA_TEXT,
            """${contact!!.prenom}
                |${contact!!.nom}
                |Numéro de téléphone ${contact!!.nombre}
                |Numéro de téléphone résidentiel ${contact!!.nombremaison}
                |Numéro de téléphone professionel ${contact!!.nombretravaille}
                |E-mail ${contact!!.but}
                |Badge d'identité ${contact!!.carte}
            """.trimMargin())
            shareIntent.type = "text/plain"
            startActivity(Intent.createChooser(shareIntent, "Share via"))


        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item!!.itemId){
            android.R.id.home -> {onBackPressed();true}
            else -> super.onOptionsItemSelected(item)
        }
    }

}