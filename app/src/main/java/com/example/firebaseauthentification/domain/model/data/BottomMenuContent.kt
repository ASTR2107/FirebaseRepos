package com.example.firebaseauthentification.domain.model.data

import androidx.annotation.DrawableRes

sealed class BottomMenuContent(
    val title: String,
    @DrawableRes val iconId: Int

)



