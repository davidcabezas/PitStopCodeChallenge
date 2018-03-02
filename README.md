# PitStop Code Challenge #

This repo contains the code of the project I developed for the code challenge that PitStop requested me.

It has been developed following MVP architecture, and includes:

* Retrofit: data request
* Google Maps: show each Sightseeing Location item's direction (there is no Release API Key set, the map is being showed in Debug mode)
* Sugar ORM: manage a SQLite database where the data is stored (the first time the App fetches de JSON the objects are created and stored into the database)
* ButterKnife: inject dependencies
* Glide: easy and reliable image loading
* SlidingUpPanel: adds double layer to Sightseeing Location item View
* Rx Java 2 + RxAndroid: performs some operations in Reactive Programming mode
* Java 8: allows Lambda usage, in order to reduce boilerplate code


##Considerations##

- There is an incompatibility regarding Sugar ORM and Instant Run, so this Android Studio's option must be disabled when running the App directly from the IDE

- LocationItemActivity has no associated Presenter because this App's scope doesn't requires it to be implemented. Anyway, the project structure is ready to fit the corresponding Model and Presenter layers (the View one is the only I added for this Activity)

- I analyzed the App's memory usage, and I saw it is increased when opening and closing  few times. After that, the App stabilizes the memory consumption. I'd like to check it more carefully, but it might take me some time and I don't wanna take advantage over other possible candidates.

