package unikom.dimasnurfauzi.androiduts.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "music")
data class Music(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val artist: String,
    @ColumnInfo(name = "image_res")
    val imageRes: String,
    @ColumnInfo(name = "spotify_id")
    val spotifyId: String
)
