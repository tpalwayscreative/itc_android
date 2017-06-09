package com.michaldrabik.kotlintest.ui.main.list

import android.content.Context
import com.michaldrabik.kotlintest.ui.base.list.BaseViewHolder
import itc_android.com.itc_android.model.CPalindrome
import itc_android.com.itc_android.R
import kotlinx.android.synthetic.main.item_result.view.*

class PalindromeViewHolder(context: Context) : BaseViewHolder<CPalindrome>(context) {

  override fun layoutResId(): Int = R.layout.item_result

  override fun bind(item: CPalindrome) {
    tvValue.text = item.value
    tvResult.text = item.result
  }

}
