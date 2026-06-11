package unikom.dimasnurfauzi.androiduts.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import unikom.dimasnurfauzi.androiduts.data.entity.DailyActivity
import unikom.dimasnurfauzi.androiduts.data.entity.Friend
import unikom.dimasnurfauzi.androiduts.data.entity.GalleryItem
import unikom.dimasnurfauzi.androiduts.data.entity.Music

@Dao
interface AppDao {
    @Query("SELECT * FROM daily_activity ORDER BY id ASC")
    fun getAllDailyActivities(): Flow<List<DailyActivity>>

    @Query("SELECT * FROM friend ORDER BY id ASC")
    fun getAllFriends(): Flow<List<Friend>>

    @Query("SELECT * FROM gallery_item ORDER BY id ASC")
    fun getAllGalleryItems(): Flow<List<GalleryItem>>

    @Query("SELECT * FROM music ORDER BY id ASC")
    fun getAllMusic(): Flow<List<Music>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyActivities(activities: List<DailyActivity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFriends(friends: List<Friend>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGalleryItems(items: List<GalleryItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMusic(music: List<Music>)

    @Query("DELETE FROM daily_activity")
    suspend fun deleteAllDailyActivities()

    @Query("DELETE FROM friend")
    suspend fun deleteAllFriends()

    @Query("DELETE FROM gallery_item")
    suspend fun deleteAllGalleryItems()

    @Query("DELETE FROM music")
    suspend fun deleteAllMusic()

}
