package sd.lemon.amin.news.repository

import sd.lemon.amin.news.api.RetrofitInstance
import sd.lemon.amin.news.db.ArticleDataBase
import sd.lemon.amin.news.model.Article

class NewsRepository(
    private val db: ArticleDataBase
) {
    suspend fun getBreakNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.topHeadlinesAPI(country = countryCode, page = pageNumber)

    suspend fun getSearchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchNewsAPI(search = searchQuery, page = pageNumber)

    suspend fun saveArticle(article: Article) = db.getArticleDao().insert(article)

    suspend fun deleteArticle(article: Article) = db.getArticleDao().delete(article)

    fun getArticles() = db.getArticleDao().getAll()
}