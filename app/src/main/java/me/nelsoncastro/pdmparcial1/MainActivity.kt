package me.nelsoncastro.pdmparcial1

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import java.io.File

class MainActivity : AppCompatActivity() {

    var contacts : ArrayList<Contact>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bungalo = Bundle()
        bungalo.putParcelableArrayList("KEY", contacts)

        val tabbything = findViewById<TabLayout>(R.id.tabby)
        val viewy = findViewById<ViewPager>(R.id.paggy)
        val adapter = ViewPagerAdapter(this, supportFragmentManager, bungalo)

        viewy.adapter = adapter
        tabbything.setupWithViewPager(viewy)
    }

    fun getContacts(){
        val resolver = contentResolver
        val curry = resolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null)
        if (curry.count > 0){
            while (curry.moveToNext()){
                val id = curry.getString(curry.getColumnIndex(ContactsContract.Contacts._ID))
                val name = curry.getString(curry.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val image = curry.getString(curry.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI))
                if(curry.getInt(curry.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0){
                    val pcurry = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID+" =?", arrayOf(id), null)
                    if (pcurry.count > 0){
                        while (pcurry.moveToNext()){
                            val numerotelephone = pcurry.getString(pcurry.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                            val imageBytes = pcurry.getBlob(pcurry.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI))
                            val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, image.length)
                            val file: String = baseContext.cacheDir.path + "/temp_" + id
                            val FileTemp = File(file)
                            try {

                            } catch (e: FileSystemException) {
                                e.printStackTrace()
                            }
                        }
                    }
                }


            }
        }
    }
}
