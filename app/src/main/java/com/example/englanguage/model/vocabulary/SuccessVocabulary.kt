package com.example.englanguage.model.vocabulary

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "vocabulary")
class SuccessVocabulary(
    @PrimaryKey(autoGenerate = true)
    @field:Expose @field:SerializedName("id") var id: Int,
    @field:Expose @field:SerializedName("word") var word: String,
    @field:Expose @field:SerializedName("mean") var mean: String,
    @field:Expose @field:SerializedName("example") var example: String
) : Serializable {

    @SerializedName("image_path")
    @Expose
    var imagePath: String? = null

    @SerializedName("id_wordtype")
    @Expose
    var idWordtype: Int? = null

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