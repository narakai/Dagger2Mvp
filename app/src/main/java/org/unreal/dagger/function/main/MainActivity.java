package org.unreal.dagger.function.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.unreal.dagger.DaggerApplication;
import org.unreal.dagger.R;
import org.unreal.dagger.data.http.taobao.vo.TaobaoIPLocationInfo;
import org.unreal.dagger.function.main.contract.MainContract;
import org.unreal.dagger.function.main.module.MainModule;
import org.unreal.dagger.function.main.presenter.MainPresenter;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainContract.View{
    private static final String TAG = "MainActivity";

    //注入presenter 对象,调其构造函数，若无Inject函数需要module里provide
    @Inject
    MainPresenter mainPresenter;

    @Inject
    OneInjectClass mOneInjectClass;

    private TextView city;
    private TextView cityCode;
    private TextView ip;
    private TextView isp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupActivityComponent();
        bindView();
        mainPresenter.main();
        Log.d(TAG, "onCreate: " + mOneInjectClass.one);
    }

    private void bindView() {
        city = (TextView) findViewById(R.id.city);
        cityCode = (TextView) findViewById(R.id.cityCode);
        ip = (TextView) findViewById(R.id.ip);
        isp = (TextView) findViewById(R.id.isp);
    }

    /**
     * 初始化属于自己Activity的Component对象
     * 本例将MainComponent添加成为AppComponent的子Component
     */
    private void setupActivityComponent() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addSub(new MainModule(this, this.getClass().getCanonicalName(), "OneInjectClass"))
                .inject(this);
    }

    /**
     * MVP Presenter 中的回调
     *
     * @param taobaoIPLocationInfo IP定位后的返回信息
     */
    @Override
    public void showLocationInfo(TaobaoIPLocationInfo taobaoIPLocationInfo) {
        city.setText(String.format("定位城市：%s", taobaoIPLocationInfo.getData().getCity()));
        cityCode.setText(String.format("定位城市代码：%s", taobaoIPLocationInfo.getData().getCity_id()));
        ip.setText(String.format("地位地区IP：%s", taobaoIPLocationInfo.getData().getIp()));
        isp.setText(String.format("isp服务提供商：%s", taobaoIPLocationInfo.getData().getIsp()));
    }

    /**
     * MVP Presenter 中的回调
     */
    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
