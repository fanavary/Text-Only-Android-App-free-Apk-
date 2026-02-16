package com.example.lightbrowser

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val webView = WebView(this)
        setContentView(webView)

        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        // leave loadsImagesAutomatically false so rendering doesn't auto-fetch images,
        // but main blocking is via shouldInterceptRequest()
        settings.loadsImagesAutomatically = false
        settings.allowContentAccess = false
        settings.allowFileAccess = false

        webView.webViewClient = object : WebViewClient() {

            override fun shouldInterceptRequest(
                view: WebView,
                request: WebResourceRequest
            ): WebResourceResponse? {
                val url = request.url.toString().lowercase()

                // quick cheap checks
                if (url.contains("data:image") ||
                    url.endsWith(".jpg") || url.endsWith(".jpeg") || url.endsWith(".png") ||
                    url.endsWith(".gif") || url.endsWith(".webp") || url.endsWith(".bmp") ||
                    url.endsWith(".mp4") || url.endsWith(".webm") || url.endsWith(".mkv") ||
                    url.endsWith(".mp3") || url.endsWith(".aac") || url.endsWith(".ogg")
                ) {
                    return emptyResponse()
                }

                // allow the request
                return super.shouldInterceptRequest(view, request)
            }

            private fun emptyResponse(): WebResourceResponse {
                return WebResourceResponse(
                    "text/plain",
                    "utf-8",
                    "".byteInputStream()
                )
            }
        }

        webView.webChromeClient = WebChromeClient()
        webView.loadUrl("https://example.com")
    }
}
