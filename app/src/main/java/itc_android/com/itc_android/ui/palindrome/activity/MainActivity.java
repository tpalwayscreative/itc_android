package itc_android.com.itc_android.ui.palindrome.activity;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.realm.Realm;
import itc_android.com.itc_android.Constant;
import itc_android.com.itc_android.common.controller.RealmController;
import itc_android.com.itc_android.common.realm.Palindrome;
import itc_android.com.itc_android.ui.palindrome.fragment.InputFragment;
import itc_android.com.itc_android.R;
import itc_android.com.itc_android.ui.palindrome.fragment.ResultFragment;
import itc_android.com.itc_android.common.utils.Utils;

public class MainActivity extends AppCompatActivity implements InputFragment.ListenerInput,ResultFragment.ListenerResult {
    private InputFragment inputFragment ;
    private ResultFragment resultFragment ;
    private FragmentManager fragmentManager ;

    private RealmController realmController ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputFragment = new InputFragment();
        fragmentManager = getSupportFragmentManager();
        inputFragment.setListenerInput(this);
        resultFragment = new ResultFragment();
        resultFragment.setListenerResult(this);
        onDefault();
        //get realm instance
        this.realmController = RealmController.with(getApplication());
    }

    public void onDefault(){
        fragmentManager.beginTransaction()
                .replace(R.id.flMain,inputFragment,inputFragment.getTag())
                .commit();
    }

    @Override
    public void onInputAction(String input,String result) {
        Bundle bundle = new Bundle();
        bundle.putString(Constant.TAG_VALUE,input);
        bundle.putString(Constant.TAG_RESULT,result);

        Palindrome book = new Palindrome();
        //book.setId(RealmController.getInstance().getBooks().size() + 1);
        book.setId(RealmController.getInstance().getBooks().size() + System.currentTimeMillis() + "");
        book.setValue(input);
        book.setResult(result);
        if (realmController.getBook(RealmController.getInstance().getBooks().size() + System.currentTimeMillis() + "")==null){
            realmController.mInsetBook(book);
        }
        resultFragment.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(R.id.flMain,resultFragment,resultFragment.getTag())
                .commit();
    }

    @Override
    public void onResultAction() {
        fragmentManager.beginTransaction()
                .replace(R.id.flMain,inputFragment,inputFragment.getTag())
                .commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d("action","Grandted successfully");
            Utils.setAutoOrientationEnabled(getApplicationContext(),true);
        }
    }
}
