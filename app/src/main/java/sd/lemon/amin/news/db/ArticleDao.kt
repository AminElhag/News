package sd.lemon.amin.news.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import sd.lemon.amin.news.model.Article

@Dao
interface ArticleDao {
    @Insert(onConflict = REPLACE)
    suspend fun insert(article: Article): Long

    @Query("SELECT * FROM Article")
    fun getAll(): LiveData<List<Article>>

    @Delete
    suspend fun delete(article: Article)
}