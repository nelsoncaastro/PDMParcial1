package me.nelsoncastro.pdmparcial1

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ViewPagerAdapter(private val Contexte: Context, fm: FragmentManager, private val bungalo: Bundle) : FragmentPagerAdapter(fm) {
    private val contacts: Contacts = Contacts
    private val contactsFavoris: ContactsFavoris = ContactsFavoris

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