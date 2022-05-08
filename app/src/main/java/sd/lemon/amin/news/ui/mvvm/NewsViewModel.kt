package sd.lemon.amin.news.ui.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import sd.lemon.amin.news.model.Article
import sd.lemon.amin.news.model.NewsModel
import sd.lemon.amin.news.repository.NewsRepository
import sd.lemon.amin.news.util.Resource

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    val breakingNews: MutableLiveData<Resource<NewsModel>> = MutableLiveData()
    var breakingNewsNumber = 1
    var breakingNewsResponse: NewsModel? = null

    val searchNews: MutableLiveData<Resource<NewsModel>> = MutableLiveData()
    var searchNewsPageNumber = 1
    var searchNewsResponse: NewsModel? = null


    init {
        getBreakNews("ae")
    }

    fun getBreakNews(countryCode: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getBreakNews(countryCode, breakingNewsNumber)
        breakingNews.postValue(handleBreakNewsResponse(response))
    }

    private fun handleBreakNewsResponse(response: Response<NewsModel>): Resource<NewsModel> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                breakingNewsNumber++
                if (breakingNewsResponse == null) {
                    breakingNewsResponse = result
                } else {
                    val old = breakingNewsResponse?.articles
                    val new = response.body()?.articles
                    if (new != null) {
                        old?.addAll(new)
                    }
                }
                return Resource.Success(breakingNewsResponse ?: result)
            }
        }
        return Resource.Error(message = response.message())
    }

    fun getSearchNews(searchQuery: String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val response = newsRepository.getSearchNews(searchQuery, searchNewsPageNumber)
        searchNews.postValue(handleSearchNewsResponse(response))
    }

    private fun handleSearchNewsResponse(response: Response<NewsModel>): Resource<NewsModel> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                searchNewsPageNumber++
                if (searchNewsResponse == null) {
                    searchNewsResponse = result
                } else {
                    val old = searchNewsResponse?.articles
                    val new = response.body()?.articles
                    if (new != null) {
                        old?.addAll(new)
                    }
                }
                return Resource.Success(searchNewsResponse ?: result)
            }
        }
        return Resource.Error(message = response.message())
    }

    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.saveArticle(article)
    }

    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }

    fun getAllArticles() = newsRepository.getArticles()
}