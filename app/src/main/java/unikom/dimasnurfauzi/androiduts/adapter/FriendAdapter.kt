package unikom.dimasnurfauzi.androiduts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import unikom.dimasnurfauzi.androiduts.data.entity.Friend
import unikom.dimasnurfauzi.androiduts.databinding.ItemFriendBinding

class FriendAdapter(
    private var items: List<Friend>
) : RecyclerView.Adapter<FriendAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemFriendBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFriendBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val context = holder.binding.root.context
        holder.binding.tvName.text = item.name

        val imageResId = context.resources.getIdentifier(
            item.imageRes, "drawable", context.packageName
        )
        holder.binding.imgFriend.setImageResource(imageResId)
    }

    override fun getItemCount(): Int = items.size

    fun updateData(newItems: List<Friend>) {
        items = newItems
        notifyDataSetChanged()
    }
}
