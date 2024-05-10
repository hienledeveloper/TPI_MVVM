package hienle.developer.tpi_mvvm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hienle.developer.tpi_mvvm.LANGUAGES_ARRAY
import hienle.developer.tpi_mvvm.databinding.ItemLanguageBinding

/**
 * Created by Hien on 5/10/24
 */
class LanguageSelectionAdapter(
    private val langSelected: String, private val callback:
        (String)
    -> Unit
) : RecyclerView
.Adapter<LanguageSelectionAdapter
.LanguageSelectionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageSelectionViewHolder {
        val binding = ItemLanguageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LanguageSelectionViewHolder(binding)
    }

    override fun getItemCount(): Int = LANGUAGES_ARRAY.size

    override fun onBindViewHolder(holder: LanguageSelectionViewHolder, position: Int) {
        LANGUAGES_ARRAY[position].let { pair ->
            holder.bind(pair)
            holder.itemView.setOnClickListener {
                callback.invoke(pair.second)
            }
        }

    }

    inner class LanguageSelectionViewHolder(private val binding: ItemLanguageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pair: Pair<String, String>) = with(binding) {
            tvLanguage.text = pair.first
            itemSelected = pair.second.contentEquals(langSelected)
        }

    }
}