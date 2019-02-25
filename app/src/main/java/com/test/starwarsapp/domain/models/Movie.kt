package com.test.starwarsapp.domain.models

import android.os.Parcel
import android.os.Parcelable
import com.test.starwarsapp.utils.dateToString
import com.test.starwarsapp.utils.stringToDate
import java.util.*

data class Movie(
        val title: String,
        val releaseDate: Date,
        val director: String,
        val producer: String,
        val episodeId: Int,
        val openingCrawl: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            stringToDate(parcel.readString()!!),
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readInt(),
            parcel.readString()!!)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(dateToString(releaseDate))
        parcel.writeString(director)
        parcel.writeString(producer)
        parcel.writeInt(episodeId)
        parcel.writeString(openingCrawl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}