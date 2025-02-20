package com.kriyantechzone.hiltandroidapps.network.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDetails(
    val login: String,
    val avatar_url: String,
    val id: Int,
    val name: String,
    val twitter_username: String
) : Parcelable
