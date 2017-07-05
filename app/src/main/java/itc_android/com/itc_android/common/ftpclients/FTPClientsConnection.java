package itc_android.com.itc_android.common.ftpclients;
import android.util.Log;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by PC on 7/4/2017.
 */

public class FTPClientsConnection {

    static private FTPClientsConnection instance ;
    private FTPClient ftpClient ;
    private ListenerFTPConnection listenerFTPConnection ;

    public static FTPClientsConnection with(ListenerFTPConnection listenerFTPConnection){
        if (instance == null){
            instance = new FTPClientsConnection();
            instance.listenerFTPConnection = listenerFTPConnection;
        }
        return instance ;
    }

    public void connectToFTPClient(String username,String password){
        if (ftpClient == null) {
            ftpClient = new FTPClient();

            Observable.create(subscriber -> {
                try {
                    ftpClient.setConnectTimeout(10 * 1000);
                    ftpClient.connect(InetAddress.getByName("ftp.tpalwayscreative.esy.es"), 21);
                    ftpClient.login(username, password);
                    ftpClient.enterLocalPassiveMode();
                    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

                    FTPFile[] ftpDirs = ftpClient.listDirectories();
                    String result = "" ;

                    for (int i = 0; i < ftpDirs.length; i++) {

                        result += ftpDirs[i].getName() + "\n" ;
                    }
                    listenerFTPConnection.onShowList(result);

                } catch (UnknownHostException e) {
                    System.err.println("ERROR :: FTP Server Unreachable");
                    listenerFTPConnection.onError("ERROR :: FTP Server Unreachable");
                } catch (IOException e) {
                    System.err.println("ERROR :: FTP Server Unreachable");
                    listenerFTPConnection.onError("ERROR :: FTP Server Unreachable");
                }
                subscriber.onNext(ftpClient);
                subscriber.onCompleted();
            }).observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.computation())
                    .subscribe(response -> {
                        this.ftpClient = (FTPClient) response;
                        listenerFTPConnection.onFTPConnection(ftpClient);
                    });
        }
        else{
            listenerFTPConnection.onFTPConnection(ftpClient);
        }
    }





    public interface ListenerFTPConnection {
        void onFTPConnection(FTPClient ftpClient);
        void onError(String message);
        void onShowList(String string);
    }
}
