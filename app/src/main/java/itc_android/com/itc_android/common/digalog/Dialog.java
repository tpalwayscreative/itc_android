package itc_android.com.itc_android.common.digalog;

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.Toast;

import itc_android.com.itc_android.common.controller.RealmController;

/**
 * Created by PC on 7/4/2017.
 */

public class Dialog {

    private static Dialog instance ;
    private Activity activity ;
    private ProgressDialog progressDialog ;

    public  Dialog(Activity activity){
        this.activity = activity;
    }

    public static Dialog with(Activity activity) {
        if (instance == null) {
            instance = new Dialog(activity);
        }
        return instance;
    }

    public void showDialog(String message){
        checkActivity();
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(message);
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    public void hideDialog(){
        checkDialog();
        progressDialog.hide();
    }

    private void checkActivity(){
        if (activity == null){
            return;
        }
        checkDialog();
    }

    private void checkDialog(){
        if (progressDialog ==null){
            return;
        }
    }



}
