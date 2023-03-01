package dev.jiskelo.mornhouse.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.jiskelo.mornhouse.data.entity.NumberEntity
import dev.jiskelo.mornhouse.databinding.CartEntityBinding

class NumberAdapter(val listener :ClickListener) :RecyclerView.Adapter<NumberAdapter.ViewHolder>() {

    private var numbers = emptyList<NumberEntity>()

    class ViewHolder(val binding: CartEntityBinding) :RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(numbers : List<NumberEntity>) {
        this.numbers = numbers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CartEntityBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return numbers.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = numbers[position]

        with(holder.binding) {
            textNumber.text = item.number.toString()
            textDescription.text = item.description

            root.setOnClickListener {
                listener.clickItem(item)
            }
        }
    }

    interface ClickListener {
        fun clickItem(numberEntity: NumberEntity)
    }
}