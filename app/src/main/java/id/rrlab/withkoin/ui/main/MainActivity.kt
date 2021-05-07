package id.rrlab.withkoin.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import id.rrlab.withkoin.data.model.User
import id.rrlab.withkoin.databinding.ActivityMainBinding
import id.rrlab.withkoin.ui.main.adapter.MainAdapter
import id.rrlab.withkoin.utils.Status
import id.rrlab.withkoin.utils.ext.gone
import id.rrlab.withkoin.utils.ext.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mMainViewModel: MainViewModel by viewModel()
    private val mAdapter by lazy { MainAdapter(arrayListOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(applicationContext)
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
            recyclerView.adapter = mAdapter
        }
    }

    private fun setupObserver() {
        mMainViewModel.users.observe(this) {
            when (it.status) {
                Status.LOADING -> {
                    binding.apply {
                        progressBar.visibility
                        recyclerView.gone()
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
                    // Handler error
                    binding.progressBar.visible()
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun renderList(users: List<User>) {
        mAdapter.setData(users)
    }
}