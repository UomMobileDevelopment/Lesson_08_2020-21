# Lesson_10_2018-19
Advanced Database topics.
- reading and writing with ORM library
- manipulating DB from Terminal

## Terminal DB manipulation

SQLite DB can be administered from the Android Studio Terminal. Some useful commands follow:

```adb devices``` to see a list of connected devices
```adb -s <device-id> shell``` to connect to the shell of this device
```su``` to become a superuser on the device's shell

now you should navigate to the path inside your app that contains the SQLite database file, this path is
```cd  /data/data/your.app.unique.id/databases```


## What is an ORM library?

 “Object-relational mapping (ORM) is a programming technique in which a metadata descriptor is used to connect object code to a relational database. Object code is written in object-oriented programming (OOP) languages such as Java or C#. ORM converts data between type systems that are unable to coexist within relational databases and OOP languages.”

