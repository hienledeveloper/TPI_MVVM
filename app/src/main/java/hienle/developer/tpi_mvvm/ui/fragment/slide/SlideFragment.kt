package hienle.developer.tpi_mvvm.ui.fragment.slide

import androidx.core.os.bundleOf
import hienle.developer.tpi_mvvm.R
import hienle.developer.tpi_mvvm.databinding.FragmentSlideBinding
import hienle.developer.tpi_mvvm.ui.component.BaseBindingFragment

/**
 * Created by Hien on 5/10/24
 */
class SlideFragment : BaseBindingFragment<FragmentSlideBinding>() {

    companion object {
        private const val IMAGE_URL = "image_url"
        fun newInstance(imageUrl: String) = SlideFragment().apply {
            arguments = bundleOf(IMAGE_URL to imageUrl)
        }
    }

    override fun getLayoutRes(): Int = R.layout.fragment_slide

    override fun onViewCreated() {
        arguments?.getString(IMAGE_URL)?.let { imageUrl ->
            binding.imageUrl = imageUrl
        }
    }
}