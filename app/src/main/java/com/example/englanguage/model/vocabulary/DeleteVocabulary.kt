package com.example.englanguage.model.vocabulary

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DeleteVocabulary {
    @SerializedName("success")
    @Expose
    var success: Int? = null
}