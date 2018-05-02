package me.nelsoncastro.pdmparcial1

import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recyclerview2_main.*

@SuppressLint("ValidFragment", "StaticFieldLeak")
object ContactsFavoris: Fragment() {
    private var contacts: ArrayList<Contact>? = null
    private var favcontacts: ArrayList<Contact>? = null
    private var rv: RecyclerView? = null
    private var adapter: ContactsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            contacts = it.getParcelableArrayList("KEY2")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.recyclerview2_main,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favcontacts = prepareFavSeries(contacts)

        rv = getView()!!.findViewById(R.id.recycly2)
        val lManager = GridLayoutManager(this.context, 3)
        rv!!.layoutManager = lManager
        adapter = ContactsAdapter(favcontacts!!,this.context!!,true)
        rv!!.adapter = adapter


    }

    fun prepareFavSeries(contacts:ArrayList<Contact>?): ArrayList<Contact> {
        val favcontacts = ArrayList<Contact>()
        for (i in contacts!!.indices) if(contacts[i].isFavoris){
            favcontacts.add(Contact(contacts[i].surnom,contacts[i].nombre,contacts[i].image,contacts[i].but,contacts[i].carte,contacts[i].isFavoris))
        }
        return favcontacts
    }

    fun update(contactsss: ArrayList<Contact>){
        favcontacts = prepareFavSeries(contactsss)
        adapter = ContactsAdapter(favcontacts!!,this.context!!,true)
        rv!!.adapter = adapter
    }

    fun  newInstance(contactss: ArrayList<Contact>)=
            ContactsFavoris.apply {
                arguments = Bundle().apply {
                    putParcelableArrayList("KEY2",contactss)
                }
            }
}