package data.network

interface NetworkHelper {
    fun registerListener(onNetworkAvailable: () -> Unit, onNetworkLost: () -> Unit)
    fun unregisterListener()
}