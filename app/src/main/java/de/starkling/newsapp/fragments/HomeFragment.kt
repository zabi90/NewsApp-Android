package de.starkling.newsapp.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import de.starkling.adapters.HeadlineAdapter
import de.starkling.newsapp.base.BaseFragment
import de.starkling.newsapp.extensions.showSnackBar
import de.starkling.newsapp.extensions.toast
import de.starkling.newsapp.injections.ViewModelFactory
import de.starkling.newsapp.rest.response.Status
import de.starkling.newsapp.viewmodels.HomeViewModel
import de.starkling.newsapp_android.R
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


class HomeFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: HomeViewModel

    private lateinit var headlineAdapter: HeadlineAdapter

    val args: HomeFragmentArgs by navArgs()

    override fun inject() {
        component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[HomeViewModel::class.java]
        viewModel.currentCategory = args.category
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {

            headlineAdapter = HeadlineAdapter(it)

            recyclerView.layoutManager = LinearLayoutManager(it)

            recyclerView.adapter = headlineAdapter
        }

        loadNews()

        refreshLayout.setOnRefreshListener {
            loadNews()
        }
    }

    private fun loadNews() {

        viewModel.getHeadline()

        viewModel.newsHeadlineResponse.observe(this, Observer {

            when (it.status) {
                Status.SUCCESS -> {
                    headlineAdapter.setItems(it.data)
                    refreshLayout.isRefreshing = false
                }
                Status.LOADING -> refreshLayout.isRefreshing = true
            }
        })

        viewModel.newsHeadlineError.observe(this, Observer {
            showSnackBar(it)
        })
    }

}
