package itc_android.com.itc_android.ui.home.adapter;

import android.app.Activity;
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
import itc_android.com.itc_android.model.CCarousel;
import itc_android.com.itc_android.model.CHome;
import itc_android.com.itc_android.ui.caruselayout.adapter.CarouselAdapter;

/**
 * Created by PC on 7/5/2017.
 */

public class HomeAdapter  extends BaseAdapter<CHome,BaseHolder> {

    private ListenerHomeAdapter listenerHomeAdapter ;
    private Activity activity ;

    public HomeAdapter(LayoutInflater inflater, Activity activity, ListenerHomeAdapter listenerHomeAdapter) {
        super(inflater);
        this.activity = activity;
        this.listenerHomeAdapter = listenerHomeAdapter;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PortfoliosHolder(inflater.inflate(R.layout.home_items, parent, false));
    }

    public class PortfoliosHolder extends BaseHolder<CHome>  {

        @BindView(R.id.llHome)
        LinearLayout llHome ;
        @BindView(R.id.tvName)
        TextView tvName;


        private CCarousel cCarousel ;
        private int position ;

        public PortfoliosHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(CHome data, int position) {
            super.bind(data, position);
            this.position = position ;
            tvName.setText(data.name);
        }

        @OnClick(R.id.llHome)
        public void onSelected(){
            listenerHomeAdapter.onItemClicked(position);
        }
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public interface ListenerHomeAdapter {
        void onItemClicked(int position);
    }

}

