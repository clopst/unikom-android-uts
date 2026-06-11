package unikom.dimasnurfauzi.androiduts.ui.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import unikom.dimasnurfauzi.androiduts.R
import unikom.dimasnurfauzi.androiduts.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:${getString(R.string.email_address)}")
            }
            startActivity(intent)
        }

        binding.btnInstagram.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(getString(R.string.instagram_url))
            }
            startActivity(intent)
        }

        binding.btnGithub.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(getString(R.string.github_url))
            }
            startActivity(intent)
        }

        binding.btnLinkedin.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(getString(R.string.linkedin_url))
            }
            startActivity(intent)
        }

        binding.btnFindMe.setOnClickListener {
            // Try Google Maps app first, fallback to browser
            val geoUri = Uri.parse(getString(R.string.gmaps_geo))
            val mapsIntent = Intent(Intent.ACTION_VIEW, geoUri).apply {
                setPackage("com.google.android.apps.maps")
            }

            if (mapsIntent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(mapsIntent)
            } else {
                val webUri = Uri.parse(getString(R.string.gmaps_web))
                startActivity(Intent(Intent.ACTION_VIEW, webUri))
            }
        }

        binding.btnAbout.setOnClickListener {
            showAboutDialog()
        }
    }

    private fun showAboutDialog() {
        val dialogView = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_about, null)

        AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setPositiveButton(R.string.about_close) { dialog, _ -> dialog.dismiss() }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
