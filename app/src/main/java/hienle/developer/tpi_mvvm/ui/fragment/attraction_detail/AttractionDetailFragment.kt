package hienle.developer.tpi_mvvm.ui.fragment.attraction_detail

import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.commit
import hienle.developer.tpi_mvvm.R
import hienle.developer.tpi_mvvm.databinding.FragmentAttractionDetailBinding
import hienle.developer.tpi_mvvm.model.AttractionDto
import hienle.developer.tpi_mvvm.ui.component.AlphaAndScalePageTransformer
import hienle.developer.tpi_mvvm.ui.component.BaseBindingFragment
import hienle.developer.tpi_mvvm.ui.component.SlidePagerAdapter
import hienle.developer.tpi_mvvm.ui.fragment.slide.SlideFragment
import hienle.developer.tpi_mvvm.ui.fragment.web.WebFragment
import hienle.developer.tpi_mvvm.util.dp2Px
import hienle.developer.tpi_mvvm.util.loadUrl

/**
 * Created by Hien on 5/10/24
 */
class AttractionDetailFragment : BaseBindingFragment<FragmentAttractionDetailBinding>() {

    companion object {

        private const val ATTRACTION_DATA = "attraction_data"

        fun newInstance(data: AttractionDto) = AttractionDetailFragment().apply {
            arguments = bundleOf(
                ATTRACTION_DATA to data
            )
        }
    }

    override fun getLayoutRes(): Int = R.layout.fragment_attraction_detail

    private val mPagerAdapter by lazy { SlidePagerAdapter(childFragmentManager) }

    override fun onViewCreated() {
        (arguments?.getParcelable(ATTRACTION_DATA) as? AttractionDto)?.let { data ->
            binding.data = data
            with(binding) {
                ibClose.setOnClickListener {
                    activity?.onBackPressed()
                }
                tvReferenceUrl.setOnClickListener {
                    activity?.supportFragmentManager?.commit(allowStateLoss = true) {
                        setCustomAnimations(
                            android.R.anim.slide_in_left,
                            android.R.anim.fade_out,
                            android.R.anim.fade_in,
                            android.R.anim.slide_out_right
                        )
                        add(R.id.fcvMain, WebFragment.newInstance(data.url.orEmpty()))
                        addToBackStack(null)
                    }
                }
                if (!data.images.isNullOrEmpty()) {
                    ivImage.isVisible = false
                    vpImages.adapter = mPagerAdapter
                    vpImages.offscreenPageLimit = data.images.size
                    vpImages.setPageTransformer(true, AlphaAndScalePageTransformer())
                    vpImages.pageMargin = 8.dp2Px()

                    data.images.map { SlideFragment.newInstance(it.imageUrl) }.let { slideFragments ->
                        mPagerAdapter.fetchFragments(slideFragments)
                    }
                } else {
                    ivImage.isVisible = true
                    data.images?.getOrNull(0)?.imageUrl.let { imageUrl ->
                        ivImage.loadUrl(imageUrl)
                    }
                }


            }
        }

    }
}