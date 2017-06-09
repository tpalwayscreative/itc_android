package itc_android.com.itc_android.ui.palindrome.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import itc_android.com.itc_android.R
import itc_android.com.itc_android.common.utils.Utils
import kotlinx.android.synthetic.main.fragment_input.*
import kotlinx.android.synthetic.main.fragment_input.view.*
import kotlinx.android.synthetic.main.fragment_result.view.*


/**
 * A simple [Fragment] subclass.
 */
class InputFragment : Fragment() {

    private var listenerInput: ListenerInput? = null

    fun setListenerInput(listenerInput: ListenerInput) {
        this.listenerInput = listenerInput
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_input, container, false)

        view.btnInput.setOnClickListener({
            val edtValue = view.edtInput!!.text.toString()
            if (!Utils.isCheckEmpty(edtValue)) {
               val value = Utils.sanitize(edtValue)
               listenerInput!!.onInputAction(edtValue, "" + Utils.isPalindrome(value))
            }
        });

        return view
    }

    interface ListenerInput {
        fun onInputAction(input: String, result: String)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
