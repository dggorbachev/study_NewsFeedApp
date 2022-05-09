package com.dggorbachev.newsfeedapp.feature.main_screen.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dggorbachev.newsfeedapp.R
import com.dggorbachev.newsfeedapp.base.openWeb
import com.dggorbachev.newsfeedapp.databinding.FragmentMainscreenBinding
import com.dggorbachev.newsfeedapp.feature.main_screen.ui.adapter.ArticlesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenFragment : Fragment(R.layout.fragment_mainscreen) {

    private var _binding: FragmentMainscreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<MainScreenViewModel>()
    private val adapter: ArticlesAdapter by lazy {
        ArticlesAdapter(
            articleList = listOf(),
            onItemClick = { article ->
                viewModel.processUiEvent(OpenArticleEvent.OnArticleClick(article))
            },
            onBookmarkClick = { article ->
                viewModel.processUiEvent(UiEvent.OnBookmarkClick(article))
            }
        )
    }

    // Если в Fragment прописать наш fragment, то метод не нужен (в случае, когда не добавляем что-то еще в него)
/*    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_mainscreen, container, false)
    }*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainscreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvArticles)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.ibSearch.setOnClickListener {
            viewModel.processUiEvent(UiEvent.OnSearchClicked)
        }
        binding.etSearchField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.processUiEvent(UiEvent.OnSearchTextInput(p0.toString()))
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer(::render))
        viewModel.openWebEvent.observe(viewLifecycleOwner, Observer(::openWebEvent))
    }

    private fun render(viewState: ViewState) {
//        adapter.updateArticles(viewState.articleList)
        showResults(viewState)
        binding.progressBar.isGone = !viewState.isLoading
        binding.searchFieldContainer.isGone = !viewState.isSearchVisible
    }

    private fun openWebEvent(event: OpenArticleEvent.OnArticleClick) {
        openWeb(requireContext(), event.article.url)
    }

    private fun showResults(viewState: ViewState) {
        if (viewState.isSearchVisible) {
            adapter.updateArticles(viewState.searchResult)
        } else {
            adapter.updateArticles(viewState.articleList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}