package com.fiiss.mercadolibredev.result.view

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import com.fiiss.mercadolibredev.R
import com.fiiss.mercadolibredev.api.RetrofitBuilder
import com.fiiss.mercadolibredev.app.BaseFragment
import com.fiiss.mercadolibredev.databinding.FragmentSearchResultBinding
import com.fiiss.mercadolibredev.result.controller.ResultController
import com.fiiss.mercadolibredev.result.interfaces.ResultInterface
import com.fiiss.mercadolibredev.result.model.Communicator
import com.fiiss.mercadolibredev.result.model.ResponseListProduct
import com.fiiss.mercadolibredev.result.model.Results
import com.fiiss.mercadolibredev.result.recycle.CustomAdapterProduct
import com.fiiss.mercadolibredev.result.recycle.RecyclerItemClickListener


class SearchResult : BaseFragment(), ResultInterface.View {

    private var _binding: FragmentSearchResultBinding? = null
    private val binding get() = _binding!!

    private var controller: ResultInterface.Controller? = null
    private var skeletonScreen: SkeletonScreen? = null
    private var listResponse: List<Results> = listOf()
    private val model: Communicator by activityViewModels()
    private lateinit var mAdapter: CustomAdapterProduct


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentSearchResultBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        model.listProduct.observe(viewLifecycleOwner, {
            mAdapter.refreshList(model.getListProduct()?.listResult)
            binding.imageBackground.visibility = View.GONE
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controller = ResultController(this)

        // the adapter is initialized so that it does not mark an error when rendering the screen
        binding.recycleProduct.setHasFixedSize(true)
        binding.recycleProduct.layoutManager = LinearLayoutManager(requireContext())
        binding.recycleProduct.addItemDecoration(DividerItemDecoration(binding.recycleProduct.context, DividerItemDecoration.VERTICAL))
        mAdapter = CustomAdapterProduct(model.getListProduct()?.listResult, requireContext())
        binding.recycleProduct.adapter = mAdapter
        binding.recycleProduct.addOnItemTouchListener(
            RecyclerItemClickListener(context, object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        model.select(model.getListProduct()?.listResult!![position])
                        controller?.contrllergoToNextScreen(view)
                    }
                })
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)

        val manager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(requireActivity().componentName))

        //region implementation click action recycleView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("", false)
                searchItem.collapseActionView()
                if (query?.trim().toString().isNotEmpty()) {
                    query?.let {
                        controller?.getItemsFromQuery(it, RetrofitBuilder.apiInterface)
                    }

                    skeletonScreen = Skeleton.bind(binding.rootView)
                        .load(R.layout.item_skeleton_news)
                        .show()

                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        //endregion

    }

    //region response service api
    override fun responseProductList(responseListProduct: ResponseListProduct) {
        this.listResponse = responseListProduct.listResult!!
        if (listResponse.isNotEmpty()) {
            model.setListProduct(responseListProduct)
            mAdapter.refreshList(model.getListProduct()?.listResult)
            skeletonScreen?.hide()
            binding.imageBackground.visibility = View.GONE
        } else {
            alertPanel(
                "No se encontraron resultados",
                R.color.colorAccent,
                R.drawable.alerter_ic_notifications,
                R.color.colorPrimary
            )
            skeletonScreen?.hide()
            binding.imageBackground.visibility = View.VISIBLE
        }

    }
    //endregion

    //region response service error
    override fun responseError(error: String) {
        binding.imageBackground.visibility = View.VISIBLE
        alertPanel(
            error,
            R.color.colorAccent,
            R.drawable.alerter_ic_notifications,
            R.color.colorPrimary
        )
    }
    //endregion

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}