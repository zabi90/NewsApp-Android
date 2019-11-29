package com.example.newsapp.fragments


import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import com.example.newsapp.MainActivity
import com.example.newsapp.R
import com.example.newsapp.adapters.HeadlineAdapter
import com.example.newsapp.base.BaseFragment
import com.example.newsapp.base.OnItemSelectListener
import com.example.newsapp.extensions.showSnackBar
import com.example.newsapp.injections.ViewModelFactory
import com.example.newsapp.models.Article
import com.example.newsapp.viewmodels.HomeViewModel
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.android.material.shape.ShapeAppearancePathProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


class HomeFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: HomeViewModel

    private val headlineAdapter: HeadlineAdapter = HeadlineAdapter()

   private val args: HomeFragmentArgs by navArgs()

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
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshLayout.setProgressViewEndTarget(false,200)
        context?.let {

            recyclerView.layoutManager = LinearLayoutManager(it)
            recyclerView.adapter = headlineAdapter
            recyclerView.addItemDecoration(DividerItemDecoration(it,LinearLayoutManager.VERTICAL))
            headlineAdapter.addListener(object : OnItemSelectListener<Article> {
                override fun onItemSelected(item: Article, position: Int, view: View) {
                    val extras = FragmentNavigatorExtras(
                        view to "imageView"
                    )
                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(item),extras)
                }
            })
        }
        loadNews()
        refreshLayout.setOnRefreshListener {
            loadNews()
        }

        recyclerView.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {

            override fun onChildViewDetachedFromWindow(view: View) {
                if(recyclerView.getChildAdapterPosition(view) == 0){
                    val ac = activity as MainActivity
                    TransitionManager.beginDelayedTransition(ac.toolbar)
                    val layoutParams = ac.toolbar.layoutParams as ConstraintLayout.LayoutParams
                    layoutParams.width = resources.getDimension(R.dimen._55sdp).toInt()
                    ac.toolbar.layoutParams = layoutParams
                    ac.toolbar.requestLayout()

                    val shapeDrawable = MaterialShapeDrawable()
                    val shapeAppearanceModel = ShapeAppearanceModel.builder().setBottomRightCorner(CornerFamily.CUT, resources.getDimension(R.dimen._16sdp)).build()
                    shapeDrawable.shapeAppearanceModel = shapeAppearanceModel
                    shapeDrawable.fillColor = ColorStateList.valueOf(ContextCompat.getColor(context!!,R.color.primaryColor))
                    ac.toolbar.background = shapeDrawable
                }
            }

            override fun onChildViewAttachedToWindow(view: View) {
                if(recyclerView.getChildAdapterPosition(view) == 0){
                    val ac = activity as MainActivity
                    TransitionManager.beginDelayedTransition(ac.toolbar)
                    val layoutParams = ac.toolbar.layoutParams as ConstraintLayout.LayoutParams
                    layoutParams.width = 0
                    ac.toolbar.layoutParams = layoutParams
                    ac.toolbar.requestLayout()

                    val shapeDrawable = MaterialShapeDrawable()
                    val shapeAppearanceModel = ShapeAppearanceModel.builder().setBottomRightCorner(CornerFamily.CUT,0f).build()
                    shapeDrawable.shapeAppearanceModel = shapeAppearanceModel
                    shapeDrawable.fillColor = ColorStateList.valueOf(ContextCompat.getColor(context!!,R.color.primaryColor))
                    ac.toolbar.background = shapeDrawable
                }
            }

        })
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_search -> {
                findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
            }
        }
        return true
    }
    private fun loadNews() {


        viewModel.getHeadline().observe(this, Observer {

            if(it.loadedCount == 0){
                emptyView.visibility = View.VISIBLE
            }else{
                emptyView.visibility = View.GONE
            }

            headlineAdapter.submitList(it)
        })

        viewModel.newsHeadlineError.observe(this, Observer { msg ->
            msg?.let {
                showSnackBar(it)
            }
        })

        viewModel.newsLoadingStatus.observe(this, Observer {
            refreshLayout.isRefreshing = it
        })
    }
}
