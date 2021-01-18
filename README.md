# Android Case Study

This is project has been completely developed using Kotlin and the MVVM Architectural Pattern and using the latest
Android JetPack Components

The App has 3 screens:
1. Product List Screen
2. Product Details Screen
3. Credit Card Validation Dialog

App Features and Overview
-> The list is built using the retrofit2 library to populate the list
-> It uses the RX Java and Rx Android to fetch the data and consume the service with Retrofit
-> It also uses the LiveData Jetpack Component to update the data in the list.
-> It uses the concept of View Model to save and persist Data
-> I have also used the DataBinding in the views.
-> The list fragment has a swipeRefreshLayout implemented, when  swiped the API is triggered.
-> I have also used the Navigation Library of the Android JetPack Component for the navigation between the fragments
and passing data between them
-> The app also uses the Glide image loading library to render the images.
->The app also uses the Coroutines to handle the asynchronous tasks

-> The credit card validation dialog validates the credit card number and if the number is of valid length
enables the submit button

