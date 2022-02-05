package dev.jgm.pokedex.ui.pokemondetail.sections

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragmentSection(@LayoutRes layoutId: Int): Fragment(layoutId) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragment(view)
    }

    protected abstract fun initFragment(view: View)

    abstract fun getTitleId(): Int
}