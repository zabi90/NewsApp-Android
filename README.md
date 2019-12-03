# NewsApp-Android

NewsApp-Android is a sample project for learning purpose only that fetch news article from [News API](https://newsapi.org/) and save locally in mobile database storage to show it later. This app design is inspired from [Fortnightly](https://material.io/design/material-studies/fortnightly.html) Google Material design case study.


| Light Mode      | Dark Mode      |
|------------|-------------|
| <img alt="screenshot" src="/ScreenShots/light_one.png" width="45%" /><img alt="screenshot" src="/ScreenShots/light_two.png" width="45%" /><img alt="screenshot" src="/ScreenShots/light_three.png" width="45%" />| <img alt="screenshot" src="/ScreenShots/dark_one.png" width="45%" /><img alt="screenshot" src="/ScreenShots/dark_two.png" width="45%" /><img alt="screenshot" src="/ScreenShots/dark_three.png" width="45%" />   |

# Features

  - Fetch news article api from [News API](https://newsapi.org/) using [Retrofit](https://square.github.io/retrofit/) with [coroutine](https://kotlinlang.org/docs/reference/coroutines-overview.html)
  - Save fetched data sqlite database using [Room](https://developer.android.com/topic/libraries/architecture/room?gclid=CjwKCAjwo9rtBRAdEiwA_WXcFl0dpqQ4MlZAP9cW7a1tVxWgc2sy3eq5sn26_KpG8dAoRf9G3y1nmBoCmIQQAvD_BwE) persistance
  - Show News articles from database first and load from api when not available into database.
  - Sync News articles after 30 minutes using [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager/basics.html))
  - This app is also support dark theme when system setting in Android Q is in dark mode.


## Pending Features

> Search news article.
> Make news article favourite.
> Open news article detail in webview. 
> Share News Article.
> Add Support for Tabs.


### Libraries

NewsApp-Android uses a number of open source projects to work properly:

* [RetroFit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java
* [Room](https://developer.android.com/topic/libraries/architecture/room?gclid=CjwKCAjwo9rtBRAdEiwA_WXcFl0dpqQ4MlZAP9cW7a1tVxWgc2sy3eq5sn26_KpG8dAoRf9G3y1nmBoCmIQQAvD_BwE) - The Room persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
* [Dagger](https://developer.android.com/training/dependency-injection/dagger-android) - Dagger is used for dependency injection.
* [Navigation Component](https://developer.android.com/guide/navigation) - Android navigation component to handle Android navigation
* [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager/basics.html) -With WorkManager, you can easily set up a task and hand it off to the system to run under the conditions you specify.
* [Pagination](https://developer.android.com/topic/libraries/architecture/paging) - The Paging Library helps you load and display small chunks of data at a time
* [Material Theme](https://material.io/design/) - Google Material Theme.
* [Glide](https://bumptech.github.io/glide/) - Glide is a fast and efficient image loading library for Android focused on smooth scrolling.
* [Coroutine](https://kotlinlang.org/docs/reference/coroutines/coroutines-guide.html) Handle Threads and Asyn Jobs with concise syntax.

### Todos

 - Write Local Tests and Instrument Tests.
 - Crashlytics.
 - Short Appbar on scrolling.

License
----

       Mit License
