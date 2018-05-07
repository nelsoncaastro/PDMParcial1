package me.nelsoncastro.pdmparcial1

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class SecondActivityAdapter(private val contact: Contact): RecyclerView.Adapter<SecondActivityAdapter.SingleViewHolder>() {

    var contactInfo : ArrayList<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_second_contact_single,parent,false)
        return SingleViewHolder(v)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: SingleViewHolder, position: Int) {
        contactInfo= crearArrayList(contact)

        when(position){
            0 -> {//holder.imgSrc.setImageResource(R.drawable.star_full)
                holder.imgSrc.maxWidth = 50
                holder.imgSrc.maxHeight = 50
                holder.text1.text = contactInfo!![position]
                holder.text2.text = "Surnom" }
            1 -> {//holder.imgSrc.setImageResource(R.drawable.star_full)
                holder.text1.text = contactInfo!![position]
                holder.text2.text = "Numero Telephonique" }
            2 -> {//holder.imgSrc.setImageResource(R.drawable.star_full)
                holder.text1.text = contactInfo!![position]
                holder.text2.text = "Carte" }


        }
    }

    fun crearArrayList(contact: Contact): ArrayList<String>{
        var auxi = ArrayList<String>().apply {
            add(contact.prenom)
            add(contact.nombre.toString())
            //add(contact.image.toString())
            //add(contact.but!!)
            add(contact.carte.toString())
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