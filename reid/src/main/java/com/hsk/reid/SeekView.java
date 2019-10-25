package com.hsk.reid;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

/**
 * Created by small-ho on 2019-10-25 15:12
 * title：
 */
class SeekView {

    static void bind(Activity activity) {
        Class<?> cls = activity.getClass(); //获取obj的Class
        Field[] fields = cls.getDeclaredFields(); //获取Class中所有的成员
        for (Field field : fields) { //遍历所有成员
            ViewReid viewResId = field.getAnnotation(ViewReid.class);//获取成员的注解
            //判断成员是否含有注解
            if (viewResId != null) {
                int viewId = viewResId.value(); //获取成员注解的参数，这就是我们传进去控件Id
                if (viewId != -1) {
                    try {
                        field.setAccessible(true);//取消成员的封装
                        field.set(activity, activity.findViewById(viewId));//即 field = contentParentView.findViewById(viewId);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static void bind(Object obj, View contentParentView) {
        Class<?> cls = obj.getClass(); //获取obj的Class
        Field[] fields = cls.getDeclaredFields(); //获取Class中所有的成员
        for (Field field : fields) { //遍历所有成员
            ViewReid viewResId = field.getAnnotation(ViewReid.class);//获取成员的注解
            //判断成员是否含有注解
            if (viewResId != null) {
                int viewId = viewResId.value(); //获取成员注解的参数，这就是我们传进去控件Id
                if (viewId != -1) {
                    try {
                        field.setAccessible(true);//取消成员的封装
                        field.set(obj, contentParentView.findViewById(viewId));//即 field = contentParentView.findViewById(viewId);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
