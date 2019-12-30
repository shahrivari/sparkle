package io.github.shahrivari.sparkle

import com.google.gson.Gson
import com.google.gson.GsonBuilder

private var sparkleGson = GsonBuilder().disableHtmlEscaping().create()

fun setUpSparkleGson(gson: Gson) {
    sparkleGson = gson
}

fun getSparkleGson() = sparkleGson

fun Any?.toJson(): String = sparkleGson.toJson(this)