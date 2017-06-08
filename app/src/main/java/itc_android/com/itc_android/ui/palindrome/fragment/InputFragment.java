package itc_android.com.itc_android.ui.palindrome.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.nio.Buffer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import itc_android.com.itc_android.R;
import itc_android.com.itc_android.common.utils.Utils;


/**
 * A simple {@link Fragment} subclass.
 */
public class InputFragment extends Fragment {

    private ListenerInput listenerInput ;
    @BindView(R.id.btnInput)
    Button btnInput ;
    @BindView(R.id.edtInput)
    EditText editText ;
    private Unbinder unbinder;

    public InputFragment(){

    }

    public void setListenerInput(ListenerInput listenerInput){
        this.listenerInput = listenerInput;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_input,container,false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.btnInput)
    public void onClickInput(){
        String edtValue = editText.getText().toString() ;
        if (!Utils.isCheckEmpty(edtValue)){
            String value = Utils.sanitize(edtValue);
            listenerInput.onInputAction(editText.getText().toString(),""+Utils.isPalindrome(value));
        }

    }

    public interface ListenerInput {
        void onInputAction(String input,String result);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
