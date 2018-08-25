package org.unreal.dagger.function.main.module;

import android.view.View;

import org.unreal.dagger.ActivityScope;
import org.unreal.dagger.function.main.contract.MainContract;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * <b>类名称：</b> MainModule <br/>
 * <b>类描述：</b> Module 类<br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年08月11日 下午5:45<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@Module
public class MainModule {

    private MainContract.View view;
    private String s;
    private String t;

    public MainModule(MainContract.View view, String s, String t){
        this.view = view;
        this.s = s;
        this.t = t;
    }

    @ActivityScope
    @Provides
    public MainContract.View providerView(){
        return view;
    }

//    如果对于不同的对象有同样的返回类型，我们可以使用@Named修饰符注解。你需要在提供单例的地方(@Provides注解)和注入的地方（@Inject注解）都使用@Named注解。
    @ActivityScope
    @Provides @Named("s")
    public String providerS(){
        return s;
    }

    @ActivityScope
    @Provides @Named("t")
    public String providerT(){
        return t;
    }

}
