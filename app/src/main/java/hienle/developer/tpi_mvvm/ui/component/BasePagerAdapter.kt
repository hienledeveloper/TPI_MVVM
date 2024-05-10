package hienle.developer.tpi_mvvm.ui.component

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import hienle.developer.tpi_mvvm.ui.fragment.slide.SlideFragment

/**
 * Created by Hien on 5/10/24
 */
class SlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val items = mutableListOf<SlideFragment>()

    fun fetchFragments(fragments: List<SlideFragment>) {
        items.clear()
        items.addAll(fragments)
        notifyDataSetChanged()
    }

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): SlideFragment {
        return items[position]
    }

}