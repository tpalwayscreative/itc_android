package itc_android.com.itc_android.ui.caruselayout.activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CarouselActivity extends AppCompatActivity implements CarouselAdapter.ListenerCarouselAdapter{

    @BindView(R.id.rc_Carousel)
    RecyclerView recyclerView ;
    private Unbinder unbinder ;
    private CarouselAdapter adapter ;
    private List<CCarousel> list ;
    private CarouselLayoutManager llm ;
    public static final String TAG = CarouselActivity.class.getSimpleName();
   // private LinearLayoutManager llm ;
    private int changed =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carousel);
        unbinder = ButterKnife.bind(this);
        setupRecyclerView();
        list = new ArrayList<>();
        for (int i = 0 ; i <= 10 ; i ++){
            if (i==0){
                list.add(new CCarousel(i+"", Utils.getRandomColor(),true));
            }
            else{
                list.add(new CCarousel(i+"", Utils.getRandomColor(),false));
            }
        }
        adapter.setDataSource(list);

        llm.addOnItemSelectionListener(new CarouselLayoutManager.OnCenterItemSelectionListener() {
            @Override
            public void onCenterItemChanged(int adapterPosition) {
                //updateViews(adapterPosition);
                updatedView();
            }
        });

        llm.scrollToPosition(3);


    }


    public void updateViews(int position){
        if (list.size()>0) {
            if(position ==0){
                list.get(position).visible = true;
            }
            if (position == 0 && list.size()>1) {
                list.get(position).visible = true;
                list.get(position + 1).visible = false;
            } else if (position > 0 && position < list.size() - 1) {
                list.get(position - 1).visible = false;
                list.get(position).visible = true;
                list.get(position + 1).visible = false;
            } else {
                list.get(position - 1).visible = false;
                list.get(position).visible = true;
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void updatedView(){
        for (int i = 0 ; i < list.size() ; i++){
            if (i== llm.getCenterItemPosition()){
                list.get(i).visible = true ;
            }
            else{
                list.get(i).visible = false ;
            }
        }
        adapter.notifyDataSetChanged();
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
