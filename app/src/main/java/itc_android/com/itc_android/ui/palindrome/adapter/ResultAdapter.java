package itc_android.com.itc_android.ui.palindrome.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import itc_android.com.itc_android.R;
import itc_android.com.itc_android.common.adapter.BaseAdapter;
import itc_android.com.itc_android.common.adapter.BaseHolder;
import itc_android.com.itc_android.model.CPalindrome;

/**
 * Created by mac10 on 6/8/17.
 */

public class ResultAdapter extends BaseAdapter<CPalindrome,BaseHolder> {

    private ListenerResultAdapter listenerResultAdapter ;
    private Activity activity ;

    public ResultAdapter(LayoutInflater inflater, Activity activity, ListenerResultAdapter listenerResultAdapter) {
        super(inflater);
        this.activity = activity;
        this.listenerResultAdapter = listenerResultAdapter;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PortfoliosHolder(inflater.inflate(R.layout.item_result, parent, false));
    }

    public class PortfoliosHolder extends BaseHolder<CPalindrome>  {

        @BindView(R.id.lnHomeItem)
        LinearLayout linearLayout;
        @BindView(R.id.tvValue)
        TextView tvValue ;
        @BindView(R.id.tvResult)
        TextView tvResult ;

        private CPalindrome cPalindrome ;
        private int position ;

        public PortfoliosHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.lnHomeItem);
        }

        @Override
        public void bind(CPalindrome data, int position) {
            super.bind(data, position);
            this.position = position ;
            tvValue.setText(data.value + "  ===>  ");
            tvResult.setText(data.result);
        }

        public void showName(){

        }

        @OnClick(R.id.lnHomeItem)
        public void onSelected(){
            listenerResultAdapter.onItemClicked(position);
        }

    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public interface ListenerResultAdapter {
        void onItemClicked(int position);
    }


}
