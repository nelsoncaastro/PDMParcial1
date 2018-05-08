package me.nelsoncastro.pdmparcial1

import android.os.Parcel
import android.os.Parcelable

data class Contact(var prenom: String,var nom: String, var nombre: String, var nombremaison: String, var nombretravaille: String, var image: Int, var but: String?, var carte: String, var isFavoris: Boolean) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(prenom)
        parcel.writeString(nom)
        parcel.writeString(nombre)
        parcel.writeString(nombremaison)
        parcel.writeString(nombretravaille)
        parcel.writeInt(image)
        parcel.writeString(but)
        parcel.writeString(carte)
        parcel.writeByte(if (isFavoris) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Contact> {
        override fun createFromParcel(parcel: Parcel): Contact {
            return Contact(parcel)
        }

        override fun newArray(size: Int): Array<Contact?> {
            return arrayOfNulls(size)
        }
    }

}