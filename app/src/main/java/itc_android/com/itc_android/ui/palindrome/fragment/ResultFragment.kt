package itc_android.com.itc_android.ui.palindrome.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

import java.util.ArrayList

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import itc_android.com.itc_android.Constant
import itc_android.com.itc_android.R
import itc_android.com.itc_android.model.CPalindrome


/**
 * A simple [Fragment] subclass.
 */
class ResultFragment : Fragment(), ResultAdapter.ListenerResultAdapter {

    @BindView(R.id.btnResult)
    internal var btnResult: Button? = null
    @BindView(R.id.tvResult)
    internal var tvResult: TextView? = null
    @BindView(R.id.rc_Item)
    internal var recyclerView: RecyclerView? = null
    private var listenerResult: ListenerResult? = null
    private var adapter: ResultAdapter? = null
    private var unbinder: Unbinder? = null
    private var list: MutableList<CPalindrome>? = null
    private var llm: LinearLayoutManager? = null

    fun setListenerResult(listenerResult: ListenerResult) {
        this.listenerResult = listenerResult
        list = ArrayList<CPalindrome>()

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_result, container, false)
        unbinder = ButterKnife.bind(this, view)
        setupRecyclerView()
        return view
    }

    fun setupRecyclerView() {
        llm = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView!!.layoutManager = llm
        adapter = ResultAdapter(activity.layoutInflater, activity, this)
        recyclerView!!.adapter = adapter
    }

    override fun onItemClicked(position: Int) {

    }

    override fun onResume() {
        super.onResume()
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
        adapter!!.dataSource = list
    }

    @OnClick(R.id.btnResult)
    fun onClickResult() {
        listenerResult!!.onResultAction()
    }

    override fun onDestroy() {
        super.onDestroy()
        unbinder!!.unbind()
    }

    interface ListenerResult {
        fun onResultAction()
    }

}// Required empty public constructor
