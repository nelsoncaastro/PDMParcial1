package me.nelsoncastro.pdmparcial1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabbything = findViewById<TabLayout>(R.id.tabby)
        val viewy = findViewById<ViewPager>(R.id.paggy)


        tabbything.setupWithViewPager(viewy)
    }
}
