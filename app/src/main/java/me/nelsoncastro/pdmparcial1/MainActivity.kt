package me.nelsoncastro.pdmparcial1

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.provider.ContactsContract
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TabLayout
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.util.Log
import android.support.v4.app.FragmentManager
import java.util.*

class MainActivity : AppCompatActivity() {

    var contacts : ArrayList<Contact> = ArrayList()
    val MY_PERMISSIONS_REQUEST_READ_CONTACTS = 100
    val lazyContacts = ArrayList<Contact>()

    var firsttime = true

    val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val receiveBundle = this.intent.extras

        if(contacts.size == 0 && firsttime){
            requestContacts()
            firsttime = false
        }

        Log.i("main", "Creating VIEW")



        if (savedInstanceState == null){
            contacts = ArrayList<Contact>().apply {
                add(Contact("Nelson", "Castro", "77400000", "0", "0", R.drawable.man, "00046516@uca.edu.sv", "00043516", false))
                add(Contact("Sam", "O'nella", "77400000", "0", "0", R.drawable.man2, "00046516@uca.edu.sv", "0", false))
                add(Contact("Phil", "DeFranco", "77400000", "0", "0" , R.drawable.man3, "00046516@uca.edu.sv", "0", false))
                add(Contact("Adele", "Whoknows", "77400000", "0", "0", R.drawable.woman, "00046516@uca.edu.sv", "0", false))
                add(Contact("Jessi", "Marthel", "77400000", "25300000", "27270000", R.drawable.himym, "00046516@uca.edu.sv", "00043516", false))
                add(Contact("Nami", "No", "77400000", "0", "0", R.drawable.woman2, "00046516@uca.edu.sv", "0", false))
            }

            contacts.addAll(lazyContacts)
        } else{
            contacts =  savedInstanceState.getParcelableArrayList("CLE")
        }

        if (receiveBundle != null) contacts = receiveBundle.getParcelableArrayList("KEEEY")

        val bungalo = Bundle()
        bungalo.putParcelableArrayList("KEY", contacts)

        val tabbything = findViewById<TabLayout>(R.id.tabby)
        val viewy = findViewById<ViewPager>(R.id.paggy)
        val adapter = ViewPagerAdapter(this, fragmentManager, bungalo, savedInstanceState)

        viewy.adapter = adapter
        tabbything.setupWithViewPager(viewy)

        val butty = findViewById<FloatingActionButton>(R.id.floatty)
        butty.setOnClickListener { v ->
            val sendBundle = Bundle()
            sendBundle.putParcelableArrayList("KEEEEY",contacts)
            val intent = Intent(this.baseContext, AddContact::class.java)
            intent.putExtras(sendBundle)
            startActivity(intent)
            finish()
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
                when(telefonos.size){
                    0 -> {}
                    1 -> {
                        //if (email)
                        lazyContacts.add(Contact(name, " ",telefonos[0],"0","0",R.drawable.defaulty,email,"00043516",false))
                    }
                    2 -> {
                        lazyContacts.add(Contact(name, " ",telefonos[0],telefonos[1],"0",R.drawable.defaulty,email,"00043516",false))
                    }
                    3-> {
                        lazyContacts.add(Contact(name, " ",telefonos[0],telefonos[1],telefonos[2],R.drawable.defaulty,email,"00043516",false))
                    }
                }
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
            getContacts()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_READ_CONTACTS -> {
                if ((grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED)) requestContacts()
                else {

                }
                return
            }
            else -> {

            }

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList("CLE",s.getArray())
        Log.i("Main Activity", "Saving state")
        super.onSaveInstanceState(outState)
        //fragmentManager.putFragment(outState,"Contacts", s)
        //fragmentManager.putFragment(outState, "ContactsFavoris", sf)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private val s = Contacts.getInstance()
        @SuppressLint("StaticFieldLeak")
        private val sf = ContactsFavoris.getInstance()
    }
}
