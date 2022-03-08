package aliabbas.com.userrepositories.shared.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


/**
 * Created By Ali Abbas on on 30,December,2021
 * This Class is used for
 *
 */
class Util {

    companion object {

        public fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            val activeNetworkInfo: NetworkInfo? = connectivityManager!!.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

        /* fun isNetworkAvailableRealTime(context: Context) {
             val connectivityManager =
                 context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                 connectivityManager.registerDefaultNetworkCallback(networkCallback)
             } else {
                 val request: NetworkRequest = NetworkRequest.Builder()
                     .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build()
                 connectivityManager.registerNetworkCallback(request, networkCallback)
             }
         }

         private var networkCallback: ConnectivityManager.NetworkCallback =
             object : ConnectivityManager.NetworkCallback() {
                 override fun onAvailable(network: Network) {
                     Log.i("Network Call", "Network Call true")
                 }

                 override fun onLost(network: Network) {
                     Log.i("Network Call", "Network Call false")
                 }
             }*/
    }
}