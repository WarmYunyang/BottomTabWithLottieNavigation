# BottomTabWithLottieNavigation



#### Lottie动画实现底部导航栏_效果演示

![演示Normal.gif](http://ww1.sinaimg.cn/large/e336ac2fly1glhqt51d0ig20bg0ooqa8.gif)

![演示Fg.gif](http://ww1.sinaimg.cn/large/e336ac2fly1glhqtydovrg20bg0oojyw.gif)

![演示Vp+Fg.gif](http://ww1.sinaimg.cn/large/e336ac2fly1glhqu6oi0yg20bg0oogsw.gif)

**使用案例**

[**使用Lottie动画实现底部导航栏**](https://blog.csdn.net/ShenQiXiaYang/article/details/110930379)



**开始使用**

[![](https://jitpack.io/v/WarmYunyang/BottomTabWithLottieNavigation.svg)](https://jitpack.io/#WarmYunyang/BottomTabWithLottieNavigation)

在根目录下的 build.gradle 中添加

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

## Gradle

```
// 引入库_必须引入lottie-android
implementation 'com.airbnb.android:lottie:3.5.0'
implementation 'com.github.WarmYunyang:BottomTabWithLottieNavigation:1.0.2'
```



## Attributes

|          name           |  format   |    description     |
| :---------------------: | :-------: | :----------------: |
|     btwln_textSize      | dimension |    设置字体大小    |
|  btwln_textSelectColor  |   color   |  设置字体选中颜色  |
| btwln_textUnselectColor |   color   | 设置字体未选中颜色 |
|     btwln_textBold      |   enum    |    设置字体加粗    |
|    btwln_textAllCaps    |  boolean  |   设置字体全大写   |
|    btwln_textVisible    |  boolean  |  设置Text是否可见  |
|     btwln_iconWidth     | dimension |    设置icon宽度    |
|    btwln_iconHeight     | dimension |    设置icon高度    |
|    btwln_iconMargin     | dimension | 设置icon与文字间距 |
|     btwln_animSpeed     |   float   |    设置动画速度    |



> Change Log
>
> 1.0.2(2021/03/03)
>
> v1.0.1(2020/12/09)
>
> Init commit



## Thanks

* [lottie-android](https://github.com/airbnb/lottie-android)

- [FlycoTabLayout](https://github.com/H07000223/FlycoTabLayout)