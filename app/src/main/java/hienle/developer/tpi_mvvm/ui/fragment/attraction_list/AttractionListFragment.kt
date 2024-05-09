package hienle.developer.tpi_mvvm.ui.fragment.attraction_list

import android.view.LayoutInflater
import android.view.ViewGroup
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

/**
 * Created by Hien on 5/9/24
 */
@AndroidEntryPoint
class AttractionListFragment : BaseBindingFragment<FragmentAttractionsBinding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_attractions

    private val viewModel: AttractionListViewModel by viewModels()
    private lateinit var attractionAdapter: AttractionAdapter

    override fun onViewCreated() {
        with(binding) {
            rcAttraction.setHasFixedSize(true)
            attractionAdapter = AttractionAdapter {

            }
            rcAttraction.adapter = attractionAdapter
        }
        viewModel.ldLanguagePage.observe(viewLifecycleOwner) {
            viewModel.getAttractions(it)
        }
        viewModel.ldReloadItems.observe(viewLifecycleOwner) {
            attractionAdapter.submitList(it)
        }
    }
}

class AttractionAdapter(private val itemClickListener: (AttractionDto) -> Unit) : ListAdapter<AttractionDto,
        AttractionViewHolder>(AttractionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionViewHolder {
        val binding = ItemAttractionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AttractionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AttractionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class AttractionDiffCallback : DiffUtil.ItemCallback<AttractionDto>() {

    override fun areItemsTheSame(oldItem: AttractionDto, newItem: AttractionDto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AttractionDto, newItem: AttractionDto): Boolean {
        return newItem.email?.let { oldItem.email?.compareTo(it) } == 0
    }
}

class AttractionViewHolder(private val binding: ItemAttractionBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: AttractionDto) {
        binding.data = data
    }
}