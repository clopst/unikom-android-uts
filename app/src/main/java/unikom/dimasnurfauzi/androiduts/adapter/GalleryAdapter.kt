package unikom.dimasnurfauzi.androiduts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import unikom.dimasnurfauzi.androiduts.data.entity.GalleryItem
import unikom.dimasnurfauzi.androiduts.databinding.ItemGalleryBinding

class GalleryAdapter(
    private var items: List<GalleryItem>
) : RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemGalleryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGalleryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val context = holder.binding.root.context

        val imageResId = context.resources.getIdentifier(
            item.imageRes, "drawable", context.packageName
        )
        holder.binding.imgGallery.setImageResource(imageResId)
    }

    override fun getItemCount(): Int = items.size

    fun updateData(newItems: List<GalleryItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}
