package itc_android.com.itc_android.ui.histories.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import itc_android.com.itc_android.R;
import itc_android.com.itc_android.common.controller.RealmController;
import itc_android.com.itc_android.common.realm.Palindrome;
import itc_android.com.itc_android.model.CPalindrome;
import itc_android.com.itc_android.ui.palindrome.adapter.ResultAdapter;

public class HistoriesActivity extends AppCompatActivity implements ResultAdapter.ListenerResultAdapter {

    @BindView(R.id.rc_Histories)
    RecyclerView recyclerView ;
    private Unbinder unbinder ;
    private ResultAdapter adapter ;
    private List<CPalindrome> list ;
    private LinearLayoutManager llm ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histories);
        unbinder = ButterKnife.bind(this);
        setupRecyclerView();
        list = new ArrayList<CPalindrome>();
        for (Palindrome index:  RealmController.with(this).getBooks()){
            list.add(new CPalindrome(index.getId(),index.getValue(),index.getResult()));
        }
        adapter.setDataSource(list);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public void setupRecyclerView() {
        llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(llm);
        adapter = new ResultAdapter(getLayoutInflater(),this,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(int position) {
        Toast.makeText(getApplicationContext(), "Position : "
                + position, Toast.LENGTH_SHORT).show();
    }
}
