package hienle.developer.tpi_mvvm.ui.fragment.attraction_list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import hienle.developer.tpi_mvvm.R
import hienle.developer.tpi_mvvm.databinding.FragmentAttractionsBinding
import hienle.developer.tpi_mvvm.databinding.ItemAttractionBinding
import hienle.developer.tpi_mvvm.model.AttractionDto
import hienle.developer.tpi_mvvm.ui.component.BaseBindingFragment
import hienle.developer.tpi_mvvm.ui.fragment.attraction_detail.AttractionDetailFragment
import hienle.developer.tpi_mvvm.ui.fragment.language_selection.LanguageSelectionFragment
import hienle.developer.tpi_mvvm.ui.fragment.language_selection.LanguageSelectionListener

/**
 * Created by Hien on 5/9/24
 */
@AndroidEntryPoint
class AttractionListFragment : BaseBindingFragment<FragmentAttractionsBinding>(), OnAttractionAdapterEvent,
    LanguageSelectionListener {

    override fun getLayoutRes(): Int = R.layout.fragment_attractions

    private val viewModel: AttractionListViewModel by viewModels()
    private lateinit var attractionAdapter: AttractionAdapter
    private val totalItems = mutableListOf<AttractionDto>()
    private val languageSelectionBottomSheetFragment by lazy { LanguageSelectionFragment() }

    override fun onViewCreated() {
        with(binding) {
            rcAttraction.setHasFixedSize(true)
            attractionAdapter = AttractionAdapter(this@AttractionListFragment)
            rcAttraction.adapter = attractionAdapter
            srlRefresh.setOnRefreshListener {
                viewModel.fetch()
            }
            btSwitchLanguage.setOnClickListener {
                languageSelectionBottomSheetFragment.show(
                    childFragmentManager,
                    languageSelectionBottomSheetFragment.tag
                )
            }
        }
        observeViewModel()
    }

    override fun onPause() {
        super.onPause()
        languageSelectionBottomSheetFragment.removeBottomSheetListener()
    }

    override fun onResume() {
        super.onResume()
        languageSelectionBottomSheetFragment.setBottomSheetListener(this)
    }

    private fun observeViewModel() = with(viewLifecycleOwner) {
        viewModel.ldLanguagePage.observe(this) {
            Log.i("AttractionListFragment", "ldLanguagePage")
            binding.srlRefresh.isRefreshing = true
            viewModel.getAttractions(it)
        }
        viewModel.ldReloadItems.observe(this) {
            totalItems.clear()
            loadItemsOnRecycler(it)
            (view?.parent as? ViewGroup)?.doOnPreDraw {
                startPostponedEnterTransition()
            }
        }
        viewModel.ldMoreItems.observe(this) {
            loadItemsOnRecycler(it)
        }
        viewModel.ldCompleted.observe(this) { isCompleted ->
            if (isCompleted) {
                binding.srlRefresh.isRefreshing = false
            }
        }
    }

    private fun loadItemsOnRecycler(items: List<AttractionDto>) {
        totalItems.addAll(items)
        attractionAdapter.submitList(totalItems.toMutableList())
    }

    override fun onItemAttractionClickListener(item: AttractionDto) {
        activity?.supportFragmentManager?.commit(allowStateLoss = true) {
            setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.slide_out_right
            )
            add(R.id.fcvMain, AttractionDetailFragment.newInstance(item))
            addToBackStack(null)
        }
    }

    override fun onLoadMoreAttractionListener() {
        viewModel.loadMore()
    }

    override fun onnSwitchLanguageEvent(lang: String) {
        viewModel.flowLanguage.value = lang
        viewModel.fetch()
    }

}

class AttractionAdapter(private val callback: OnAttractionAdapterEvent) : ListAdapter<AttractionDto,
        AttractionViewHolder>(AttractionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionViewHolder {
        val binding = ItemAttractionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AttractionViewHolder(binding, callback)
    }

    override fun onBindViewHolder(holder: AttractionViewHolder, position: Int) {
        getItem(position).run {
            holder.bind(this)
        }

    }

    override fun onViewAttachedToWindow(holder: AttractionViewHolder) {
        super.onViewAttachedToWindow(holder)
        if (holder.adapterPosition > itemCount - 5) {
            callback.onLoadMoreAttractionListener()
        }
    }

}

interface OnAttractionAdapterEvent {
    fun onItemAttractionClickListener(item: AttractionDto)

    fun onLoadMoreAttractionListener()
}

class AttractionDiffCallback : DiffUtil.ItemCallback<AttractionDto>() {

    override fun areItemsTheSame(oldItem: AttractionDto, newItem: AttractionDto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AttractionDto, newItem: AttractionDto): Boolean {
        return newItem.email?.let { oldItem.email?.compareTo(it) } == 0
    }
}

class AttractionViewHolder(private val binding: ItemAttractionBinding, private val callback: OnAttractionAdapterEvent) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(data: AttractionDto) = with(binding) {
        this.data = data
        root.setOnClickListener {
            callback.onItemAttractionClickListener(data)
        }
    }
}