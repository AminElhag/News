package sd.lemon.amin.news.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import sd.lemon.amin.news.databinding.ActivityNewsBinding
import sd.lemon.amin.news.db.ArticleDataBase
import sd.lemon.amin.news.repository.NewsRepository
import sd.lemon.amin.news.ui.mvvm.NewsViewModel
import sd.lemon.amin.news.ui.mvvm.NewsViewModelProviderFactory

class NewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsBinding
    lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = NewsRepository(ArticleDataBase(this))
        val factory = NewsViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigationView.setupWithNavController(binding.flFragment[0].findNavController())
    }
}