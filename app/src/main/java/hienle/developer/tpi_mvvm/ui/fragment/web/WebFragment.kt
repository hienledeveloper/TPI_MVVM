package hienle.developer.tpi_mvvm.ui.fragment.web

import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.core.os.bundleOf
import hienle.developer.tpi_mvvm.R
import hienle.developer.tpi_mvvm.databinding.FragmentWebBinding
import hienle.developer.tpi_mvvm.ui.component.BaseBindingFragment

/**
 * Created by Hien on 5/10/24
 */
class WebFragment : BaseBindingFragment<FragmentWebBinding>() {

    companion object {
        private const val URL = "web_url"
        fun newInstance(url: String) = WebFragment().apply {
            arguments = bundleOf(URL to url)
        }
    }

    override fun getLayoutRes(): Int = R.layout.fragment_web

    override fun onViewCreated() {
        with(binding) {
            ibClose.setOnClickListener {
                activity?.onBackPressed()
            }
            webView.settings.javaScriptEnabled = true
            webView.webViewClient = MyWebViewClient()
            webView.webChromeClient = MyWebChromeClient()
            arguments?.getString(URL)?.let { url ->
                webView.loadUrl(url)
            }
        }
    }

    private class MyWebViewClient : WebViewClient()
    private class MyWebChromeClient : WebChromeClient()
}