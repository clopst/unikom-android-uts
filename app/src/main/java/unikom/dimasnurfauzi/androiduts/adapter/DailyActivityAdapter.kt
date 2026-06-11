package unikom.dimasnurfauzi.androiduts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import unikom.dimasnurfauzi.androiduts.data.entity.DailyActivity
import unikom.dimasnurfauzi.androiduts.databinding.ItemDailyActivityBinding

class DailyActivityAdapter(
    private var items: List<DailyActivity>,
    private val onItemClick: (DailyActivity) -> Unit = {}
) : RecyclerView.Adapter<DailyActivityAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemDailyActivityBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDailyActivityBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val context = holder.binding.root.context
        holder.binding.tvTitle.text = item.title
        holder.binding.tvDescription.text = item.description

        val imageResId = context.resources.getIdentifier(
            item.imageRes, "drawable", context.packageName
        )
        holder.binding.imgActivity.setImageResource(imageResId)

        holder.binding.root.setOnClickListener { onItemClick(item) }
    }

    override fun getItemCount(): Int = items.size

    fun updateData(newItems: List<DailyActivity>) {
        items = newItems
        notifyDataSetChanged()
    }
}
