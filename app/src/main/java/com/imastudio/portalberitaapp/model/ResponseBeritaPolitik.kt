package com.imastudio.portalberitaapp.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class ResponseBeritaPolitik(

	@field:SerializedName("totalResults")
	val totalResults: Int? = null,

	@field:SerializedName("articles")
	val articles1: List<ArticlesItem?>? = null,

	@field:SerializedName("status")
	val status1: String? = null
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.createTypedArrayList(ArticlesItem),
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeValue(totalResults)
		parcel.writeTypedList(articles1)
		parcel.writeString(status1)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<ResponseBeritaPolitik> {
		override fun createFromParcel(parcel: Parcel): ResponseBeritaPolitik {
			return ResponseBeritaPolitik(parcel)
		}

		override fun newArray(size: Int): Array<ResponseBeritaPolitik?> {
			return arrayOfNulls(size)
		}
	}
}