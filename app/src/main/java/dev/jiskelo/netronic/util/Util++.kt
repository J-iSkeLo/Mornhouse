package dev.jiskelo.netronic.util

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.util.*

fun String.toast(context :Context) = Toast.makeText(context, this, Toast.LENGTH_SHORT).show()

@SuppressLint("SimpleDateFormat")
fun String.convertDate() :String {
    val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(this)
    return SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH).format(date)
}