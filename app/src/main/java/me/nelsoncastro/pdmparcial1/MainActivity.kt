package me.nelsoncastro.pdmparcial1

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.ContactsContract
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TabLayout
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import java.io.File
import java.io.FileOutputStream
import java.security.Permissions

class MainActivity : AppCompatActivity() {

    var contacts : ArrayList<Contact> = ArrayList()
    val MY_PERMISSIONS_REQUEST_READ_CONTACTS = 100
    val lazyContacts:ArrayList<Contact> by lazy {
        requestContacts()
        ArrayList<Contact>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contacts.addAll(lazyContacts)

        contacts = if(savedInstanceState != null){
            savedInstanceState.getParcelableArrayList("CLE")
        }else {
            ArrayList<Contact>().apply {
                add(Contact("Nelson", "Castro", 77400000, 0, 0, R.drawable.earth, null, 43516, false))
                add(Contact("Sam", "O'nella", 74190000, 0, 0, R.drawable.twd, null, 0, false))
                add(Contact("Phil", "DeFranco", 71300000, 0, 0 , R.drawable.got, null, 0, false))
                add(Contact("Adele", "Whoknows", 71300000, 0, 0, R.drawable.bbad, null, 0, false))
                add(Contact("Jessi", "Marthel", 71300000, 0, 0, R.drawable.himym, null, 0, false))
                add(Contact("Nami", "No", 71300000, 0, 0, R.drawable.favs, null, 0, false))
            }
        }

        val bungalo = Bundle()
        bungalo.putParcelableArrayList("KEY", contacts)

        val tabbything = findViewById<TabLayout>(R.id.tabby)
        val viewy = findViewById<ViewPager>(R.id.paggy)
        val adapter = ViewPagerAdapter(this, supportFragmentManager, bungalo)

        viewy.adapter = adapter
        tabbything.setupWithViewPager(viewy)

        val butty = findViewById<FloatingActionButton>(R.id.floatty)
        butty.setOnClickListener { v ->
            val i = Intent(this, AddContact::class.java)
            startActivity(i)
        }
    }

    fun getContacts(){
        val resolver = contentResolver
        val curry = resolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null)
        if (curry.count > 0){
            while (curry.moveToNext()){
                val id = curry.getString(curry.getColumnIndex(ContactsContract.Contacts._ID))
                val name = curry.getString(curry.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                var photouri = curry.getString(curry.getColumnIndex(ContactsContract.Contacts.PHOTO_URI))
                val thumbnailuri = curry.getString(curry.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO_ID))
                var telefonos = ArrayList<String>()
                var email = ""
                var numerotelephone  = ""
                photouri = thumbnailuri ?: photouri
                if(curry.getInt(curry.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0){
                    val pcurry = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID+" =?", arrayOf(id), null)
                    if (pcurry.count > 0){
                        while (pcurry.moveToNext()){
                            telefonos.add(pcurry.getString(pcurry.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)))
                        }
                    }
                    pcurry.close()
                }
                val ecurry = resolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID+" = ?", arrayOf(id), null)
                while (ecurry.moveToNext()) {
                    email = ecurry.getString(ecurry.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA))
                }
                ecurry.close()

                lazyContacts.add(Contact(name, "",telefonos[0].toInt(),0,0,photouri.toInt(),email,0,false))
            }
            curry.close()
        }
    }

    fun requestContacts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_CONTACTS)){

            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS),MY_PERMISSIONS_REQUEST_READ_CONTACTS)
            }
        } else{

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_READ_CONTACTS -> {
                if ((grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED)) getContacts()
                else {

                }
                return
            }
            else -> {

            }

        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        //outState!!.putParcelableArray("CLE",s.getArray().)
        super.onSaveInstanceState(outState)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private val s = Contacts.getInstance()
        @SuppressLint("StaticFieldLeak")
        private val sf = ContactsFavoris.getInstance()
    }
}
