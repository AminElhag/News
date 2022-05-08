package sd.lemon.amin.news.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import sd.lemon.amin.news.databinding.ItemArticlePreviewBinding
import sd.lemon.amin.news.model.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ItemViewHolder>() {

    private val differCallBack = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    inner class ItemViewHolder(private val binding: ItemArticlePreviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            Glide.with(binding.root).load(article.urlToImage).into(binding.ivArticleImage)
            binding.tvTitle.text = article.title
            binding.tvSource.text = article.source?.name
            binding.tvDescription.text = article.description
            binding.tvPublishedAt.text = article.publishedAt
        }
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            holder.bind(article)
            setOnClickListener {
                onItemClickListener?.let {
                    it(article)
                }
            }
        }
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}