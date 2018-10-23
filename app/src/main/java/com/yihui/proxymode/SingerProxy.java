package com.yihui.proxymode;

/**
 * 功能详细描述
 *
 * @author: zhouxing
 * @version: [1.0, 2018/10/23]
 * @see: [相关类/方法]
 * @describe: [产品/模块版本]
 */
public class SingerProxy implements ISinger {
    // 接收目标对象，以便调用sing方法
    private ISinger target;

    public SingerProxy(ISinger target) {
        this.target = target;
    }

    // 对目标对象的sing方法进行功能扩展
    @Override
    public void sing() {
        System.out.println("向观众问好");
        target.sing();
        System.out.println("谢谢大家");
    }
}
