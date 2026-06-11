package unikom.dimasnurfauzi.androiduts.ui.daily

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import unikom.dimasnurfauzi.androiduts.adapter.DailyActivityAdapter
import unikom.dimasnurfauzi.androiduts.adapter.FriendAdapter
import unikom.dimasnurfauzi.androiduts.data.database.AppDatabase
import unikom.dimasnurfauzi.androiduts.databinding.FragmentDailyBinding

class DailyFragment : Fragment() {

    private var _binding: FragmentDailyBinding? = null
    private val binding get() = _binding!!
    private lateinit var dailyAdapter: DailyActivityAdapter
    private lateinit var friendAdapter: FriendAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDailyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dailyAdapter = DailyActivityAdapter(emptyList())
        friendAdapter = FriendAdapter(emptyList())

        binding.rvDailyActivity.adapter = dailyAdapter
        binding.rvFriends.adapter = friendAdapter

        loadData()
    }

    private fun loadData() {
        val db = AppDatabase.getInstance(requireContext())
        val dao = db.appDao()

        lifecycleScope.launch {
            dao.getAllDailyActivities().collect { activities ->
                dailyAdapter.updateData(activities)
            }
        }

        lifecycleScope.launch {
            dao.getAllFriends().collect { friends ->
                friendAdapter.updateData(friends)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
