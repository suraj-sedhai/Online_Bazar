package com.example.onlinebazar.adapters

import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlinebazar.data.Product
import com.example.onlinebazar.databinding.ProductRvItemBinding
import com.example.onlinebazar.helper.getProductPrice

class BestProductsAdapter : RecyclerView.Adapter<BestProductsAdapter.BestProductsViewHolder>() {

    inner class BestProductsViewHolder(private val binding: ProductRvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.apply {
                Glide.with(itemView).load(product.images[0]).into(imgProduct)
                tvPrice.text = "$ ${product.price}"
                tvName.text = product.name
                if(product.offerPercaentage !=null){
                    tvPrice.paintFlags = tvPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    val priceAfterOffer = product.offerPercaentage.getProductPrice(product.price)
                    tvNewPrice.text = "$ ${String.format("%.2f", priceAfterOffer)}"

                }
//                val priceAfterOffer = product.offerPercaentage.getProductPrice(product.price)
//                tvNewPrice.text = "$ ${String.format("%.2f", priceAfterOffer)}"
//                tvPrice.paintFlags = tvPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                if (product.offerPercaentage == null)
                    tvNewPrice.visibility = View.INVISIBLE


            }
            binding.apply {
                val priceAfterOffer = product.offerPercaentage.getProductPrice(product.price)
                tvNewPrice.text = "$ ${String.format("%.2f", priceAfterOffer)}"
                tvPrice.paintFlags = tvPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                if (product.offerPercaentage == null)
                    tvNewPrice.visibility = View.INVISIBLE

                Glide.with(itemView).load(product.images[0]).into(imgProduct)
                tvPrice.text = "$ ${product.price}"
                tvName.text = product.name
            }

        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestProductsViewHolder {
        return BestProductsViewHolder(
            ProductRvItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: BestProductsViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.bind(product)

        holder.itemView.setOnClickListener {
            onClick?.invoke(product)
        }
        Log.e("differ", product.name)


    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    var onClick: ((Product) -> Unit)? = null

}