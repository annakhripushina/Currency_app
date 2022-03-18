package com.example.currency_app.presentation.view.currencyList

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.currency_app.R
import com.example.currency_app.dagger.CurrencyApp
import com.example.currency_app.dagger.module.viewModel.CurrencyViewModelFactory
import com.example.currency_app.data.entity.Currency
import com.example.currency_app.presentation.view.currencyConverter.CurrencyConverterActivity
import com.example.currency_app.utils.CurrencyItemDecorator
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class CurrencyListActivity : Fragment() {
    @Inject
    lateinit var viewModelFactory: CurrencyViewModelFactory
    lateinit var viewModel: CurrencyListViewModel

    private lateinit var recyclerView: RecyclerView

    private lateinit var swipeContainer: SwipeRefreshLayout

    private val adapter = CurrencyListAdapter(object : CurrencyListAdapter.CurrencyClickListener {
        override fun onCurrencyClick(currencyItem: Currency, itemView: View, position: Int) {
            viewModel.onSetCurrencyItem(currencyItem)

            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.containerActivity,
                    CurrencyConverterActivity(),
                    "currencyConverterActivity"
                )
                .addToBackStack("currencyConverterActivity")
                .commit()
        }

    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        CurrencyApp.appComponentViewModel.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CurrencyListViewModel::class.java)
        return inflater.inflate(
            R.layout.currency_list,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pullToRefresh(view)
        initRecycler(view)

        if (viewModel.prefsFirstRun) {
            viewModel.onGetCurrencyList()
        }

        viewModel.onGetCurrencyListDB()
            .observe(viewLifecycleOwner, Observer { list ->
                list?.let {
                    adapter.setItems(list as ArrayList<Currency>)
                }
            })

        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            val snackUpdateList = Snackbar.make(
                view,
                error,
                Snackbar.LENGTH_LONG
            )
            snackUpdateList.setAction(getString(R.string.updateText)) {
                viewModel.onGetCurrencyList()
                snackUpdateList.dismiss()
            }
            snackUpdateList.show()

        })

    }

    private fun initRecycler(view: View) {
        recyclerView = view.findViewById(R.id.recyclerViewCurrencyList)
        recyclerView.adapter = adapter
        setGridByOrientation(resources.configuration.orientation)
        recyclerView.addItemDecoration(CurrencyItemDecorator())

    }

    private fun pullToRefresh(view: View) {
        swipeContainer = view.findViewById(R.id.swipeContainer)
        swipeContainer.setOnRefreshListener {
            viewModel.onGetCurrencyList()
            swipeContainer.isRefreshing = false
        }
    }

    private fun setGridByOrientation(orientation: Int) {
        when (orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                recyclerView.layoutManager = GridLayoutManager(view?.context, 2)
            }
            Configuration.ORIENTATION_PORTRAIT -> {
                recyclerView.layoutManager = GridLayoutManager(view?.context, 1)
            }
        }
    }

}
