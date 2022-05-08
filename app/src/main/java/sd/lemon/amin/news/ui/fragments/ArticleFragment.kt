package sd.lemon.amin.news.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import sd.lemon.amin.news.R
import sd.lemon.amin.news.databinding.FragmentArticleBinding
import sd.lemon.amin.news.model.Article
import sd.lemon.amin.news.ui.NewsActivity
import sd.lemon.amin.news.ui.mvvm.NewsViewModel
import sd.lemon.amin.news.util.Constants.Companion.ARTICLE_ARGUMENT

class ArticleFragment : Fragment(R.layout.fragment_article) {
    lateinit var viewModel: NewsViewModel
    lateinit var binding: FragmentArticleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        val article: Article = arguments?.getSerializable(ARTICLE_ARGUMENT) as Article
        binding.webView.apply {
            webViewClient = WebViewClient()
            article.url?.let { loadUrl(it) }
        }

        binding.fab.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(view, R.string.save_articl_successfully, Snackbar.LENGTH_LONG).show()
        }
    }
}
