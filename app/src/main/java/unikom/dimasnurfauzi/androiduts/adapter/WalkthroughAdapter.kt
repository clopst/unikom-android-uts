package unikom.dimasnurfauzi.androiduts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import unikom.dimasnurfauzi.androiduts.databinding.SlideWalkthroughBinding

data class WalkthroughItem(
    val imageRes: Int,
    val title: String,
    val description: String
)

class WalkthroughAdapter(
    private val items: List<WalkthroughItem>
) : RecyclerView.Adapter<WalkthroughAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: SlideWalkthroughBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SlideWalkthroughBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.imgWalkthrough.setImageResource(item.imageRes)
        holder.binding.tvTitle.text = item.title
        holder.binding.tvDesc.text = item.description
    }

    override fun getItemCount(): Int = items.size
}
