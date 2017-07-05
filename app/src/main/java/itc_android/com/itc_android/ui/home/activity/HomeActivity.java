package itc_android.com.itc_android.ui.home.activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import itc_android.com.itc_android.R;
import itc_android.com.itc_android.model.CCarousel;
import itc_android.com.itc_android.model.CHome;
import itc_android.com.itc_android.ui.caruselayout.activity.CarouselActivity;
import itc_android.com.itc_android.ui.caruselayout.adapter.CarouselAdapter;
import itc_android.com.itc_android.ui.home.adapter.HomeAdapter;
import itc_android.com.itc_android.ui.palindrome.activity.MainActivity;

public class HomeActivity extends AppCompatActivity implements HomeAdapter.ListenerHomeAdapter {

    @BindView(R.id.rc_Home)
    RecyclerView recyclerView ;
    private Unbinder unbinder ;
    private HomeAdapter adapter ;
    private List<CHome> list ;
    private LinearLayoutManager llm ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        unbinder = ButterKnife.bind(this);
        setupRecyclerView();
        list = new ArrayList<>();
        list.add(new CHome(0,"Main activity"));
        list.add(new CHome(1,"Carousel"));
        adapter.setDataSource(list);
    }

    public void setupRecyclerView() {
        llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(llm);
        adapter = new HomeAdapter(getLayoutInflater(),this,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(int position) {
        switch (position){
            case 0 :
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                break;
            case 1 :
                Intent is = new Intent(getApplicationContext(), CarouselActivity.class);
                startActivity(is);
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
