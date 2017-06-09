package itc_android.com.itc_android.ui.palindrome.activity

import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import itc_android.com.itc_android.Constant
import itc_android.com.itc_android.ui.palindrome.fragment.InputFragment
import itc_android.com.itc_android.R
import itc_android.com.itc_android.ui.palindrome.fragment.ResultFragment
import itc_android.com.itc_android.common.utils.Utils

class MainActivity : AppCompatActivity(), InputFragment.ListenerInput, ResultFragment.ListenerResult {

    private var inputFragment: InputFragment? = null
    private var resultFragment: ResultFragment? = null
    private var fragmentManager: FragmentManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inputFragment = InputFragment()
        fragmentManager = supportFragmentManager
        inputFragment!!.setListenerInput(this)
        resultFragment = ResultFragment()
        resultFragment!!.setListenerResult(this)
        onDefault()

    }

    fun onDefault() {
        fragmentManager!!.beginTransaction()
                .replace(R.id.flMain, inputFragment, inputFragment!!.tag)
                .commit()
    }


    override fun onInputAction(input: String, result: String) {
        val bundle = Bundle()
        bundle.putString(Constant.TAG_VALUE, input)
        bundle.putString(Constant.TAG_RESULT, result)
        resultFragment!!.arguments = bundle
        fragmentManager!!.beginTransaction()
                .replace(R.id.flMain, resultFragment, resultFragment!!.tag)
                .commit()
    }

    override fun onResultAction() {
        fragmentManager!!.beginTransaction()
                .replace(R.id.flMain, inputFragment, inputFragment!!.tag)
                .commit()
    }

}
