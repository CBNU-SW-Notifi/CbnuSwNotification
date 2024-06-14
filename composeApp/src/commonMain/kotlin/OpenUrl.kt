interface OpenUrl {
    fun open(url: String)
}

expect fun getOpenUrl(context: Any): OpenUrl