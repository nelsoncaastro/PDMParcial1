package me.nelsoncastro.pdmparcial1

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class SecondActivityAdapter(contact: Contact): RecyclerView.Adapter<SecondActivityAdapter.SingleViewHolder>() {

    var contactInfo : ArrayList<Contact>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_second_contact_single,parent,false)
        return SingleViewHolder(v)
    }

    override fun getItemCount(): Int {
        return contactInfo!!.size
    }

    override fun onBindViewHolder(holder: SingleViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    class SingleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        internal var card : CardView = itemView.findViewById(R.id.card_view2)
        internal var name : TextView = itemView.findViewById(R.id.name)
        internal var img : ImageView = itemView.findViewById(R.id.img)
        internal var  butt : ImageView = itemView.findViewById(R.id.butt)
    }
}