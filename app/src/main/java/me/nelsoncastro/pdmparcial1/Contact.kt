package me.nelsoncastro.pdmparcial1

import android.os.Parcel
import android.os.Parcelable

data class Contact(var surnom: String, var nombre: Int, var image: Int, var but: String?, var carte: Int, var isFavoris: Boolean): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readByte() != 0.toByte()) {}

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(surnom)
        parcel.writeInt(nombre)
        parcel.writeInt(image)
        parcel.writeString(but)
        parcel.writeInt(carte)
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