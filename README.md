# Sneaker v2
A lightweight Android library for customizable alerts

![](https://github.com/Hamadakram/Sneaker/blob/master/art/Sneaker.png?raw=true)
## Download
Grab via Gradle:
```kotlin
implementation 'com.irozon.sneaker:sneaker:2.0.0'
```
## Usage

In Sneaker 2.0.0 it's possilbe to show sneaker on Activity, Fragment or any ViewGroup
```kotlin
 Sneaker.with(activity) // To show Sneaker on Activity
 Sneaker.with(fragment) // To show Sneaker on Fragment
 Sneaker.with(viewGroup) // To show Sneaker on ViewGroup
```

#### Custom:
```kotlin
Sneaker.with(actvitiy) // Activity, Fragment or ViewGroup
       .setTitle("Title", R.color.white) // Title and title color
       .setMessage("This is the message.", R.color.white) // Message and message color
       .setDuration(4000) // Time duration to show
       .autoHide(true) // Auto hide Sneaker view
       .setHeight(ViewGroup.LayoutParams.WRAP_CONTENT) // Height of the Sneaker layout
       .setIcon(R.drawable.ic_no_connection, R.color.white, false) // Icon, icon tint color and circular icon view
       .setTypeface(Typeface.createFromAsset(this.getAssets(), "font/" + fontName)); // Custom font for title and message
       .setOnSneakerClickListener(this) // Click listener for Sneaker
       .setOnSneakerDismissListener(this) // Dismiss listener for Sneaker. - Version 1.0.2
       .setCornerRadius(radius, margin) // Radius and margin for round corner Sneaker. - Version 1.0.2
       .sneak(R.color.colorAccent) // Sneak with background color
```
#### Error:
```kotlin
 Sneaker.with(actvitiy) // Activity, Fragment or ViewGroup
        .setTitle("Error!!")
        .setMessage("This is the error message")
        .sneakError()
```
#### Success:
```kotlin
 Sneaker.with(actvitiy) // Activity, Fragment or ViewGroup
        .setTitle("Success!!")
        .setMessage("This is the success message")
        .sneakSuccess()
```
#### Warning:
```kotlin
 Sneaker.with(actvitiy) // Activity, Fragment or ViewGroup
        .setTitle("Warning!!")
        .setMessage("This is the warning message")
        .sneakWarning()
```
#### Custom View:
```kotlin
 val sneaker = Sneaker.with(actvitiy) // Activity, Fragment or ViewGroup
 val view = LayoutInflater.from(this).inflate(R.layout.custom_view,  sneaker.getView(), false)
 // Your custom view code
 view.findViewById<TextView>(R.id.tvInstall).setOnClickListener{
       Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
 }
 sneaker.sneakCustom(view)
```
## Apps using Sneaker
If you are using Sneaker in your app and would like to be listed here, please let me know by [email](mailto:hamadakram91@gmail.com) or opening a new issue!

## Authors

* **Hammad Akram** - (https://github.com/hamadakram)

## Licence
```
Copyright 2018 Irozon, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
