package com.dggorbachev.newsfeedapp.feature.main_screen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dggorbachev.newsfeedapp.R
import com.dggorbachev.newsfeedapp.base.utils.setAdapterAndCleanupOnDetachFromWindow
import com.dggorbachev.newsfeedapp.databinding.FragmentMainscreenBinding
import com.dggorbachev.newsfeedapp.feature.main_screen.ui.adapter.ArticlesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenFragment : Fragment(R.layout.fragment_mainscreen) {




    private val viewModel by viewModel<MainScreenViewModel>()

    private var _binding: FragmentMainscreenBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Cannot access binding")

    private var articlesAdapter: ArticlesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentMainscreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        viewModel.viewState.observe(viewLifecycleOwner, ::render)
    }

    private fun render(viewState: ViewState) {
        articlesAdapter?.items = viewState.articleList
        binding.progressBar.isVisible = viewState.isLoading
    }

    private fun initAdapter() {
        articlesAdapter = ArticlesAdapter()
        binding.rvArticles.apply {
            articlesAdapter?.let { setAdapterAndCleanupOnDetachFromWindow(it) }
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}