package itc_android.com.itc_android.ui.palindrome.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import itc_android.com.itc_android.R;
import itc_android.com.itc_android.common.utils.Utils;
import itc_android.com.itc_android.ui.ftpconnection.activity.FTPConnectionActivity;
import itc_android.com.itc_android.ui.histories.activity.HistoriesActivity;
import itc_android.com.itc_android.ui.printtxt.PrintFileActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class InputFragment extends Fragment {

    private ListenerInput listenerInput ;
    @BindView(R.id.btnInput)
    Button btnInput ;
    @BindView(R.id.btnShowHistories)
    Button btnShowHistories ;
    @BindView(R.id.btnConnectFTP)
    Button btnConnectingToFTP ;
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

    @OnClick(R.id.btnShowHistories)
    public void onClickShowHistories(){
        Intent i = new Intent(getContext(), HistoriesActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btnPrintTxt)
    public void onPrintFileTxt(){
        Intent i = new Intent(getContext(), PrintFileActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btnConnectFTP)
    public void onClickConnectingToFTP(){
        Intent i = new Intent(getContext(), FTPConnectionActivity.class);
        startActivity(i);
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
