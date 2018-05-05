package me.nelsoncastro.pdmparcial1

import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class Contacts : Fragment() {
    var rv: RecyclerView? = null
    var contacts: ArrayList<Contact>? = null
    var adapter: ContactsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            contacts = it.getParcelableArrayList("KEY1")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.recyclerview1_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv = getView()!!.findViewById(R.id.recycly1)
        val lManager = GridLayoutManager(this.context, 3)
        rv!!.layoutManager = lManager
        adapter = ContactsAdapter(contacts!!,this.context!!,false)
        rv!!.adapter = adapter

    }

    fun update(contactsss: ArrayList<Contact>, position: Int, fav:Boolean){
        for (i in contacts!!.indices){
            if (contacts!![i].surnom == contactsss[position].surnom){
                contacts!![i].isFavoris = fav
            }
        }
        adapter = ContactsAdapter(contacts!!,this.context!!,false)
        rv!!.adapter = adapter
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @JvmStatic val fraggy = Contacts()
        @JvmStatic val args = Bundle()

        fun newInstance(contactsss: ArrayList<Contact>):Contacts{
            args.putParcelableArrayList("KEY1",contactsss)
            fraggy.arguments = args
            return fraggy
        }
        fun getInstance(): Contacts{
            return fraggy
        }
    }

}