package com.dschumerth.api.response

import java.io.Serializable
import com.google.gson.annotations.SerializedName

data class ArtworkResponse (
    @SerializedName("data")
    val artworkList: List<ArtworkData>,
): Serializable