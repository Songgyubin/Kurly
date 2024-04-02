package com.gyub.kurly.ui.section

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gyub.core.common.util.extension.orDefault
import com.gyub.kurly.R
import com.gyub.kurly.databinding.ActivityMainBinding
import com.gyub.kurly.ui.section.SectionViewModel.SectionViewState.Error
import com.gyub.kurly.ui.section.SectionViewModel.SectionViewState.Loading
import com.gyub.kurly.ui.section.SectionViewModel.SectionViewState.Success
import com.gyub.kurly.ui.section.adapter.SectionsAdapter
import com.gyub.kurly.ui.section.adapter.SectionsAdapter.Companion.PREFETCH
import com.gyub.kurly.util.extension.dp
import com.gyub.kurly.util.view.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * SectionActivity
 *
 * @author   Gyub
 * @created  2024/03/30
 */
@AndroidEntryPoint
class SectionActivity : AppCompatActivity() {
    private val sectionViewModel: SectionViewModel by viewModels()

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@SectionActivity
        }
    }

    private val sectionsAdapter: SectionsAdapter by lazy { SectionsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setLayout()
        sectionViewModel.fetchSections()
        collect()
    }

    /**
     * 레이아웃 세팅
     */
    private fun setLayout() {
        setSectionsRecyclerView()
        setUpRefreshLayout()
    }

    /**
     * Section RecyclerView 세팅
     */
    private fun setSectionsRecyclerView() {
        binding.recyclerViewSections.run {
            adapter = sectionsAdapter

            addItemDecoration(
                DividerItemDecoration(
                    context,
                    dividerHeight = 5.dp,
                    R.color.gray,
                    spaceHeight = 20.dp
                )
            )

            itemAnimator = null

            addOnScrollListener(scrollListener)

            setHasFixedSize(true)
        }
    }

    /**
     * SwipeRefreshLayout 설정, 새로 고침(Refresh) 동작 처리
     */
    private fun setUpRefreshLayout() {
        binding.swipeRefreshLayout.run {
            setProgressViewOffset(false, 0, 40.dp)

            setOnRefreshListener {
                refreshSections()
            }
        }
    }

    /**
     * Section 새로고침
     */
    private fun refreshSections() {
        sectionsAdapter.submitList(null)
        sectionViewModel.fetchSections()
    }

    /**
     * collect
     */
    private fun collect() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                collectCombinedSection()
            }
        }
    }

    /**
     * 결합된 섹션 collect
     */
    private suspend fun collectCombinedSection() {
        sectionViewModel.sectionViewState.collectLatest { sectionViewState ->
            when (sectionViewState) {
                is Success -> {
                    sectionsAdapter.submitList(sectionViewState.combinedSections)
                    binding.swipeRefreshLayout.isRefreshing = false
                    sectionViewModel.saveCurrentSectionsForPaging(sectionViewState)
                }

                is Loading -> {
                    binding.swipeRefreshLayout.isRefreshing = true
                }

                is Error -> {
                    binding.swipeRefreshLayout.isRefreshing = false
                    Toast.makeText(this@SectionActivity, getString(R.string.unexpected_error_message), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    /**
     * 다음 페이지 요청
     */
    private fun loadNextPage() {
        sectionViewModel.run {
            val currentState = sectionViewState.value
            if (currentState !is Success) {
                return
            }

            fetchSections(isFirstPage = false, nextPage = currentState.nextPage)
        }
    }

    /**
     * 다음 페이지 로드 여부 결정
     *
     * @param attachedItemCount 현재 어댑터 연결된 아이템 수
     * @param lastVisibleItem 마지막으로 보이는 아이템의 인덱스
     * @return 다음 페이지 로드해야 하는 경우 true, 그렇지 않으면 false
     */
    private fun shouldLoadNextPage(attachedItemCount: Int, lastVisibleItem: Int): Boolean {
        val currentState = sectionViewModel.sectionViewState.value
        if (currentState !is Success) {
            return false
        }
        return currentState.nextPage > 0 && isEndOfList(attachedItemCount, lastVisibleItem)
    }

    /**
     * 리스트 끝 도달 여부 판단
     *
     * @param attachedItemCount 현재 어댑터 연결된 아이템 수
     * @param lastVisibleItem 마지막으로 보이는 아이템의 인덱스
     * @return 리스트 끝에 도달 했을 경우 true, 그렇지 않으면 false
     */
    private fun isEndOfList(attachedItemCount: Int, lastVisibleItem: Int): Boolean {
        return (lastVisibleItem + PREFETCH) >= attachedItemCount
    }


    /**
     * SwipeRefreshLayout 활성/비활성 상태를 업데이트
     *
     * @param verticalScrollOffset 수직 스크롤 오프셋 값
     */
    private fun updateEnabledSwipeRefreshLayout(verticalScrollOffset: Int) {
        binding.swipeRefreshLayout.run {
            if (isRefreshing) {
                return
            }

            isEnabled = verticalScrollOffset == 0
        }
    }

    /**
     * RecyclerView Scroll Listener
     */
    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val layoutManager = recyclerView.layoutManager as? LinearLayoutManager ?: return

            val verticalScrollOffset = recyclerView.computeVerticalScrollOffset().orDefault()
            val attachedItemCount = layoutManager.itemCount.orDefault()
            val lastVisibleItem = layoutManager.findLastVisibleItemPosition().orDefault()

            updateEnabledSwipeRefreshLayout(verticalScrollOffset)

            if (shouldLoadNextPage(attachedItemCount, lastVisibleItem)) {
                loadNextPage()
            }

        }
    }
}