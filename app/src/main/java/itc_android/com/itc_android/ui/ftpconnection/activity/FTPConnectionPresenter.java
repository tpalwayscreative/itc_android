package itc_android.com.itc_android.ui.ftpconnection.activity;
import android.view.View;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.telnet.TelnetClient;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import itc_android.com.itc_android.R;
import itc_android.com.itc_android.common.presenter.Presenter;
import rx.Observable;
import rx.Subscriber;
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

       Observable.create(subscriber -> {
            subscriber.onNext("");
            subscriber.onCompleted();
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .subscribe(data ->{

                });

        FTPConnectionView view = view();
        view.showLoading();
        ftp = new FTPClient();

        String username = view.getUsername();
        String password = view.getPassword();

        if(username.isEmpty()){view.onShowErrorPassword(R.string.tv_username);
            return;
        }
        else if(password.isEmpty()){
            view.onShowErrorPassword(R.string.tv_password);
            return;
        }
        try {
            ftp.setConnectTimeout(10 * 1000);
            ftp.connect(InetAddress.getByName("31.170.165.108"),21);
            if (FTPReply.isPositiveCompletion(ftp.getReply())){
                boolean login = ftp.login(username,password);
                System.out.println(" login "+ login );
                showLog(tagName," login "+ login );
            }
            view.hideLoading();
        }
        catch (UnknownHostException e) {
            System.err.println("ERROR :: FTP Server Unreachable");
            sleep();
            connect();
        }  catch (IOException e) {
            System.err.println("ERROR :: FTP Server Unreachable");
            sleep();
            connect();
        }
    }

    public void sleep(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setTagName(String tagName){
        this.tagName = tagName ;
    }

}
