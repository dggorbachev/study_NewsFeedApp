package com.dggorbachev.newsfeedapp.feature.main_screen.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dggorbachev.newsfeedapp.R
import com.dggorbachev.newsfeedapp.base.openWeb
import com.dggorbachev.newsfeedapp.feature.main_screen.ui.adapter.ArticlesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenFragment : Fragment(R.layout.fragment_mainscreen) {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvArticles)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.viewState.observe(viewLifecycleOwner, Observer(::render))
        viewModel.openWebEvent.observe(viewLifecycleOwner, Observer(::openWebEvent))
    }

    private fun render(viewState: ViewState) {
        adapter.updateArticles(viewState.articleList)
    }

    private fun openWebEvent(event: OpenArticleEvent.OnArticleClick) {
        openWeb(requireContext(), event.article.url)
    }
}