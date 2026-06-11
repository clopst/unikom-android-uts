package unikom.dimasnurfauzi.androiduts.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import unikom.dimasnurfauzi.androiduts.data.entity.Music
import unikom.dimasnurfauzi.androiduts.databinding.ItemMusicBinding

class MusicAdapter(
    private var items: List<Music>
) : RecyclerView.Adapter<MusicAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemMusicBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMusicBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val context = holder.binding.root.context
        holder.binding.tvTitle.text = item.title
        holder.binding.tvArtist.text = item.artist

        val imageResId = context.resources.getIdentifier(
            item.imageRes, "drawable", context.packageName
        )
        holder.binding.imgAlbum.setImageResource(imageResId)

        holder.binding.root.setOnClickListener {
            // Open in Spotify app, fallback to browser
            val spotifyUri = "https://open.spotify.com/track/${item.spotifyId}"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(spotifyUri))
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateData(newItems: List<Music>) {
        items = newItems
        notifyDataSetChanged()
    }
}
