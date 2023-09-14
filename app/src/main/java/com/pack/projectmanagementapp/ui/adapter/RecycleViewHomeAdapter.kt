package com.pack.projectmanagementapp.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
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
    private var onItemLongClickListener: OnProductLongClickListener
) : RecyclerView.Adapter<RecycleViewHomeAdapter.RecycleViewHomeHolder>() {

    private var products: MutableList<Product> = mutableListOf()

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

        if (product.isSelected) {
            holder.binding.icoSelected.visibility = View.VISIBLE
            holder.itemView.setBackgroundColor(R.color.white)
        } else {
            holder.binding.icoSelected.visibility = View.GONE
            holder.itemView.setBackgroundColor(R.color.black)
        }

        holder.itemView.setOnClickListener {
            if (product.isSelected) {
                product.isSelected = false
                //if the item isSelected then update the ui and make them unSelected
                holder.binding.icoSelected.visibility = View.GONE
                holder.itemView.setBackgroundColor(R.color.black)
            } else {
                onItemClickListener.onProductClick(product)
            }
        }

        holder.itemView.setOnLongClickListener {
            onItemLongClickListener.onProductLongClick(position)
            product.isSelected = true
            notifyItemChanged(position)
            true
        }
    }


    interface OnProductClickListener {
        fun onProductClick(product: Product)
    }

    interface OnProductLongClickListener {
        fun onProductLongClick(position: Int)
    }


}