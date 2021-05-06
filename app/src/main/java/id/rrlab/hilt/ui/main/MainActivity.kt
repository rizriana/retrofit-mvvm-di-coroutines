package id.rrlab.hilt.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.rrlab.hilt.data.model.User
import id.rrlab.hilt.databinding.ActivityMainBinding
import id.rrlab.hilt.utils.Status
import id.rrlab.hilt.utils.gone
import id.rrlab.hilt.utils.visible

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUi()
        setupObserver()
    }

    private fun setupUi() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        mainViewModel.users.observe(this) {
            when (it.status) {
                Status.LOADING -> {
                    binding.apply {
                        binding.progressBar.visible()
                        binding.recyclerView.gone()
                    }
                }
                Status.SUCCESS -> {
                    binding.apply {
                        progressBar.gone()
                        recyclerView.visible()
                    }
                    it.data?.let { users -> renderList(users) }
                }
                Status.ERROR -> {
                    binding.progressBar.gone()
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun renderList(users: List<User>) {
        adapter.setData(users)
    }
}