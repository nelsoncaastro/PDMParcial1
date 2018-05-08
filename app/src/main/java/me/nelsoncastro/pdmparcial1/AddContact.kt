package me.nelsoncastro.pdmparcial1

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText

class AddContact : AppCompatActivity(){

    var contact: Contact? = null
    var contacts  = ArrayList<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_addcontact)

        val receiveBundle = this.intent.extras
        contacts = receiveBundle.getParcelableArrayList("KEEEEY")

        val tooly = findViewById<Toolbar>(R.id.toolbarry)
        setSupportActionBar(tooly)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        val first = findViewById<EditText>(R.id.first_edit)
        val last = findViewById<EditText>(R.id.last_edit)
        var phone = findViewById<EditText>(R.id.phone_edit)
        val home = findViewById<EditText>(R.id.home_edit)
        val work = findViewById<EditText>(R.id.work_edit)
        val email = findViewById<EditText>(R.id.email_edit)
        val id = findViewById<EditText>(R.id.badge_edit)
        val butt = findViewById<Button>(R.id.button)





        butt.setOnClickListener { v ->
            contact = Contact(
                    first.text.toString(),
                    last.text.toString(),
                    phone.text.toString(),
                    home.text.toString(),
                    work.text.toString(),
                    R.drawable.defaulty,
                    email.text.toString(),
                    id.text.toString(),
                    false
            )
            contacts.add(contact!!)
            val sendBundle = Bundle()
            sendBundle.putParcelableArrayList("KEEEY",contacts)
            val i = Intent(this, MainActivity::class.java)
            i.putExtras(sendBundle)
            startActivity(i)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item!!.itemId){
            android.R.id.home -> {onBackPressed();true}
            else -> super.onOptionsItemSelected(item)
        }
    }
}