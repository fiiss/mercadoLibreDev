package com.fiiss.mercadolibredev.result.recycle

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fiiss.mercadolibredev.R
import com.fiiss.mercadolibredev.result.model.Results
import java.text.NumberFormat
import java.util.*

class CustomAdapterProduct(private var listResult: List<Results>?, private val requireContext: Context): RecyclerView.Adapter<ViewHolderProductos>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolderProductos {
        val view = LayoutInflater.from(viewGroup.context).inflate(
            R.layout.product_result_item,
            viewGroup,
            false
        )

        return ViewHolderProductos(view)
    }

    override fun onBindViewHolder(holder: ViewHolderProductos, position: Int) {
        val product = listResult?.get(position)
        Glide.with(requireContext).load(product?.thumbnail).dontAnimate().into(holder.imageProduct)
        holder.titleProduct.text = product?.title ?: ""
        holder.priceProduct.text = String.format( "$%1s", NumberFormat.getNumberInstance(Locale.GERMANY).format(product?.price ))

        when(product?.shipping!!.free_shipping) {
            true -> holder.quantityProduct.text = requireContext.getString(R.string.free_shipping)
            false -> holder.quantityProduct.text = ""
        }

    }



    fun refreshList(listResult: List<Results>?) {
        this.listResult = listResult
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        if (listResult == null) {
            return 0
        }

        return listResult!!.size
    }
}