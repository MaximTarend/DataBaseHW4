package by.hometrainng.databasehw4.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.hometrainng.databasehw4.databinding.DatabaseItemBinding
import by.hometrainng.databasehw4.model.ListElement
import by.hometrainng.databasehw4.model.User

class ItemAdapter(
    context: Context,
    private val onClicked: (User) -> Unit
): ListAdapter<User, UserViewHolder>(DIFF_UTIL) {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            binding = DatabaseItemBinding.inflate(layoutInflater, parent, false),
            onClicked = onClicked
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class UserViewHolder(
    private val binding: DatabaseItemBinding,
    private val onClicked: (User) -> Unit
): RecyclerView.ViewHolder(binding.root) {
    fun bind (item: User) {
        with(binding) {
            firstName.text = item.firstName
            lastName.text = item.lastName
            id.text = item.id.toString()

            root.setOnClickListener {
                onClicked(item)
            }
        }
    }
}