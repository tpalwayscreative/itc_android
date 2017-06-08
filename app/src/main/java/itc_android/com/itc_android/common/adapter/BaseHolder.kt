package itc_android.com.itc_android.common.adapter

import android.support.v7.widget.RecyclerView
import android.view.View

import butterknife.ButterKnife

open class BaseHolder<V>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    init {
        ButterKnife.bind(this, itemView)
    }

    open fun bind(data: V, position: Int) {}

    fun event() {}


}
