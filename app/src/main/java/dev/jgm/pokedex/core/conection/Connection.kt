package dev.jgm.pokedex.core.conection

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build

class Connection {
    companion object{
        fun isOnline(context: Context): Boolean {
            val connectivityManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

            if(Build.VERSION.SDK_INT < 29) {
                val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
                return (activeNetwork?.isConnectedOrConnecting == true)
            } else {
                val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null) {
                    when {
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
                    }
                }
            }
            return false
        }
    }
}