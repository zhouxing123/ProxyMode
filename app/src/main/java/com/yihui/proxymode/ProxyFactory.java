package com.yihui.proxymode;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Method;

import leo.android.cglib.proxy.Enhancer;
import leo.android.cglib.proxy.MethodInterceptor;
import leo.android.cglib.proxy.MethodProxy;


/**
 * 功能详细描述
 *
 * @author: zhouxing
 * @version: [1.0, 2018/10/23]
 * @see: [相关类/方法]
 * @describe: [产品/模块版本]
 */
public class ProxyFactory implements MethodInterceptor {
    // 维护目标对象
    private Object target;
    private Context context;
    public ProxyFactory(Object target,Context context) {
        this.target = target;
        this.context =context;
    }

    // 给目标对象创建一个代理对象
    public Object getProxyInstance() {
        //1.工具类
        Enhancer enhancer = new Enhancer(context);
        //2.设置父类
        enhancer.setSuperclass(target.getClass());
        //3.设置回调函数
        enhancer.setInterceptor(this);
        //4.创建子类(代理对象)
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Object[] objects, MethodProxy methodProxy) throws Exception {
        System.out.println("Cgclib向观众问好");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("Cgclib谢谢大家");
        return result;
    }
}
