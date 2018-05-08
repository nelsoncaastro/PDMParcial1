package me.nelsoncastro.pdmparcial1

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ViewPagerAdapter(private val Contexte: Context, fm: FragmentManager, private val bungalo: Bundle, private val savedInstanceState: Bundle?) : FragmentPagerAdapter(fm) {
    //private val contacts = if(savedInstanceState==null) Contacts.newInstance(bungalo.getParcelableArrayList("KEY")) else fm.getFragment(savedInstanceState,"Contacts")
    //private val contactsFavoris = if(savedInstanceState==null) ContactsFavoris.newInstance(bungalo.getParcelableArrayList("KEY")) else fm.getFragment(savedInstanceState,"ContactsFavoris")

    private val contacts = Contacts.newInstance(bungalo.getParcelableArrayList("KEY"))
    private val contactsFavoris = ContactsFavoris.newInstance(bungalo.getParcelableArrayList("KEY"))

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> contacts
        1 -> contactsFavoris
        else -> contacts
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> Contexte.getString(R.string.tab1)
            1 -> Contexte.getString(R.string.tab2)
            else -> null
        }
    }
}