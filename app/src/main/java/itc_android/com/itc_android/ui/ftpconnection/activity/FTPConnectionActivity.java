package itc_android.com.itc_android.ui.ftpconnection.activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import itc_android.com.itc_android.R;
import itc_android.com.itc_android.common.digalog.Dialog;

public class FTPConnectionActivity extends AppCompatActivity implements FTPConnectionView{

    private FTPConnectionPresenter ftpConnectionPresenter ;
    private Unbinder unbinder ;
    @BindView(R.id.edtUsername)
    EditText edtUsername ;
    @BindView(R.id.edtPassword)
    EditText edtPassword ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ftpconnection);
        unbinder = ButterKnife.bind(this);
        ftpConnectionPresenter = new FTPConnectionPresenter();
        ftpConnectionPresenter.bindView(this);
        ftpConnectionPresenter.setTagName(this.getClass().getSimpleName());
        edtPassword.setTypeface(Typeface.DEFAULT);
    }

    @OnClick(R.id.btnConnect)
    public void onClickConnect(){
        ftpConnectionPresenter.connect();
    }

    @Override
    public void onConnection() {
        ftpConnectionPresenter.connect();
    }

    @Override
    public void onGettingInfo() {

    }

    @Override
    public void showLoading() {
        Dialog.with(this).showDialog("Connecting to server. Please wait...");
    }

    @Override
    public void hideLoading() {
        Dialog.with(this).hideDialog();
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public String getUsername() {
        return edtUsername.getText().toString();
    }

    @Override
    public String getPassword() {
        return edtPassword.getText().toString();
    }

    @Override
    public void onShowErrorPassword(int resId) {
        edtPassword.setError(getString(resId));
    }

    @Override
    public void onShowErrorUsername(int resId) {
        edtUsername.setError(getString(resId));
    }
}
