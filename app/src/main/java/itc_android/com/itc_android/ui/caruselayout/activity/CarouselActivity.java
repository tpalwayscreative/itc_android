package itc_android.com.itc_android.ui.caruselayout.activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import itc_android.com.itc_android.R;
import itc_android.com.itc_android.common.carousellayout.CarouselLayoutManager;
import itc_android.com.itc_android.common.carousellayout.CenterScrollListener;
import itc_android.com.itc_android.common.utils.Utils;
import itc_android.com.itc_android.model.CCarousel;
import itc_android.com.itc_android.common.carousellayout.CarouselZoomPostLayoutListener;
import itc_android.com.itc_android.model.CPalindrome;
import itc_android.com.itc_android.ui.caruselayout.adapter.CarouselAdapter;
import itc_android.com.itc_android.ui.palindrome.adapter.ResultAdapter;

public class CarouselActivity extends AppCompatActivity implements CarouselAdapter.ListenerCarouselAdapter{

    @BindView(R.id.rc_Carousel)
    RecyclerView recyclerView ;
    private Unbinder unbinder ;
    private CarouselAdapter adapter ;
    private List<CCarousel> list ;
    private CarouselLayoutManager llm ;
   // private LinearLayoutManager llm ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carousel);
        unbinder = ButterKnife.bind(this);
        setupRecyclerView();
        list = new ArrayList<>();
        for (int i = 0 ; i <= 10 ; i ++){
            list.add(new CCarousel(i+"", Utils.getRandomColor(),false));
        }
        adapter.setDataSource(list);

    }

    public void setupRecyclerView() {
        llm = new  CarouselLayoutManager(CarouselLayoutManager.VERTICAL);
        //llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnScrollListener(new CenterScrollListener());
        llm.setMaxVisibleItems(3);
        llm.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        adapter = new CarouselAdapter(getLayoutInflater(),this,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(int position) {
        if (position == llm.getCenterItemPosition()){
            Toast.makeText(getApplicationContext(),position+"",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
