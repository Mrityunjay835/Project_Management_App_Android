package com.pack.projectmanagementapp.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pack.projectmanagementapp.R
import com.pack.projectmanagementapp.databinding.ViewProductBinding
import com.pack.projectmanagementapp.entities.Product

class RecycleViewHomeAdapter(
    private var context: Context,
    private var onItemClickListener: OnProductClickListener,
    private var onUpdateDeleteList:OnDeleteListUpdate,
    private val showDeleteComponents: (Boolean) -> Unit
) : RecyclerView.Adapter<RecycleViewHomeAdapter.RecycleViewHomeHolder>() {

    private var products: MutableList<Product> = mutableListOf()
    var selectedDeleteItem: MutableList<Int> = mutableListOf()
    private var isAllSelectFalse = false

    class RecycleViewHomeHolder(var binding: ViewProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewHomeHolder {
        val view = ViewProductBinding.inflate(LayoutInflater.from(context), parent, false)
        return RecycleViewHomeHolder(view)
    }

    fun setProductList(productsList: MutableList<Product>) {
        this.products = productsList
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return products.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: RecycleViewHomeHolder, position: Int) {
        val product = products[position]
        holder.binding.apply {
            productData = product
        }

        //holder.binding.imageUrl =product.thumbnail
        holder.binding.executePendingBindings()

        if(isAllSelectFalse){
            product.isSelected=false
            updateView(holder,product)
            if(product.id== products.size){
                isAllSelectFalse=false
                selectedDeleteItem.clear()
            }
        }

        if (product.isSelected) {
            holder.binding.icoSelected.visibility = View.VISIBLE
            holder.itemView.setBackgroundColor(R.color.white)
        } else {
            holder.binding.icoSelected.visibility = View.GONE
            holder.itemView.setBackgroundColor(R.color.black)
        }

        holder.itemView.setOnClickListener {
            Log.i("checkSelectedDeleteItem","selectedDeleteItem size =${selectedDeleteItem.size}")
            if (selectedDeleteItem.isEmpty()) {
               Log.i("checkSelectedDeleteItem","selectedDeleteItem size =${selectedDeleteItem.size}")
                onItemClickListener.onProductClick(product)
            } else {
                if (product.isSelected) {
                    //if the item isSelected then update the ui and make them unSelected
                    updateView(holder, product)
                } else {
                    updateViewOnDelete(holder, product)
                }

            }
        }

        holder.itemView.setOnLongClickListener {
//            showDeleteComponents(true)//------------------------
//            product.isSelected = true//------------------
//            selectedDeleteItem.add(product.id)//-----------------------
            if (product.isSelected) {
                //if the item isSelected then update the ui and make them unSelected
                updateView(holder, product)
            } else {
                updateViewOnDelete(holder, product)
            }
            notifyItemChanged(position)
            true
        }
    }

    @SuppressLint("ResourceAsColor")
    fun updateViewOnDelete(holder: RecycleViewHomeHolder, product: Product) {
        holder.itemView.setBackgroundColor(R.color.white)
        holder.binding.icoSelected.visibility = View.VISIBLE
        selectedDeleteItem.add(product.id)
        product.isSelected = true
        showDeleteComponents(true)
        onUpdateDeleteList.addDeletedProductId(product.id)
    }

    @SuppressLint("ResourceAsColor")
    fun updateView(holder: RecycleViewHomeHolder, product: Product) {
        holder.itemView.setBackgroundColor(R.color.black)
        holder.binding.icoSelected.visibility = View.GONE
        selectedDeleteItem.remove(product.id)
        onUpdateDeleteList.removeDeletedProductId(product.id)
        product.isSelected = false
        if (selectedDeleteItem.isEmpty())
            showDeleteComponents(false)
    }

    fun clearAllSelectedItem() {
        Log.i("clearAllSelectedItem","Yes")
        isAllSelectFalse =true
        this.notifyDataSetChanged()
    }


    interface OnProductClickListener {
        fun onProductClick(product: Product)
    }

    interface OnDeleteListUpdate{
        fun addDeletedProductId(id:Int)
        fun removeDeletedProductId(id:Int)
    }


//    fun clearList(){
//
//    }



}