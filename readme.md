### Xposed 使用简介

> 此示例是在模拟器上面完成，Android5.0 以下的系统此示例不适用

**使用环境**

- 雷电模拟器 Android 5.1.1 版本
- Android Studio 3.1.2 版本
- Xposed Installer 3.1.5 版本   需要将此apk文件安装到模拟器中
- 安装[Xposed_Installer_apk](https://raw.githubusercontent.com/OhMyGod111/XposedDemo/master/apk_file/XposedInstaller_3.1.5.apk)文件，打开在”安装/更新“中下载所需库文件，按提示操作完成，重启设备即可

**工程配置**

1. 创建一个Android 普通工程 gradle 配置如下：

   ```groovy
   compileOnly 'de.robv.android.xposed:api:82'
   compileOnly 'de.robv.android.xposed:api:82:sources'
   ```

2. 创建一个普通类并实现 IXposedHookLoadPackage 接口

   ```java
   public class TestHook implements IXposedHookLoadPackage {
       @Override
       public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
         XposedBridge.log("Loaded app: " + lpparam.packageName);
       }
   }
   ```

3. AndroidManifest.xml 配置

   ```xml
   <?xml version="1.0" encoding="utf-8"?>
   <manifest xmlns:android="http://schemas.android.com/apk/res/android"
       xmlns:tools="http://schemas.android.com/tools"      
       package="cn.vobile.textxposed.com"
             
       <application
           android:icon="@drawable/ic_launcher"
           android:label="@string/app_name" >
           <meta-data
               android:name="xposedmodule"
               android:value="true" />
           <meta-data
               android:name="xposeddescription"
               android:value="Easy example ..." />
           <meta-data
               android:name="xposedminversion"
               android:value="86" />
       </application>
   </manifest>
   ```

4. assets/xposed_init  配置入口 指定实现类的全路径 如：

   ```
   cn.vobile.textxposed.com.TestHook
   ```

到此环境配置完毕！具体使用使用示例参看此Demo

**注意：**

要确保关闭 Android Studio 中 Instant Run项`File -> Settings -> Build, Execution, Deployment -> Instant Run`,否者Hook操作失效

**参考**

https://github.com/rovo89/XposedBridge/wiki/Development-tutorial