package unikom.dimasnurfauzi.androiduts.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import unikom.dimasnurfauzi.androiduts.databinding.ActivitySplashBinding
import unikom.dimasnurfauzi.androiduts.ui.walkthrough.WalkthroughActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    companion object {
        private const val SPLASH_DELAY = 2000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, WalkthroughActivity::class.java))
            finish()
        }, SPLASH_DELAY)
    }
}
