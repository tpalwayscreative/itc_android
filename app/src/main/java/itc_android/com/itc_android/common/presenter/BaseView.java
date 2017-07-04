package itc_android.com.itc_android.common.presenter;
import android.content.Context;

/**
 * Created by PC on 7/4/2017.
 */

public interface BaseView {
    void showLoading();
    void hideLoading();
    Context getContext();
}


