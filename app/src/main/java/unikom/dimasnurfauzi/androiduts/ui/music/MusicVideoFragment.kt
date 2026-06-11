package unikom.dimasnurfauzi.androiduts.ui.music

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import unikom.dimasnurfauzi.androiduts.R
import unikom.dimasnurfauzi.androiduts.adapter.MusicAdapter
import unikom.dimasnurfauzi.androiduts.data.database.AppDatabase
import unikom.dimasnurfauzi.androiduts.databinding.FragmentMusicBinding

class MusicVideoFragment : Fragment() {

    private var _binding: FragmentMusicBinding? = null
    private val binding get() = _binding!!
    private lateinit var musicAdapter: MusicAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMusicBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupYoutubeWebView()

        musicAdapter = MusicAdapter(emptyList())
        binding.rvMusic.adapter = musicAdapter
        loadData()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupYoutubeWebView() {
        val youtubeId = getString(R.string.embed_youtube_id)

        binding.webviewYoutube.apply {
            settings.javaScriptEnabled = true
            settings.mediaPlaybackRequiresUserGesture = false
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
            setBackgroundColor(Color.BLACK)

            val html = """
                <!DOCTYPE html>
                <html>
                <head>
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <style>
                    * { margin: 0; padding: 0; }
                    iframe { display: block; border: none; border-radius: 12px; }
                </style>
                </head>
                <body style="background:#000;">
                <iframe
                    src="https://www.youtube.com/embed/$youtubeId"
                    width="100%" height="100%"
                    frameborder="0"
                    allowfullscreen
                    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture">
                </iframe>
                </body>
                </html>
            """.trimIndent()
            loadDataWithBaseURL("https://www.youtube.com", html, "text/html", "UTF-8", null)
        }
    }

    private fun loadData() {
        val db = AppDatabase.getInstance(requireContext())
        val dao = db.appDao()

        lifecycleScope.launch {
            dao.getAllMusic().collect { music ->
                musicAdapter.updateData(music)
            }
        }
    }

    override fun onDestroyView() {
        binding.webviewYoutube.destroy()
        super.onDestroyView()
        _binding = null
    }
}
