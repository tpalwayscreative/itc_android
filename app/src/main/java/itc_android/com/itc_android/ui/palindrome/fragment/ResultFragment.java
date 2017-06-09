package itc_android.com.itc_android.ui.palindrome.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import itc_android.com.itc_android.Constant;
import itc_android.com.itc_android.R;
import itc_android.com.itc_android.model.CPalindrome;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment implements ResultAdapter.ListenerResultAdapter {

    @BindView(R.id.btnResult)
    Button btnResult ;
    @BindView(R.id.tvResult)
    TextView tvResult ;
    @BindView(R.id.rc_Item)
    RecyclerView recyclerView ;
    private ListenerResult listenerResult ;
    private ResultAdapter adapter ;
    private Unbinder unbinder;
    private List<CPalindrome> list ;
    private LinearLayoutManager llm ;

    public ResultFragment() {
        // Required empty public constructor
    }

    public void setListenerResult(ListenerResult listenerResult){
        this.listenerResult = listenerResult ;
        list = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_result, container, false);
        unbinder = ButterKnife.bind(this,view);
        setupRecyclerView();
        return  view ;
    }

    public void setupRecyclerView() {
        llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(llm);
        adapter = new ResultAdapter(getActivity().getLayoutInflater(),getActivity(), this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = new Bundle();
        bundle = getArguments();
        String value = bundle.getString(Constant.TAG_VALUE);
        String result = bundle.getString(Constant.TAG_RESULT);
        if (result.equals("true")){
            tvResult.setText(value +" : "+" is a palindrome");
        }
        else{
            tvResult.setText(value +" : "+" is not a palindrome");
        }
        list.add(new CPalindrome(value,result));
        Collections.reverse(list);
        adapter.setDataSource(list);
    }

    @OnClick(R.id.btnResult)
    public void onClickResult(){
        listenerResult.onResultAction();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public interface ListenerResult {
        void onResultAction();
    }

}
