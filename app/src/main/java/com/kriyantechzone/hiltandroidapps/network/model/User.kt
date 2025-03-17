package com.kriyantechzone.hiltandroidapps.network.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    @SerializedName("login")
    @Expose
    val login: String,

    @SerializedName("avatar_url")
    @Expose
    val avatarUrl: String,

    @SerializedName("id")
    @Expose
    val id: Int): Parcelable
