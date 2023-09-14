package com.pack.projectmanagementapp.ui.fragment.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.pack.projectmanagementapp.R
import com.pack.projectmanagementapp.databinding.FragmentProductBinding
import com.pack.projectmanagementapp.entities.Product
import com.pack.projectmanagementapp.services.repo.ProductRepository
import com.pack.projectmanagementapp.ui.fragment.home.HomeFragment

class ProductFragment : Fragment(), HomeFragment.DataPassListener {
    lateinit var binding: FragmentProductBinding
    lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        product = arguments?.getParcelable(PRODUCT_KEY) ?: Product(1,"nullpp","null","null","null",0.0,
            listOf(), 0,0.0,0,"null") // Provide a default Product if needed
        super.onViewCreated(view, savedInstanceState)

        binding.tvProductTitle.text= product.title
        binding.apply {
            productDetails = product
        }




        binding.imgBack.setOnClickListener {
//            requireActivity().onBackPressed
            requireActivity().supportFragmentManager.popBackStack()
//            val homeFragment = HomeFragment()
//            val transaction = requireActivity().supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.fragmentListProduct, homeFragment)
//            transaction.addToBackStack(null) // Allows going back to the previous fragment
//            transaction.commit()
        }

    }

    companion object {
        private const val PRODUCT_KEY = "product"
        @JvmStatic
        fun newInstance(product: Product): ProductFragment {
            val fragment = ProductFragment()
            val args = Bundle()
            args.putParcelable(PRODUCT_KEY, product)
            fragment.arguments = args
            return fragment
        }
    }
    override fun onDataPassed(data: Product) {
        product = data
    }
}