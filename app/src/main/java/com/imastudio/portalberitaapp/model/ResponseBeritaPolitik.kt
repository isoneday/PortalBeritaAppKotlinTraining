package com.imastudio.portalberitaapp.model

import com.google.gson.annotations.SerializedName


data class ResponseBeritaPolitik(

	@field:SerializedName("totalResults")
	val totalResults: Int? = null,

	@field:SerializedName("articles")
	val articles1: List<ArticlesItem?>? = null,

	@field:SerializedName("status")
	val status1: String? = null
)