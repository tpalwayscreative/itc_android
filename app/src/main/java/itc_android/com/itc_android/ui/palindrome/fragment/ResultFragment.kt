package itc_android.com.itc_android.ui.palindrome.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.util.ArrayList
import com.michaldrabik.kotlintest.utilities.extensions.dpToPx
import itc_android.com.itc_android.Constant
import itc_android.com.itc_android.R
import itc_android.com.itc_android.model.CPalindrome
import kotlinx.android.synthetic.main.fragment_result.view.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class ResultFragment : Fragment() {

    private var listenerResult: ListenerResult? = null
    @Inject lateinit var adapter: ResultAdapter
    private var list: MutableList<CPalindrome>? = null
    private var tvResult : TextView? = null

    fun setListenerResult(listenerResult: ListenerResult) {
        this.listenerResult = listenerResult
        list = ArrayList();
        adapter = ResultAdapter();
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_result, container, false)
        onClickResult(view)
        setupRecycler(view)
        return view
    }

    private fun setupRecycler(view : View) {
        tvResult = view.tvResult
        view.recyclerView!!.setHasFixedSize(true)
        view.recyclerView!!.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL, false)
        view.recyclerView!!.addItemDecoration(com.michaldrabik.kotlintest.utilities.DividerItemDecoration(activity, LinearLayoutManager.VERTICAL, 8.dpToPx(activity)))
        view.recyclerView!!.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter.clearItems();
        var bundle = Bundle()
        bundle = arguments
        val value = bundle.getString(Constant.TAG_VALUE)
        val result = bundle.getString(Constant.TAG_RESULT)
        if (result == "true") {
            tvResult!!.text = value + " : " + " is a palindrome"
        } else {
            tvResult!!.text = value + " : " + " is not a palindrome"
        }
        list!!.add(CPalindrome(value, result))
        adapter!!.addItems(list!!)
    }


    fun onClickResult(view : View) {
        view.btnResult.setOnClickListener({
            listenerResult!!.onResultAction()
        });
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    interface ListenerResult {
        fun onResultAction()
    }

}// Required empty public constructor
