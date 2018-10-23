package com.yihui.proxymode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        initProxy();
//        initProxyTwo();
        initProxyThree();
    }

    /**
     * 静态代理
     */
    private void initProxy() {
        //目标对象
        ISinger target = new Singer();
        //代理对象
        ISinger proxy = new SingerProxy(target);
        //执行的是代理的方法
        proxy.sing();
    }

    /**
     * jdk动态代理
     */
    private void initProxyTwo() {
        final Singer target = new Singer();
        ISinger proxy = (ISinger) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("动态代理向观众问好");
                        //执行目标对象方法
                        Object returnValue = method.invoke(target, args);
                        System.out.println("动态代理谢谢大家");
                        return returnValue;
                    }
                });
        proxy.sing();
    }

    /**
     * cgclib动态代理
     */
    private void initProxyThree() {
        //目标对象
        Singer2 target = new Singer2();
        //代理对象
        Singer2 proxy1 = (Singer2) new ProxyFactory(target,this).getProxyInstance();
        //执行代理对象的方法
        proxy1.sing();
    }

}
