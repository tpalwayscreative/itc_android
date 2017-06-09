package com.michaldrabik.kotlintest.utilities.extensions

import android.content.Context
import android.view.View

fun Int.dpToPx(context: Context): Int = this * context.resources.displayMetrics.density.toInt()


