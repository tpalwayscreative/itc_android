package itc_android.com.itc_android.ui.caruselayout.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import itc_android.com.itc_android.R;
import itc_android.com.itc_android.common.adapter.BaseAdapter;
import itc_android.com.itc_android.common.adapter.BaseHolder;
import itc_android.com.itc_android.common.carousellayout.CarouselLayoutManager;
import itc_android.com.itc_android.common.utils.PixelGridView;
import itc_android.com.itc_android.model.CCarousel;
import itc_android.com.itc_android.model.CPalindrome;

/**
 * Created by mac10 on 6/8/17.
 */

public class CarouselAdapter extends BaseAdapter<CCarousel,BaseHolder> {

    private ListenerCarouselAdapter listenerCarouselAdapter ;
    private Activity activity ;
    public static final String TAG= CarouselAdapter.class.getSimpleName();

    public CarouselAdapter(LayoutInflater inflater, Activity activity, ListenerCarouselAdapter listenerCarouselAdapter) {
        super(inflater);
        this.activity = activity;
        this.listenerCarouselAdapter = listenerCarouselAdapter;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PortfoliosHolder(inflater.inflate(R.layout.carousel_items, parent, false));
    }

        public class PortfoliosHolder extends BaseHolder<CCarousel>  {

            @BindView(R.id.llCarousel)
            LinearLayout llCarousel ;
            @BindView(R.id.tvCount)
            TextView tvCount;
            @BindView(R.id.llItems)
            LinearLayout llItems ;
            @BindView(R.id.imgColorMain)
            ImageView imgMainColor ;
            private CCarousel cCarousel ;
            private int position ;
            public PortfoliosHolder(View itemView) {
                super(itemView);
            }

        @Override
        public void bind(CCarousel data, int position) {
            super.bind(data, position);
            this.position = position ;
            tvCount.setText(data.id);
            PixelGridView pixelGrid = new PixelGridView(activity);
            pixelGrid.setNumColumns(2);
            pixelGrid.setNumRows(5);
            llItems.addView(pixelGrid);
            imgMainColor.setVisibility(data.visible ? View.VISIBLE : View.INVISIBLE);
            Log.d(TAG,position+"");
            //imgColor.setVisibility(data.visible ? View.VISIBLE : View.INVISIBLE);
        }

        @OnClick(R.id.llCarousel)
        public void onSelected(){
            listenerCarouselAdapter.onItemClicked(position);
        }
    }




    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public interface ListenerCarouselAdapter {
        void onItemClicked(int position);
    }

}
