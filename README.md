# Lesson_08 2020-21
Android Database topics.
- reading and writing with ORM library
- working with ORM and updating schema
- manipulating DB from Terminal

## GreenDAO library 


### What is ORM?

Object-Relational Mapping (ORM) is a technique that lets you query and manipulate data from a database using an object-oriented paradigm. When talking about ORM, most people are referring to a library that implements the Object-Relational Mapping technique, hence the phrase "an ORM".

An ORM library is a completely ordinary library written in your language of choice that encapsulates the code needed to manipulate the data, so you don't use SQL anymore; you interact directly with an object in the same language you're using.

For the full story and a good example read [this SOF answer](https://stackoverflow.com/a/1279678/2956283)


# Program for offline SQLite DB manipulation

## [SQLite Browser - Needs installation](https://sqlitebrowser.org)

## [Razor SQL - can be be used offline ](https://razorsql.com/download_win.html)


## Terminal DB manipulation

When working with DB on android we need a quick and efficient way to read the DB, validate insertions and generally, overview the tables and db structure. For this purpose it is prefered to use a command line interface way. 

We'll use the Android Debugging Bridge tool. First of all we need to add the folder that adb exists in the PATH variable to be able to call it from the CMD. [Read this for more info about this process](https://stackoverflow.com/questions/20564514/adb-is-not-recognized-as-an-internal-or-external-command-operable-program-or)

SQLite DB can be administered from the Android Studio Terminal. Some useful commands follow:

```adb devices``` to see a list of connected devices

```adb -s <device-id> shell``` to connect to the shell of this device

```su``` to become a superuser on the device's shell

now you should navigate to the path inside your app that contains the SQLite database file, this path is
```cd  /data/data/your.app.unique.id/databases```

in the databases folder type ```ls -la ``` to get a list of the database files

now type ```sqlite3 database-file-name.db``` to connect to the specified database. the prompt now is ```sqlite>```

type: ```.tables``` to get a list of the tables. 

You can start typing normal SQL commands to create your own tables or query the existing ones


