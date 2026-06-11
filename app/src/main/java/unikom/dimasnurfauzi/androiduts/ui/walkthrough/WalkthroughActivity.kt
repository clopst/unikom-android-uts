package unikom.dimasnurfauzi.androiduts.ui.walkthrough

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import unikom.dimasnurfauzi.androiduts.MainActivity
import unikom.dimasnurfauzi.androiduts.R
import unikom.dimasnurfauzi.androiduts.adapter.WalkthroughAdapter
import unikom.dimasnurfauzi.androiduts.adapter.WalkthroughItem
import unikom.dimasnurfauzi.androiduts.databinding.ActivityWalkthroughBinding

class WalkthroughActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWalkthroughBinding
    private lateinit var prefs: SharedPreferences
    private val dots = mutableListOf<TextView>()
    private var currentPage = 0

    companion object {
        private const val PREFS_NAME = "app_prefs"
        private const val KEY_FIRST_LAUNCH = "is_first_launch"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWalkthroughBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        val items = listOf(
            WalkthroughItem(
                R.drawable.ic_walkthrough_1,
                getString(R.string.wt_title_1),
                getString(R.string.wt_desc_1)
            ),
            WalkthroughItem(
                R.drawable.ic_walkthrough_2,
                getString(R.string.wt_title_2),
                getString(R.string.wt_desc_2)
            ),
            WalkthroughItem(
                R.drawable.ic_walkthrough_3,
                getString(R.string.wt_title_3),
                getString(R.string.wt_desc_3)
            )
        )

        val adapter = WalkthroughAdapter(items)
        binding.viewpager.adapter = adapter

        setupDots(items.size)
        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                currentPage = position
                updateDots()
                updateButton()
            }
        })

        binding.btnAction.setOnClickListener {
            if (currentPage < items.size - 1) {
                binding.viewpager.currentItem = currentPage + 1
            } else {
                prefs.edit().putBoolean(KEY_FIRST_LAUNCH, false).apply()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

    private fun setupDots(count: Int) {
        dots.clear()
        binding.layoutDots.removeAllViews()
        for (i in 0 until count) {
            val dot = TextView(this).apply {
                text = "●"
                textSize = 16f
                setTextColor(
                    if (i == 0) ContextCompat.getColor(context, android.R.color.white)
                    else Color.parseColor("#66FFFFFF")
                )
                setPadding(8, 0, 8, 0)
            }
            dots.add(dot)
            binding.layoutDots.addView(dot)
        }
    }

    private fun updateDots() {
        for (i in dots.indices) {
            dots[i].setTextColor(
                if (i == currentPage) ContextCompat.getColor(this, android.R.color.white)
                else Color.parseColor("#66FFFFFF")
            )
        }
    }

    private fun updateButton() {
        binding.btnAction.text = if (currentPage == dots.size - 1) {
            getString(R.string.wt_finish)
        } else {
            getString(R.string.wt_next)
        }
    }
}
