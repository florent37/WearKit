WearKit
=======

[![Build Status](https://travis-ci.org/florent37/WearKit.svg?branch=master)](https://travis-ci.org/florent37/WearKit)

![alt sample](https://raw.githubusercontent.com/florent37/WearKit/master/wear/src/main/res/drawable/sample_content_1.png)

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

![alt sample_menu](https://raw.githubusercontent.com/florent37/WearKit/master/wear/src/main/res/drawable/sample_status  .png)

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

Modal Sheets - Page
--------

Contextual menu
--------

You can use display a ContextualMenu, accessible by long-click on any page content.

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
        android:layout_height="match_parent"
        />

    <com.github.florent37.wearkit.view.StatusBarView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>

    <com.github.florent37.wearkit.view.ContextualMenu
        android:id="@+id/menu"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

</FrameLayout>
```

Fill it in your code (4 entries max)

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


Dependencies
--------

Using menu logos from [http://www.sketchappsources.com/free-source/960-apple-watch-menu-ui-kit-sketch-freebie-resource.html][logos_menu]

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