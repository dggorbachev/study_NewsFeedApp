package com.dggorbachev.newsfeedapp.feature.bookmarks_screen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dggorbachev.newsfeedapp.R
import com.dggorbachev.newsfeedapp.databinding.FragmentBookmarksBinding
import com.dggorbachev.newsfeedapp.feature.main_screen.ui.adapter.ArticlesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarksScreenFragment : Fragment(R.layout.fragment_bookmarks) {
    private var _binding: FragmentBookmarksBinding? = null
    private val binding get() = _binding!!

    private val bookmarksScreenViewModel by viewModel<BookmarksViewModel>()
    private val newsAdapter: ArticlesAdapter by lazy {
        ArticlesAdapter(
            articleList = listOf(),
            onItemClick = { article ->
                bookmarksScreenViewModel.processUiEvent(OpenArticleEvent.OnArticleClick(article))
            },
            onBookmarkClick = { article ->
                bookmarksScreenViewModel.processUiEvent(UiEvent.OnBookmarkClick(article))
            }
        )
    }

    companion object {
        fun newInstance(): BookmarksScreenFragment {
            return BookmarksScreenFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookmarksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvArticles.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }

        bookmarksScreenViewModel.viewState.observe(viewLifecycleOwner, ::render)
//        bookmarksScreenViewModel.singleEvent.observe(viewLifecycleOwner, ::singleEvent)
    }

    private fun render(viewState: ViewState) {
        val articles = viewState.articleList.sortedByDescending { it.publishedAt }
        newsAdapter.updateArticles(articles)
    }

//    private fun singleEvent(event: OpenArticleEvent.OnArticleClick) {
//        openUrl(requireContext(), event.article.url)
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}