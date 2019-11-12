package de.starkling.newsapp.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import de.starkling.newsapp.extensions.loadNetworkImage
import de.starkling.newsapp.models.Article

import de.starkling.newsapp_android.R
import kotlinx.android.synthetic.main.fragment_detail.*

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {


    private val args: DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.article?.let {
            setupArticleData(it)
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_search -> {
                findNavController().navigate(R.id.action_detailFragment_to_searchFragment)
            }
        }
        return true
    }

    private fun setupArticleData(article: Article) {
        titleTextView.text = article.title
        descriptionTextView.text = article.description
        authorTextView.text = "By ${article.author}"
        publishDateTextView.text = article.publishedAt
        sourceTextView.text = article.source?.name

        article.urlToImage?.let {
            imageView.loadNetworkImage(progressBar, it)
        }
        contentTextView.text = article.content
    }

}
