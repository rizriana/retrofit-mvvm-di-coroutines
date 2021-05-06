package id.rrlab.hilt.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.rrlab.hilt.data.model.User
import id.rrlab.hilt.databinding.ItemMainBinding
import id.rrlab.hilt.utils.DiffUtils

class MainAdapter(private val users: ArrayList<User>) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMainBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    class ViewHolder(private val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.apply {
                tvUsername.text = user.name
                tvUserEmail.text = user.email
                Glide.with(ivAvatar.context)
                    .load(user.avatar)
                    .into(ivAvatar)
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