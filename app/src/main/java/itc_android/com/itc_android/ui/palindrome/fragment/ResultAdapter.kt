package itc_android.com.itc_android.ui.palindrome.fragment

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

import butterknife.BindView
import butterknife.OnClick
import itc_android.com.itc_android.R
import itc_android.com.itc_android.common.adapter.BaseHolder
import itc_android.com.itc_android.model.CPalindrome

/**
 * Created by mac10 on 6/8/17.
 */

class ResultAdapter(inflater: LayoutInflater, private val activity: Activity, private val listenerResultAdapter: ResultAdapter.ListenerResultAdapter) : BaseAdapter<CPalindrome, BaseHolder<*>>(inflater) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<*>? {
        return PortfoliosHolder(inflater.inflate(R.layout.item_result, parent, false))
    }

    inner class PortfoliosHolder(itemView: View) : BaseHolder<CPalindrome>(itemView) {

        @BindView(R.id.lnHomeItem)
        internal var linearLayout: LinearLayout
        @BindView(R.id.tvValue)
        internal var tvValue: TextView? = null
        @BindView(R.id.tvResult)
        internal var tvResult: TextView? = null

        private val cPalindrome: CPalindrome? = null
        private var positions: Int = 0


        init {
            linearLayout = itemView.findViewById(R.id.lnHomeItem) as LinearLayout
        }

        override fun bind(data: CPalindrome, position: Int) {
            super.bind(data, position)
            this.positions = position
            tvValue!!.text = data.value + "  ===>  "
            tvResult!!.text = data.result
        }

        @OnClick(R.id.lnHomeItem)
        fun onSelected() {
            listenerResultAdapter.onItemClicked(position)
        }

    }

    override fun getItemCount(): Int {
        return dataSource.size
    }


    interface ListenerResultAdapter {
        fun onItemClicked(position: Int)
    }


}
