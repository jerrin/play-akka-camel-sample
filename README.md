Play Akka Camel Sample
======================

A sample application done in Play framework. This app constantly polls for log files from remote FTP server using akka-camel module, download and process it and index them using elastic search. It provides a simple RESTful web interface to allow the users to search for keywords in the log files. The app looks for file "file.csv" at ftp://admin@localhost:21/?username=admin&password=admin

##Tools used 

Play framework - To develop web application<br/>
Google guice - For dependency injection<br/>
Akka-Camel - To poll log files from remote FTP server<br/>
ElasticSearch - To index the log file contents<br/>
