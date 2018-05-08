package me.nelsoncastro.pdmparcial1

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import java.util.jar.Manifest

class SecondActivityAdapter(private val contact: Contact, private val contexte: Context): RecyclerView.Adapter<SecondActivityAdapter.SingleViewHolder>() {

    var contactInfo  = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_second_contact_single,parent,false)
        contactInfo= crearArrayList(contact)
        return SingleViewHolder(v)
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onBindViewHolder(holder: SingleViewHolder, position: Int) {
        contactInfo= crearArrayList(contact)


        when(position){
            6 -> {//holder.imgSrc.setImageResource(R.drawable.star_full)
                //holder.imgSrc.maxWidth = 50
                //holder.imgSrc.maxHeight = 50
                //holder.text1.text = contactInfo!![position]
                //holder.text2.text = "Prenom"
            }
            0 -> {//holder.imgSrc.setImageResource(R.drawable.star_full)
                holder.imgSrc.setImageResource(R.drawable.ic_account_circle_black_24dp)
                holder.text1.text = contactInfo[position]
                holder.text2.text = "Prénom" }
            1 -> {
                if (contactInfo[position] != "0") {
                    holder.imgSrc.setImageResource(R.drawable.ic_phone_forwarded_black_24dp)
                    holder.imgSrc.setOnClickListener { v ->
                        if (ContextCompat.checkSelfPermission(contexte, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(contexte as Activity, arrayOf(android.Manifest.permission.CALL_PHONE), 225)
                        } else {
                            val callIntent = Intent(Intent.ACTION_CALL, Uri.fromParts("tel", holder.text1.text.toString(), null))
                            contexte.startActivity(callIntent)
                        }
                    }
                } else holder.imgSrc.setImageResource(R.drawable.ic_local_phone_black_24dp)
                holder.text1.text = if (contactInfo!![position] != "0") contactInfo!![position] else "Il n'y a pas existé"
                holder.text2.text = "Numero de telephone" }
            2 -> {
                if (contactInfo[position] != "0") {
                    holder.imgSrc.setImageResource(R.drawable.ic_phone_forwarded_black_24dp)
                    holder.imgSrc.setOnClickListener { v ->
                        if (ContextCompat.checkSelfPermission(contexte, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(contexte as Activity, arrayOf(android.Manifest.permission.CALL_PHONE), 225)
                        } else {
                            val callIntent = Intent(Intent.ACTION_CALL, Uri.fromParts("tel", holder.text1.text.toString(), null))
                            contexte.startActivity(callIntent)
                        }
                    }
                } else holder.imgSrc.setImageResource(R.drawable.ic_local_phone_black_24dp)
                holder.text1.text = if (contactInfo[position] != "0") contactInfo[position] else "Il n'y a pas existé"
                holder.text2.text = "Numero aux maison" }
            3 -> {
                if (contactInfo[position] != "0") {
                    holder.imgSrc.setImageResource(R.drawable.ic_phone_forwarded_black_24dp)
                    holder.imgSrc.setOnClickListener { v ->
                        if (ContextCompat.checkSelfPermission(contexte, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(contexte as Activity, arrayOf(android.Manifest.permission.CALL_PHONE), 225)
                        } else {
                            val callIntent = Intent(Intent.ACTION_CALL, Uri.fromParts("tel", holder.text1.text.toString(), null))
                            contexte.startActivity(callIntent)
                        }
                    }
                } else holder.imgSrc.setImageResource(R.drawable.ic_local_phone_black_24dp)
                holder.text1.text = if (contactInfo[position] != "0") contactInfo[position] else "Il n'y a pas existé"
                holder.text2.text = "Numero aux travaille" }
            4 -> {
                holder.imgSrc.setImageResource(R.drawable.ic_email_black_24dp)
                holder.text1.text = contactInfo[position]
                holder.text2.text = "Email" }
            5 -> {
                holder.imgSrc.setImageResource(R.drawable.ic_perm_contact_calendar_black_24dp)
                holder.text1.text = contactInfo[position]
                holder.text2.text = "Carte aux Universite"
            }


        }
    }

    fun crearArrayList(contact: Contact): ArrayList<String>{
        var auxi = ArrayList<String>().apply {
            add("${contact.prenom} ${contact.nom}")
            add(contact.nombre)
            //if(contact.nombremaison!=0) add(contact.nombremaison.toString())
            add(contact.nombremaison.toString())
            //if(contact.nombretravaille!=0) add(contact.nombretravaille.toString())
            //add(contact.image.toString())
            add(contact.nombretravaille)
            add(contact.but!!)
            add(contact.carte)
        }

        return auxi
    }

    class SingleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        internal var card : CardView = itemView.findViewById(R.id.card_view2)
        internal var text1 : TextView = itemView.findViewById(R.id.texty1_single)
        internal var  text2 : TextView = itemView.findViewById(R.id.texty2_single)
        internal var imgSrc : ImageView = itemView.findViewById(R.id.imagy_single)
    }
}