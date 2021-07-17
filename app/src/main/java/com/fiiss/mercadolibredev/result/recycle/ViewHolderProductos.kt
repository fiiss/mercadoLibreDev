package com.fiiss.mercadolibredev.result.recycle

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fiiss.mercadolibredev.R

class ViewHolderProductos(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var imageProduct: ImageView = itemView.findViewById(R.id.imgProduct)
    var titleProduct: TextView = itemView.findViewById(R.id.titleProduct)
    var priceProduct: TextView = itemView.findViewById(R.id.priceProduct)
    var quantityProduct: TextView = itemView.findViewById(R.id.quantityProduct)

}