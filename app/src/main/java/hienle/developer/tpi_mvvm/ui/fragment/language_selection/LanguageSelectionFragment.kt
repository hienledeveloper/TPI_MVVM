package hienle.developer.tpi_mvvm.ui.fragment.language_selection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import hienle.developer.tpi_mvvm.R
import hienle.developer.tpi_mvvm.core.preference.UserSharedPreferences
import hienle.developer.tpi_mvvm.databinding.FragmentLanguageSelectionBinding
import hienle.developer.tpi_mvvm.ui.adapter.LanguageSelectionAdapter
import javax.inject.Inject

/**
 * Created by Hien on 5/10/24
 */
@AndroidEntryPoint
class LanguageSelectionFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentLanguageSelectionBinding

    @Inject
    lateinit var sharedPreferences: UserSharedPreferences
    private var bottomSheetListener: LanguageSelectionListener? = null
    private var adapter: LanguageSelectionAdapter? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_language_selection,
            container,
            false
        )
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = LanguageSelectionAdapter(sharedPreferences.getLanguage()) { lang ->
            sharedPreferences.setLanguage(lang)
            bottomSheetListener?.onnSwitchLanguageEvent(lang)
            dismiss()
        }
        with(binding) {
            rvLanguage.setHasFixedSize(true)
            rvLanguage.adapter = adapter
        }
    }

    // Function to set the listener
    fun setBottomSheetListener(listener: LanguageSelectionListener) {
        bottomSheetListener = listener
    }

    // Function to remove the listener
    fun removeBottomSheetListener() {
        bottomSheetListener = null
    }

}