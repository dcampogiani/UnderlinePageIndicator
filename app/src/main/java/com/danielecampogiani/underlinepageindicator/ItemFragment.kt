package com.danielecampogiani.underlinepageindicator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_item.*

private const val ITEM = "item"

/**
 * A simple [Fragment] subclass.
 * Use the [ItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ItemFragment : Fragment() {
    private val name: String by lazy {
        requireArguments().getParcelable<Item>(ITEM)!!.name
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text.text = name
    }

    companion object {

        operator fun invoke(item: Item) = newInstance(item)

        fun newInstance(item: Item) =
            ItemFragment().apply {
                arguments = Bundle(1).apply {
                    putParcelable(ITEM, item)
                }
            }
    }
}