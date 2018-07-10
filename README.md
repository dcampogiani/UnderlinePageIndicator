# Underline Page Indicator [![Release]( https://jitpack.io/v/dcampogiani/UnderlinePageIndicator.svg )]( https://jitpack.io/#dcampogiani/UnderlinePageIndicator ) [![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-Underline%20Page%20Indicator-green.svg?style=flat )]( https://android-arsenal.com/details/1/7027 )

![Demo](https://github.com/dcampogiani/UnderlinePageIndicator/blob/master/demo.gif?raw=true)

This indicator will underline each tab text, morphing its size while scrolling


## How to use

Add **JitPack** repository to your `build.gradle` file

``` gradle
allprojects {
	repositories {
	     ...
	     maven { url 'https://jitpack.io' }
	}
}
```

Add the Dependency 

``` gradle
dependencies {
    implementation "com.github.dcampogiani:underlinepageindicator:0.0.1"
}
```

Add TabLayout and UnderlinePageIndicator to XML:

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <android.support.design.widget.TabLayout
        android:id="@+id/tabs"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:tabIndicatorHeight="0dp"
        app:tabMode="fixed" />

    <com.danielecampogiani.underlinepageindicator.UnderlinePageIndicator
        android:id="@+id/indicator"
        android:layout_width="match_parent"
        android:layout_height="2dp" />


    <android.support.v4.view.ViewPager
        android:id="@+id/pager"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</LinearLayout>
```

Style TabLayout with ```app:tabIndicatorHeight="0dp"``` and  ```app:tabMode="fixed"```

Setup with ViewPager

```kotlin
pager.adapter = Adapter(...)
indicator.setTabLayoutAndViewPager(tabs, pager)
```

Checkout the app project for an example.

## Thanks
 - [ViewPagerIndicator](https://github.com/JakeWharton/ViewPagerIndicator)
