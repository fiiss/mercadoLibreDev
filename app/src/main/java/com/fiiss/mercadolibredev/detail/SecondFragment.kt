package com.fiiss.mercadolibredev.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.fiiss.mercadolibredev.R
import com.fiiss.mercadolibredev.app.BaseFragment
import com.fiiss.mercadolibredev.databinding.FragmentSecondBinding
import com.fiiss.mercadolibredev.result.model.Attributes
import com.fiiss.mercadolibredev.result.model.Communicator
import com.fiiss.mercadolibredev.result.model.Results
import com.fiiss.mercadolibredev.result.view.SearchResult
import java.text.NumberFormat
import java.util.*

class SecondFragment : BaseFragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private val model: Communicator by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.selected.observe(viewLifecycleOwner, { item ->
            setDataProduct(item)
        })
    }

    private fun setDataProduct(productSelect: Results) {

        when(productSelect.condition) {
            "new" -> binding.condition.text = getString(R.string.new_)
            else -> binding.condition.text = getString(R.string.old)
        }

        binding.txtSold.text = String.format("%1s vendidos", productSelect.sold_quantity)
        binding.productName.text = productSelect.title
        binding.textPreci.text = String.format(
            "$%1s", NumberFormat.getNumberInstance(Locale.GERMANY).format(
                productSelect.price
            )
        )
        if (productSelect.installments == null) {
            binding.installmentsLiner.visibility = View.GONE
        } else {
            binding.installmentsLiner.visibility = View.VISIBLE
            binding.txtInstallments.text = String.format(
                "%1s x %2s", productSelect.installments!!.quantity, NumberFormat.getNumberInstance(
                    Locale.GERMANY
                ).format(productSelect.installments!!.rate)
            )
        }
        Glide.with(this).load(productSelect.thumbnail).dontAnimate().into(binding.imgProductDetail)

        for (item: Attributes in productSelect.attributes!!) {
            val view: View = LayoutInflater.from(binding.addfeatures.context).inflate(
                R.layout.items_addfeatures,
                binding.addfeatures,
                false
            )
            val name = view.findViewById<TextView>(R.id.name)
            val valueName = view.findViewById<TextView>(R.id.value_name)
            name.text = String.format("%1s:", item.name)
            valueName.text = item.value_name
            binding.addfeatures.addView(view)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}