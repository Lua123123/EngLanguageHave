package com.example.englanguage.model.topic

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Topic {
    @SerializedName("success")
    @Expose
    var success: List<Success>? = null
}