package com.danielecampogiani.underlinepageindicator

import android.os.Parcel
import android.os.Parcelable

data class Item(val name: String) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString().orEmpty())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item = Item(parcel)

        override fun newArray(size: Int): Array<Item?> = arrayOfNulls(size)
    }
}