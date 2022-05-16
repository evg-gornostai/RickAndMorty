package com.gornostai.rickandmorty.data.datasources

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import javax.inject.Inject

class IsOnlineDataSourceImpl @Inject constructor(
    private val application: Application
): IsOnlineDataSource {

    override fun isOnline(): Boolean {
        var result = false
        val connectivityManager =
            application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                    ?: return false
            result = when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            result = when (networkInfo.type) {
                ConnectivityManager.TYPE_WIFI -> true
                ConnectivityManager.TYPE_MOBILE -> true
                ConnectivityManager.TYPE_ETHERNET -> true
                else -> false
            }
        }
        return result
    }
}