package itc_android.com.itc_android.ui.palindrome.fragment

import android.content.Context
import com.michaldrabik.kotlintest.ui.base.list.BaseListAdapter
import com.michaldrabik.kotlintest.ui.base.list.BaseViewHolder
import com.michaldrabik.kotlintest.ui.main.list.PalindromeViewHolder
import itc_android.com.itc_android.model.CPalindrome
import javax.inject.Inject

/**
 * Created by mac10 on 6/8/17.
 */

class ResultAdapter @Inject constructor() : BaseListAdapter<CPalindrome>() {

    override fun getListItemView(context: Context): BaseViewHolder<CPalindrome> {
        return PalindromeViewHolder(context)
    }

}