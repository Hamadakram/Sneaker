# Sneaker
A lightweight Android library for customizable alerts

![](https://github.com/Hamadakram/Sneaker/blob/master/app/Sneaker.png?raw=true)
## Download
Grab via Gradle:
```java
compile 'com.irozon.sneaker:sneaker:1.0.0'
```
Or Maven:
```java
<dependency>
  <groupId>com.irozon.sneaker</groupId>
  <artifactId>sneaker</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
``` 
## Usage
#### Custom:
```java
Sneaker.with(this)
       .setTitle("Title", R.color.white) // Title and title color
       .setMessage("This is the message.", R.color.white) // Message and message color
       .setDuration(4000) // Time duration to show
       .autoHide(true) // Auto hide Sneaker view
       .setHeight(ViewGroup.LayoutParams.WRAP_CONTENT) // Height of the Sneaker layout
       .setIcon(R.drawable.ic_no_connection, R.color.white, false) // Icon, icon tint color and circular icon view
       .setOnSneakerClickListener(this) // Click listener for Sneaker
       .sneak(R.color.colorAccent); // Sneak with background color
```
#### Error:
```java
 Sneaker.with(this)
        .setTitle("Error!!")
        .setMessage("This is the error message")
        .sneakError();
```
#### Success:
```java
 Sneaker.with(this)
        .setTitle("Success!!")
        .setMessage("This is the success message")
        .sneakSuccess();
```
#### Warning:
```java
 Sneaker.with(this)
        .setTitle("Warning!!")
        .setMessage("This is the warning message")
        .sneakWarning();
```
## Authors

* **Hammad Akram** - (https://github.com/hamadakram)

## Licence
```
Copyright 2017 Irozon, Inc.

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
