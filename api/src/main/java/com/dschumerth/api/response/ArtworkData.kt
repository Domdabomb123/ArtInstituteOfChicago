package com.dschumerth.api.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ArtworkData (
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("image_id")
    val image_id: String
): Serializable