package id.rrlab.withkoin.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.rrlab.withkoin.data.model.User
import id.rrlab.withkoin.databinding.ItemLayoutBinding
import id.rrlab.withkoin.utils.DiffUtils

class MainAdapter(
    private val users: ArrayList<User>
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
        MainViewHolder.from(parent)

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int =
        users.size

    class MainViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): MainViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemLayoutBinding.inflate(inflater, parent, false)
                return MainViewHolder(binding)
            }
        }

        fun bind(users: User) {
            binding.apply {
                result = users
                executePendingBindings()
            }
        }
    }

    fun setData(users: List<User>) {
        val diffCallback = DiffUtils(this.users, users)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.users.addAll(users)
        diffResult.dispatchUpdatesTo(this)
    }
}