![DroidBallet](https://raw.github.com/sahan/DroidBallet/master/logo.png)

> **DroidBallet** /'drȯid'ba'lā/ <em>noun.</em> **1** Motion based interaction and UI navigation. 

<br/>
##About

**DroidBallet** is an effort to supplement the user experience on Android by incorporating motion based 
interaction and UI navigation.   
   
This library is currently in a **PRE-ALPHA** stage with on-going tweaks to certain equations and experimentation 
with various widgets.   
<br/>

##Setup

### 1. For Maven Based Android Projects

Add the following dependency in your project's pom.xml.

```xml
<dependency>
   <groupId>com.lonepulse</groupId>
   <artifactId>droidballet</artifactId>
   <version>0.1.0</version>
   <type>apklib</type>
</dependency>
```

For information on building Android projects using Maven here's [Chapter 14](http://www.sonatype.com/books/mvnref-book/reference/android-dev.html) of `Maven: The Complete Reference`.   
<br/>   

### 2. For Standard Android Projects

Clone the repository and import it as an **existing project** in Eclipse.

```bash
$ git clone git://github.com/sahan/DroidBallet.git
```

Since the Eclipse metadata is maintained in the repository, the project should be immediately available as an Android library which you can reference in your own project. Refer the 
[developer guide](http://developer.android.com/tools/projects/projects-eclipse.html#ReferencingLibraryProject) for information on referencing library projects.   
<br/><br/>

##Usage

The current motion widgets arsenal consists of a single component named `LinearMotionListView`. 
Use this on a portrait activity using the steps outlined below.   
<br/>   

###1. Configure `AndroidManifest.xml` to use DroidBallet's application instance.   

<pre>
<font color="#00ABDF">&lt;manifest </font>
...
    <font color="#00ABDF">&lt;application </font>
            <font color="#04BA31">android:name=</font><font color="#FB5D5D">"com.lonepulse.droidballet.app.MotionApplication"</font>
...
</pre>   
<br/>

###2. Declare the `LinearMotionListView` in your layout.

```xml
<com.lonepulse.droidballet.widget.LinearMotionListView
	android:id="@android:id/list"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```  
> Else you could instantiate `LinearMotionListView` within your activity and set it as the *content view*.
 
<br/>
###3. Override `onResume()` and `onPause()` to activate and deactivate the motion widgets in the smallest possible scope.

######Activate 
```java
@Override
protected void onResume() {
	
	super.onStart();
		
	HiggsField.INSTANCE.activate();
	motionListView.register();
}
```

######Deactivate 
```java
@Override
protected void onPause() {
	
	super.onPause();
		
	HiggsField.INSTANCE.deactivate();
	motionListView.unregister();
}
```   
<br/>

##License
This library is licensed under [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html).
