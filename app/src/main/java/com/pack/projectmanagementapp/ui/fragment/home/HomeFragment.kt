package com.pack.projectmanagementapp.ui.fragment.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pack.projectmanagementapp.R
import com.pack.projectmanagementapp.databinding.FragmentHomeBinding
import com.pack.projectmanagementapp.entities.Product
import com.pack.projectmanagementapp.ui.adapter.RecycleViewHomeAdapter
import com.pack.projectmanagementapp.ui.fragment.product.ProductFragment
import com.pack.projectmanagementapp.viewModel.HomeFragmentViewModel
import com.pack.projectmanagementapp.viewModel.HomeFragmentViewModelFactory

class HomeFragment : Fragment(), RecycleViewHomeAdapter.OnProductClickListener, RecycleViewHomeAdapter.OnDeleteListUpdate{

    private lateinit var dataPassListener: DataPassListener
    private lateinit var viewModel: HomeFragmentViewModel
    private lateinit var binding: FragmentHomeBinding
    private val productList = ArrayList<Product>()
    private var count=1;
    private var listOfIdOfProductDeleted = ArrayList<Int>()
    private val rvHomeAdapter by lazy {
        RecycleViewHomeAdapter(
            requireContext(),
            this,this
        )
        { show -> showDeleteComponent(show) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        initViewModel()
        observeProductData()
        fetchProductDetailsApi()

        binding.clDeleteDetailsButton.setOnClickListener {
            //write the delete event listener
            viewModel.deleteAllProduct(listOfIdOfProductDeleted)
            rvHomeAdapter.notifyDataSetChanged()
            showDeleteComponent(false)
            rvHomeAdapter.selectedDeleteItem.clear()
            count=0

        }

        binding.ivCross.setOnClickListener {
            listOfIdOfProductDeleted.clear()
            rvHomeAdapter.clearAllSelectedItem()
            showDeleteComponent(false)
        }
        binding.tvDeleteCount.setOnClickListener {
            listOfIdOfProductDeleted.clear()
            rvHomeAdapter.clearAllSelectedItem()
            showDeleteComponent(false)
        }
    }

    private fun setupRecyclerView() {
        binding.rvProducts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvProducts.adapter = rvHomeAdapter
    }

    private fun initViewModel() {
        val viewModelFactory = HomeFragmentViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeFragmentViewModel::class.java)
    }

    private fun observeProductData() {
        viewModel.allProducts.observe(viewLifecycleOwner) { list ->
            try {
                list?.let {
                    productList.clear()
                    productList.addAll(it)
                    rvHomeAdapter.setProductList(productList)
                }
            } catch (e: Exception) {
                Log.e("HomeFragment", "Error in LiveData observer: ${e.message}", e)
            }
        }

    }

    private fun fetchProductDetailsApi() {
        viewModel.fetchDataFromApi()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is DataPassListener) {
            dataPassListener = context
        } else {
            throw ClassCastException("$context must implement DataPassListener")
        }
    }

    private fun sendDataToProductFragment(data: Product) {
        dataPassListener.onDataPassed(data)
    }


    interface DataPassListener {
        fun onDataPassed(data: Product)
    }


    override fun onProductClick(product: Product) {
        sendDataToProductFragment(product)
        val productFragment = ProductFragment.newInstance(product)
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentListProduct, productFragment)
        transaction.addToBackStack(null) // Allows going back to the previous fragment
        transaction.commit()
    }

    fun showDeleteComponent(show: Boolean): Unit {
        if(show){
            binding.clDeleteDetailsButton.visibility = View.VISIBLE
            binding.clDeleteDetailsCount.visibility = View.VISIBLE
        }else{
            binding.clDeleteDetailsButton.visibility = View.GONE
            binding.clDeleteDetailsCount.visibility = View.GONE
        }

    }

    override fun addDeletedProductId(id: Int) {
        listOfIdOfProductDeleted.add(id)
        binding.tvDeleteCount.text=rvHomeAdapter.selectedDeleteItem.size.toString()
        Log.i("listOfIdOfProductDeletedTestingP", "count List : $count")

    }

    override fun removeDeletedProductId(id: Int) {
        listOfIdOfProductDeleted.remove(id)
        binding.tvDeleteCount.text=rvHomeAdapter.selectedDeleteItem.size.toString()

        Log.i("listOfIdOfProductDeletedTestingN", "count List : $count")

    }


}
