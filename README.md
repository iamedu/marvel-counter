# marvel-counter

Simple clojure tool to get marvel character stats

## Requirements

REQUIRED You need to get your api key from marvel, check [http://developer.marvel.com](http://developer.marvel.com)
REQUIRED You need to have Java Software Development Kit 1.8 (should work with 1.7, but it hasn't been tested). You can get it from [Oracle](http://www.oracle.com/technetwork/java/javase/downloads/index-jsp-138363.html)
OPTIONAL Install the leiningen build tool, if you use OS X or Linux you can probably skip this step, since some support scripts are included. In windows it is highly recommended that you install [http://leiningen.org/](leiningen) the standard way.

Check that you have the JAVA_HOME environment variable, and that the javac command is in your PATH (details on how to do this depend on the shell you use, and the OS you use, so consult with your tech support team or give us specific details about your environment!)

## 3 step guide in OS X or Linux

1. PUBLIC_KEY=blahblah PRIVATE_KEY=blahblah ./leinw run fetch
2. ./leinw run list-all
3. ./leinw run list-popular

Don't be worried if step 1 takes a long time the first time you run it, it's downloading the leiningen build tool and fetching all marvel characters

## Installation / Usage

DISCLAIMER: This is supported in Linux and Mac OS X, running it in Windows should be simple, but I don't have easy access to a windows machine.

Normally, you would be asked to install leiningen 

There are two ways to run this

* Directly from Leiningen
* Builder the standalone jar file first

### Running it directly from leiningen

If you choose to use leiningen, then things are pretty simple, you just need to run this:

```
./leinw run ACTION
```

We explain what actions are possible later


### Building the jar file

Run:

```
./leinw uberjar
```

When this is done, you have a standalone java jar file and you can run it by doing this:

```
java -jar target/uberjar/marvel-counter-0.1.0-SNAPSHOT-standalone.jar ACTION
```

You can rename, move or do anything you want with the jar file.

### Running your actions

There are three possible actions:

1. fetch
2. list-all
3. list-popular

Everything should be pretty straightforward, the only thing you have to keep in mind, is that you need to pass your marvel developer credentials, and you have to do it via environment variables.

So if you want to run the fetch step with leiningen you have to do:

```
PUBLIC_KEY=blahblah PRIVATE_KEY=blahblah ./leinw run fetch
```

If you want to run it using the jar file run:

```
PUBLIC_KEY=blahblah PRIVATE_KEY=blahblah java -jar target/uberjar/marvel-counter-0.1.0-SNAPSHOT-standalone.jar fetch
```

Thats about everything you have to know! enjoy!
