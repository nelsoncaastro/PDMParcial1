package me.nelsoncastro.pdmparcial1

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recyclerview2_main.*

class ContactsFavoris: Fragment() {
    var contacts: ArrayList<Contact>? = null
    var favcontacts: ArrayList<Contact> = ArrayList()
    var rv: RecyclerView? = null
    var adapter: ContactsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contacts = if (savedInstanceState != null) savedInstanceState.getParcelableArrayList("KEEEEY") else ArrayList()
        arguments?.let {
            contacts = it.getParcelableArrayList("KEY2")
        }
        favcontacts = prepareFavSeries(contacts)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.recyclerview2_main,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv = getView()!!.findViewById(R.id.recycly2)
        val lManager = GridLayoutManager(this.context, 3)
        rv!!.layoutManager = lManager
        adapter = ContactsAdapter(favcontacts,this.context!!,true)
        rv!!.adapter = adapter


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("State status", "Fragment 2 onSaveInstance")
        outState.putParcelableArrayList("KEEEEY",contacts)
    }

    fun prepareFavSeries(contacts:ArrayList<Contact>?): ArrayList<Contact> {
        val favcontacts = ArrayList<Contact>()
        for (i in contacts!!.indices) if(contacts[i].isFavoris) favcontacts.add(Contact(
                contacts[i].prenom,
                contacts[i].nom,
                contacts[i].nombre,
                contacts[i].nombremaison,
                contacts[i].nombretravaille,
                contacts[i].image,
                contacts[i].but,
                contacts[i].carte,
                contacts[i].isFavoris
        ))
        return favcontacts
    }

    fun update(contactsss: ArrayList<Contact>){
        favcontacts = prepareFavSeries(contactsss)
        adapter = ContactsAdapter(favcontacts,this.context!!,true)
        rv!!.adapter = adapter
    }

    fun calculateNoOfColumns(contexte: Context): Int{
        val dislpayMetrics: DisplayMetrics = contexte.resources.displayMetrics
        val dpWidth: Float = dislpayMetrics.widthPixels / dislpayMetrics.density
        val noOfColumns: Int = (dpWidth/180).toInt()
        return noOfColumns
    }

    fun getArray(): ArrayList<Contact>{
        return favcontacts
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @JvmStatic val fraggy = ContactsFavoris()
        @JvmStatic val args = Bundle()

        fun newInstance(contactsss: ArrayList<Contact>):ContactsFavoris{
            args.putParcelableArrayList("KEY2",contactsss)
            fraggy.arguments = args
            return fraggy
        }
        fun getInstance(): ContactsFavoris{
            return fraggy
        }
    }
}