package by.hometrainng.databasehw4.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.hometrainng.databasehw4.databinding.DatabaseItemBinding
import by.hometrainng.databasehw4.model.ListElement

class ItemAdapter(
    context: Context
): ListAdapter<ListElement, ListElementViewHolder>(DIFF_UTIL) {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListElementViewHolder {
        return ListElementViewHolder(
            binding = DatabaseItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListElementViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<ListElement>() {
            override fun areItemsTheSame(oldItem: ListElement, newItem: ListElement): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ListElement, newItem: ListElement): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class ListElementViewHolder(
    private val binding: DatabaseItemBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind (item: ListElement) {
        with(binding) {
            id.text = item.id.toString()
            firstName.text = item.firstName
            lastName.text = item.lastName
        }
    }
}