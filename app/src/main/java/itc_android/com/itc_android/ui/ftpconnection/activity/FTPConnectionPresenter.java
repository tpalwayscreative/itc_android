package itc_android.com.itc_android.ui.ftpconnection.activity;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import itc_android.com.itc_android.R;
import itc_android.com.itc_android.common.ftpclients.FTPClientsConnection;
import itc_android.com.itc_android.common.presenter.Presenter;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by PC on 7/4/2017.
 */

public class FTPConnectionPresenter extends Presenter<FTPConnectionView> {

    private FTPClient ftp = null;
    private String tagName ;

    /*
       Resolve main thread

    *  StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
    *
    *
    *
    * */

    public void connect()
    {
        FTPConnectionView view = view();
        String username = view.getUsername();
        String password = view.getPassword();
        if (username.isEmpty()){
            view.onShowErrorUsername(R.string.tv_username);
            return;
        }
        else if(password.isEmpty()){
            view.onShowErrorPassword(R.string.tv_password);
            return;
        }
        view.showLoading();
        FTPClientsConnection.with(new FTPClientsConnection.ListenerFTPConnection() {
            @Override
            public void onError(String message) {
              view.hideLoading();
            }
            @Override
            public void onFTPConnection(FTPClient ftpClient) {
                showLog(tagName,ftpClient.getReplyString());
                ftp = ftpClient;
                view.hideLoading();
            }
            @Override
            public void onShowList(String string) {
                showLog(tagName,string.toString());

            }
        }).connectToFTPClient(username,password);

    }

    public void setTagName(String tagName){
        this.tagName = tagName ;
    }

}
