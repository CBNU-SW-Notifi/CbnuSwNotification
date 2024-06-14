import platform.Foundation.NSURL
import platform.UIKit.UIApplication

class OpenUrliOS : OpenUrl {
    override fun open(url: String) {
        val nsUrl = NSURL.URLWithString(url)
        nsUrl?.let { UIApplication.sharedApplication.openURL(it) }
    }
}

actual fun getOpenUrl(context: Any): OpenUrl = OpenUrliOS()