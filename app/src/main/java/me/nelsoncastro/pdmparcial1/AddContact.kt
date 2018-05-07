package me.nelsoncastro.pdmparcial1

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Button
import android.widget.EditText

class AddContact : AppCompatActivity(){

    var contact: Contact? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_third_addcontact)

        val tooly = findViewById<Toolbar>(R.id.toolbarry)
        setSupportActionBar(tooly)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        val first = findViewById<EditText>(R.id.first_edit)
        val last = findViewById<EditText>(R.id.last_edit)
        val phone = findViewById<EditText>(R.id.phone_edit)
        val home = findViewById<EditText>(R.id.home_edit)
        val work = findViewById<EditText>(R.id.work_edit)
        val email = findViewById<EditText>(R.id.email_edit)
        val id = findViewById<EditText>(R.id.badge_edit)
        val butt = findViewById<Button>(R.id.button)

        contact = Contact(
                first.text.toString(),
                last.text.toString(),
                phone.text.toString().toInt(),
                home.text.toString().toInt(),
                work.text.toString().toInt(),
                R.drawable.himym,
                email.text.toString(),
                id.text.toString().toInt(),
                false
        )

        butt.setOnClickListener { v ->
            val sendBundle = Bundle()
            sendBundle.putParcelable("KEEEY",contact)
            val i = Intent(this, MainActivity::class.java)
            i.putExtras(sendBundle)
            startActivity(i)
        }
    }
}