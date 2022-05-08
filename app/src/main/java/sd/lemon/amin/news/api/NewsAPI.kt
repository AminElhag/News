package sd.lemon.amin.news.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import sd.lemon.amin.news.BuildConfig
import sd.lemon.amin.news.model.NewsModel

interface NewsAPI {

    @GET("v2/top-headlines")
    suspend fun topHeadlinesAPI(
        @Query("country") country: String = "ae",
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("page") page: Int = 1
    ): Response<NewsModel>

    @GET("v2/everything")
    suspend fun searchNewsAPI(
        @Query("q") search: String,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("page") page: Int = 1
    ): Response<NewsModel>
}