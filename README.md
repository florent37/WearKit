WearKit
=======

[![Build Status](https://travis-ci.org/florent37/WearKit.svg?branch=master)](https://travis-ci.org/florent37/WearKit)

![alt sample](https://raw.githubusercontent.com/florent37/WearKit/master/wear/src/main/res/drawable/wearkit2_small.jpg)

Wearkit is an Android Wear implementation of WatchKit.

Download
--------

In your root build.gradle add
```groovy
repositories {
    maven {
        url  "http://dl.bintray.com/florent37/maven"
    }
}
```

In your wear module [ ![Download](https://api.bintray.com/packages/florent37/maven/WearKit/images/download.svg) ](https://bintray.com/florent37/maven/WearKit/_latestVersion)
```groovy
compile 'com.github.florent37:wearkit:1.0.0@aar'
```

Usage
--------

StatusBar
--------

You can add a status bar, displaying application name and current time.

![alt sample_status](https://raw.githubusercontent.com/florent37/WearKit/master/wear/src/main/res/drawable/sample_status.png)

In your activity layout, add StatusBarView
```xml
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/black">

    <com.github.florent37.wearkit.view.StatusBarView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>

</FrameLayout>
```

The Status Bar Title respond to Activity's title, but you can change it with .setTitle

```java
StatusBarView statusBarView = (StatusBarView) findViewById(R.id.statusBar);
statusBarView.setTitle("MyTitle");
```

You can set the title color
```xml
<com.github.florent37.wearkit.view.StatusBarView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:titleColor="@color/blue"/>
```

```java
statusBarView.setTitleColor(Color.BLUE  );
```

You can also add a Back button to the status bar

```xml
<com.github.florent37.wearkit.view.StatusBarView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:backEnabled="true"
        />
```

```java
statusBarView.setBackEnabled(true);
```

![alt sample](https://raw.githubusercontent.com/florent37/WearKit/master/wear/src/main/res/drawable/sample_back.png)

Clicking on the status bar will finish the current Activity

Modal Sheets - Page
--------

In Wearkit, navigation is based on Pagination. The user swipe from right to left to switch from one to another page.

Each page is divided in two sections.
The main content, displayed at least on full height of the wear screen ; and the secondary content, displayed below.

![alt sample](https://raw.githubusercontent.com/florent37/WearKit/master/wear/src/main/res/drawable/sample_content1.png)
![alt sample](https://raw.githubusercontent.com/florent37/WearKit/master/wear/src/main/res/drawable/sample_content2.png)

To enable Pagination, add a Pager to your activity layout

```xml
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/black">

    <com.github.florent37.wearkit.view.Pager
        android:id="@+id/viewPager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

    <com.github.florent37.wearkit.view.StatusBarView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>

</FrameLayout>
```

Then create your pages extending wearkit.Page

```java
public class CustomPage extends Page {
    @Override
    public View onCreatePageContent(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.content2, container, false);
    }

    @Override
    public View onCreatePageSecondaryContent(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.content2_secondary, container, false);
    }
}
```

![alt sample](https://raw.githubusercontent.com/florent37/WearKit/master/wear/src/main/res/drawable/sample_content1.png)
![alt sample](https://raw.githubusercontent.com/florent37/WearKit/master/wear/src/main/res/drawable/sample_content1_second.png)

You can also generate a page with secondary options buttons extending wearkit.PageWithActions

```java
public class PageWithImage extends PageWithActions {
    @Override
    public View onCreatePageContent(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.content, container, false);
    }

    @Override
    public Actions onCreatePageActions() {
        return new Actions(
                new String[]{"ok", "nope"},
                true
        );
    }

    @Override
    protected void clickedOnAction(int position) {

    }
}
```

Actions are constructed with the buttons label. A boolean will enable/disable the dismiss button.
(Clicking on the dismiss btn will trigger a scroll to top.)

![alt sample](https://raw.githubusercontent.com/florent37/WearKit/master/wear/src/main/res/drawable/sample_content2.png)
![alt sample](https://raw.githubusercontent.com/florent37/WearKit/master/wear/src/main/res/drawable/sample_content2_second.png)

And add an adapter (based on ViewPager FragmentStatePagerAdapter)

```java
viewPager = (Pager) findViewById(R.id.viewPager);
viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(final int position) {
                if (position % 2 == 0) {
                    return new PageWithImage();
                } else {
                    return new CustomPage();
                }
            }

            @Override
            public int getCount() {
                return 5;
            }
        });
```

Contextual menu
--------

You can display a ContextualMenu, accessible by a long press on any page content.

![alt sample_menu](https://raw.githubusercontent.com/florent37/WearKit/master/wear/src/main/res/drawable/sample_menu.png)

In your activity layout, add ContextualMenu
```xml
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/black">

    <com.github.florent37.wearkit.view.Pager
        android:id="@+id/viewPager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

    <com.github.florent37.wearkit.view.StatusBarView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>

    <com.github.florent37.wearkit.view.ContextualMenu
        android:id="@+id/menu"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

</FrameLayout>
```

Fill in your code with it (4 entries max)

```java`
ContextualMenu contextualMenu = ((ContextualMenu) findViewById(R.id.menu));
contextualMenu.setMenuEntries(new String[]{
                "Accept",
                "Decline"
        }, new Drawable[]{
                getResources().getDrawable(R.drawable.wearkit_menu_accept),
                getResources().getDrawable(R.drawable.wearkit_menu_decline)
        });
```

And listen to the user interraction

```java`
contextualMenu.setOnMenuClickListener(new ContextualMenu.OnMenuClickListener() {
            @Override
            public void onMenuClick(int position) {

            }
        });
```

Styling
--------

Wearkit come with his styled widgets

**Group**

```xml
<LinearLayout
        android:layout_height="wrap_content"
        android:layout_width="140dp"
        android:orientation="vertical"
        style="@style/wearkit.Group">
```
![alt sample](https://raw.githubusercontent.com/florent37/WearKit/master/wear/src/main/res/drawable/sample_group.png)

**Button**

```xml
<Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        style="@style/wearkit.Button"
        android:text="Button" />
```
![alt sample](https://raw.githubusercontent.com/florent37/WearKit/master/wear/src/main/res/drawable/sample_button.png)


Dependencies
--------

- Using menu logos from [http://www.sketchappsources.com/free-source/960-apple-watch-menu-ui-kit-sketch-freebie-resource.html][logos_menu]
- ViewPagerIndicator (from Jake Wharton) [https://github.com/JakeWharton/Android-ViewPagerIndicator][viewpager_indicator]

TODO
--------

- Implement custom scrollbar, with 1/3 screen height

Community
--------

Looking for contributors, feel free to fork !

Wear
--------

If you want to learn wear development : [http://tutos-android-france.com/developper-une-application-pour-les-montres-android-wear/][tuto_wear].

Credits
-------

Author: Florent Champigny

<a href="https://plus.google.com/+florentchampigny">
  <img alt="Follow me on Google+"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/gplus.png" />
</a>
<a href="https://twitter.com/florent_champ">
  <img alt="Follow me on Twitter"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/twitter.png" />
</a>
<a href="https://www.linkedin.com/profile/view?id=297860624">
  <img alt="Follow me on LinkedIn"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/linkedin.png" />
</a>


Pictures by Logan Bourgouin

<a href="https://plus.google.com/+LoganBOURGOIN">
  <img alt="Follow me on Google+"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/gplus.png" />
</a>


License
--------

    Copyright 2015 florent37, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


[snap]: https://oss.sonatype.org/content/repositories/snapshots/
[tuto_wear]: http://tutos-android-france.com/developper-une-application-pour-les-montres-android-wear/
[logos_menu]: http://www.sketchappsources.com/free-source/960-apple-watch-menu-ui-kit-sketch-freebie-resource.html
[viewpager_indicator]: https://github.com/JakeWharton/Android-ViewPagerIndicator