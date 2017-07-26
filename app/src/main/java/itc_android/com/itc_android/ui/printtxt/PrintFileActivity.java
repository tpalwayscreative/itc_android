package itc_android.com.itc_android.ui.printtxt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import itc_android.com.itc_android.Constant;
import itc_android.com.itc_android.R;
import itc_android.com.itc_android.common.controller.RealmController;
import itc_android.com.itc_android.common.realm.Palindrome;
import itc_android.com.itc_android.common.utils.FileUtil;
import itc_android.com.itc_android.model.CPalindrome;

public class PrintFileActivity extends AppCompatActivity implements FileUtil.ListenerFileUtil {

    private RealmController realmController;
    public static  final String TAG = PrintFileActivity.class.getSimpleName();
    private List<CPalindrome> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_file);
        FileUtil.instance(getApplicationContext(),this);
        realmController = RealmController.with(getApplication());
        list = new ArrayList<CPalindrome>();
        for (Palindrome index: realmController.getBooks()){
            list.add(new CPalindrome(index.getId(),index.getValue(),index.getResult()));
        }
        Gson g = new Gson();
        FileUtil.mCreateAndSaveFile(Constant.TAG_PRINT_FILE,printToString());
        FileUtil.mReadJsonDataSymbologies(Constant.TAG_PRINT_FILE);

    }

    @Override
    public void onResponse(String result) {
        Log.d(TAG,result);
    }

    @Override
    public void onSuccess(boolean flag) {

    }

    public String printToString(){
        StringBuilder sb = new StringBuilder();
        Gson j = new Gson();
        for (CPalindrome str : list)
        {
                sb.append("\n");
                sb.append(j.toJson(str));
        }

      return sb.toString();
    }
}
