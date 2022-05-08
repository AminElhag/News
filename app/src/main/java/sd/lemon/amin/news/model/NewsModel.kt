package sd.lemon.amin.news.model

data class NewsModel(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)