package org.unreal.dagger.function.main.presenter;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.unreal.dagger.data.http.local.service.UserService;
import org.unreal.dagger.data.http.taobao.service.TaobaoIPLocationService;
import org.unreal.dagger.data.http.taobao.vo.TaobaoIPLocationInfo;
import org.unreal.dagger.function.main.contract.MainContract;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * <b>类名称：</b> MainPresenter <br/>
 * <b>类描述：</b> Presenter类 <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年08月12日 下午4:34<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class MainPresenter implements MainContract.presenter {
    private static final String TAG = "MainPresenter";
    private final MainContract.View view;
    private final SharedPreferences sharedPreferences;
    private final TaobaoIPLocationService locationService;
    private final UserService userService;
    private final String tv;

    @Inject
    public MainPresenter(MainContract.View view,
                         @Named("default") SharedPreferences sharedPreferences,
                         TaobaoIPLocationService locationService,
                         UserService userService, @Named("s") String tv) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        this.locationService = locationService;
        this.userService = userService;
        this.tv = tv;
    }

    public void main(){
        Log.d(TAG, "main: " + tv);
        locationService.getIPInfo("myip")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TaobaoIPLocationInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(TaobaoIPLocationInfo taobaoIPLocationInfo) {
                        view.showLocationInfo(taobaoIPLocationInfo);
                    }
                });

    }
}
