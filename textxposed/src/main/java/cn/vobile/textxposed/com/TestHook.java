package cn.vobile.textxposed.com;

import android.util.Log;

import java.util.Date;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * <pre>
 *     author : Wang Pan
 *     e-mail : 501098462@qq.con
 *     time   : 2018/11/15
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class TestHook implements IXposedHookLoadPackage {
    private static final String TAG = "TestHook";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {

        if (loadPackageParam.packageName.equals("cn.vobile.com.xposeddemo")){
            Log.e(TAG, "handleLoadPackage:");
            XposedBridge.log("handleLoadPackage");

            XposedHelpers.findAndHookMethod("cn.vobile.com.xposeddemo.MainActivity",
                    loadPackageParam.classLoader,
                    "getString",
//                    String.class,
                    new MyXC_MethodHook());

            XposedHelpers.findAndHookMethod("java.text.DateFormat",
                    loadPackageParam.classLoader,
                    "format",
                    Date.class,
                    new MyXC_MethodHook1());
        }
    }

    static class MyXC_MethodHook extends XC_MethodHook {
        public MyXC_MethodHook() {
        }

        public MyXC_MethodHook(int priority) {
            super(priority);
        }

        @Override
        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
            super.beforeHookedMethod(param);
            XposedBridge.log("Enter->beforeHookedMethod:Activity.onCreate");
        }

        @Override
        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
            super.afterHookedMethod(param);
            XposedBridge.log("Enter->afterHookedMethod:Activity.onCreate");
            param.setResult("Hello TestHook !!!");
        }
    }

    static class MyXC_MethodHook1 extends XC_MethodHook {
        public MyXC_MethodHook1() {
        }

        public MyXC_MethodHook1(int priority) {
            super(priority);
        }

        @Override
        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
            super.beforeHookedMethod(param);
            XposedBridge.log("Enter->beforeHookedMethod");
            Date date = new Date();
            date.setTime(50758);
            param.args = new Object[]{date};
        }

        @Override
        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
            super.afterHookedMethod(param);
            XposedBridge.log("Enter->afterHookedMethod");
        }
    }
}
