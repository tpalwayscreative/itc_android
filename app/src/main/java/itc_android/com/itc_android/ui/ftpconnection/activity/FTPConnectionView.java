package itc_android.com.itc_android.ui.ftpconnection.activity;
import itc_android.com.itc_android.common.presenter.BaseView;

/**
 * Created by PC on 7/4/2017.
 */

public interface FTPConnectionView extends BaseView {

    void onConnection();
    void onGettingInfo();
    String getUsername();
    String getPassword();
    void onShowErrorUsername(int resId);
    void onShowErrorPassword(int resId);

}

