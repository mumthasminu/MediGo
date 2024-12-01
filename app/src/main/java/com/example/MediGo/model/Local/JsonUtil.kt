package com.example.MediGo.model.Local

import android.content.Context
import java.io.InputStream
 class JsonUtil {
     fun readJsonFromAssets(context: Context, fileName: String): String {
        val inputStream: InputStream = context.assets.open(fileName)
        return inputStream.bufferedReader().use { it.readText() }
    }

}