package com.example.englanguage.model.topic

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "topics")
class Success(
    @field:Expose @field:SerializedName("name") var name: String,
    @field:Expose @field:SerializedName("soluong") var soluong: Int,
    @PrimaryKey(autoGenerate = true)
    @field:Expose @field:SerializedName("id") var id: Int
) {

    @SerializedName("user_create")
    @Expose
    var userCreate: Int? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

}