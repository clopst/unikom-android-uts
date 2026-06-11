package unikom.dimasnurfauzi.androiduts.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import unikom.dimasnurfauzi.androiduts.data.dao.AppDao
import unikom.dimasnurfauzi.androiduts.data.entity.DailyActivity
import unikom.dimasnurfauzi.androiduts.data.entity.Friend
import unikom.dimasnurfauzi.androiduts.data.entity.GalleryItem
import unikom.dimasnurfauzi.androiduts.data.entity.Music

@Database(
    entities = [
        DailyActivity::class,
        Friend::class,
        GalleryItem::class,
        Music::class
    ],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                // Delete old database to ensure fresh data on every app start
                context.applicationContext.deleteDatabase("dimas_nurfauzi_db")

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "dimas_nurfauzi_db"
                )
                    .build()
                INSTANCE = instance

                // Populate immediately after creation
                CoroutineScope(Dispatchers.IO).launch {
                    populateDatabase(instance.appDao())
                }

                instance
            }
        }

        private suspend fun populateDatabase(dao: AppDao) {
            // Delete existing data first
            dao.deleteAllMusic()
            dao.deleteAllGalleryItems()
            dao.deleteAllFriends()
            dao.deleteAllDailyActivities()

            // Pre-populate Daily Activities
            dao.insertDailyActivities(
                listOf(
                    DailyActivity(
                        title = "Bangun pagi dan olahraga ringan",
                        description = "Streching 15 menit",
                        imageRes = "ic_workout"
                    ),
                    DailyActivity(
                        title = "Bekerja",
                        description = "Menjalani pekerjaan 9 to 5",
                        imageRes = "ic_work"
                    ),
                    DailyActivity(
                        title = "Kucing",
                        description = "Mengurus kucing di rumah",
                        imageRes = "ic_cat"
                    ),
                    DailyActivity(
                        title = "Kuliah kelas karyawan",
                        description = "Mengikuti kelas malam di UNIKOM",
                        imageRes = "ic_study"
                    )
                )
            )

            // Pre-populate Friends
            dao.insertFriends(
                listOf(
                    Friend(name = "Rizky Aditya", imageRes = "ic_friend_1"),
                    Friend(name = "Siti Nurhaliza", imageRes = "ic_friend_2"),
                    Friend(name = "Budi Santoso", imageRes = "ic_friend_3"),
                    Friend(name = "Ayu Lestari", imageRes = "ic_friend_4"),
                    Friend(name = "Rama Putra", imageRes = "ic_friend_5"),
                    Friend(name = "Dewi Anggraini", imageRes = "ic_friend_6"),
                    Friend(name = "Fajar Pratama", imageRes = "ic_friend_7"),
                    Friend(name = "Nadia Safira", imageRes = "ic_friend_8")
                )
            )

            // Pre-populate Gallery Items
            dao.insertGalleryItems(
                listOf(
                    GalleryItem(title = "", imageRes = "gallery_1"),
                    GalleryItem(title = "", imageRes = "gallery_2"),
                    GalleryItem(title = "", imageRes = "gallery_3"),
                    GalleryItem(title = "", imageRes = "gallery_4"),
                    GalleryItem(title = "", imageRes = "gallery_5"),
                    GalleryItem(title = "", imageRes = "gallery_6")
                )
            )

            // Pre-populate Music with Spotify track IDs
            dao.insertMusic(
                listOf(
                    Music(title = "Setengah Lima", artist = "Sore", imageRes = "music_1", spotifyId = "1feOaXwpdAtKphBT1uXxoU"),
                    Music(title = "Karunia Semesta", artist = "The Changcuters", imageRes = "music_2", spotifyId = "4lw4drUE3FNvcKnQ7Ku4pB"),
                    Music(title = "Berduka-Lara", artist = "MASDO", imageRes = "music_3", spotifyId = "0CCtyxmEcpIQaNwPqWHgAW"),
                    Music(title = "Timur", artist = "The Adams", imageRes = "music_4", spotifyId = "2bEuh25NMtUEQGu6VqohPu")
                )
            )

        }
    }
}
