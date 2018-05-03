package me.nelsoncastro.pdmparcial1

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class ContactsAdapter(private val contacts: ArrayList<Contact>, private val contexte: Context, private val favoris : Boolean) : RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.contactsview_main, parent, false)
        return ContactViewHolder(v, this.favoris)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.name.text = contacts[position].surnom
        holder.img.setImageResource(contacts[position].image)
        holder.butt.setImageResource(if (contacts[position].isFavoris) R.drawable.star_full else R.drawable.star_empty)
        holder.butt.setOnClickListener {  v ->
            Toast.makeText(v.context, contacts[position].surnom, Toast.LENGTH_SHORT).show()
            if(!favoris) {
                if (!holder.fav) {
                    contacts[position].isFavoris = true
                    holder.butt.setImageResource(R.drawable.star_full)
                    holder.fav = true
                } else {
                    contacts[position].isFavoris = false
                    holder.butt.setImageResource(R.drawable.star_empty)
                    holder.fav = false
                }

            } else {
                if (!holder.fav) {
                    contacts[position].isFavoris = true
                    holder.butt.setImageResource(R.drawable.star_full)
                    holder.fav = true
                } else {
                    contacts[position].isFavoris = false
                    holder.butt.setImageResource(R.drawable.star_empty)
                    holder.fav = false
                }
                s.update(contacts, position, holder.fav)
            }
            sf.update(contacts)
        }
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    class ContactViewHolder(itemView: View, internal  var fav: Boolean) : RecyclerView.ViewHolder(itemView){
        internal var card : CardView = itemView.findViewById(R.id.card_view)
        internal var name : TextView = itemView.findViewById(R.id.name)
        internal var img : ImageView = itemView.findViewById(R.id.img)
        internal var  butt : ImageView = itemView.findViewById(R.id.butt)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private val s = Contacts
        @SuppressLint("StaticFieldLeak")
        private val sf = ContactsFavoris
    }
}