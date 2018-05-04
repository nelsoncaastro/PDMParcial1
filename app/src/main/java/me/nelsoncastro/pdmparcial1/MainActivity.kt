package me.nelsoncastro.pdmparcial1

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.ContactsContract
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    var contacts : ArrayList<Contact> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contacts = ArrayList<Contact>().apply {
        add(Contact("Nelson",77400000,R.drawable.earth,null,0,false))
        add(Contact("Sam",74190000,R.drawable.twd,null,0,false))
        add(Contact("Phil",71300000,R.drawable.got,null,0,false))
        add(Contact("Adele",71300000,R.drawable.bbad,null,0,false))
        add(Contact("Jessi",71300000,R.drawable.all,null,0,false))
        add(Contact("Nami",71300000,R.drawable.favs,null,0,false))}


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
                                val fOutStream = FileOutputStream(FileTemp)
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOutStream)
                                fOutStream.flush()
                                fOutStream.close()
                            } catch (e: ExceptionInInitializerError) {
                                e.printStackTrace()
                            }
                            val imageSrc = FileTemp.path
                            contacts.add(Contact(name,numerotelephone.toInt(),imageSrc.toInt(),null,0,false))
                        }
                    }
                    pcurry.close()
                }


            }
        }
        curry.close()
    }
}
