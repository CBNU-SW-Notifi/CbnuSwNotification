import android.content.Context
import android.content.Intent
import android.net.Uri

class OpenUrlAndroid(private val context: Context) : OpenUrl {
    override fun open(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }
}


actual fun getOpenUrl(context: Any): OpenUrl {
    return OpenUrlAndroid(context as Context)
}