package id.backup.mvvm.ui.main.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import id.backup.mvvm.data.api.ApiHelper
import id.backup.mvvm.data.api.RetrofitBuilder
import id.backup.mvvm.data.model.User
import id.backup.mvvm.databinding.ActivityMainBinding
import id.backup.mvvm.ui.base.ViewModelFactory
import id.backup.mvvm.ui.main.adapter.MainAdapter
import id.backup.mvvm.ui.main.viewmodel.MainViewModel
import id.backup.mvvm.utils.Ext.gone
import id.backup.mvvm.utils.Ext.visible
import id.backup.mvvm.utils.Status

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupUI()
        setupObserver()
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {
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
        mainViewModel.getUsers().observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.apply {
                            recyclerView.visible()
                            progressBar.gone()
                        }
                        resource.data?.let { users -> retrieveList(users) }
                    }
                    Status.ERROR -> {
                        binding.apply {
                            recyclerView.visible()
                            progressBar.gone()
                        }
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    }
                    Status.LOADING -> {
                        binding.apply {
                            recyclerView.visibility
                            progressBar.gone()
                        }
                    }
                }
            }
        })
    }

    private fun retrieveList(users: List<User>) {
        adapter.addUsers(users)
    }
}