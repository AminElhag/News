package sd.lemon.amin.news.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Article")
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    @Embedded
    val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
) : Serializable