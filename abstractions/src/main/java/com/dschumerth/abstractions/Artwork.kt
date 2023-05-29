package com.dschumerth.abstractions

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artwork")
data class Artwork(
    @ColumnInfo("name")
    val name: String?,
    @ColumnInfo("image")
    val image: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
