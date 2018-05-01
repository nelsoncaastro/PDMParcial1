package me.nelsoncastro.pdmparcial1

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ViewPagerAdapter(private val Contexte: Context, fm: FragmentManager, private val bungalo: Bundle) : FragmentPagerAdapter(fm) {
    private val serie: Contacts
    private val favSerie: ContactsFavoris

    init {
        //this.serie = RecyclerViewFragment1.newInstance(bungalo.getParcelableArrayList<Serie>("KEY"))
        //this.favSerie = RecyclerViewFragment2.newInstance(bungalo.getParcelableArrayList<Serie>("KEY"))
    }

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> serie
        1 -> favSerie
        else -> serie
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