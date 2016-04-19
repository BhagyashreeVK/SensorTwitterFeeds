# SensorTwitterFeeds

Project Description: 
Restful web service that employs Twitter's REST API to post data to 
and get data from Twitter timeline

Prerequisites For Project Setup:
1. Java 1.8
2. Eclipse IDE or any IDE that supports Java Web Projects

Project Setup Instructions:

A] Setup Tomcat Server:
1. Download Tomcat v8.0 binary distruibution from the below link:
   http://tomcat.apache.org/download-80.cgi
2. Extract the downloaded zip folder.
3. In Eclipse IDE, 
   a. Go to Servers tab.
   b. Right click and select New->Server 
      (OR Click on "No servers are available. Click this link to create a new server.")
   c. Click Tomcat v8.0 Server and Next
   d. Select Apache installation Directory and click Finish.
   e. "Tomcat v8.0 Server at localhost [Stopped, Republish]" under Servers tab should be visible. 
      Double click on it verify HTTP ports information. By default HTTP port is 8080.
   f. Right click on Server and click Start. It should be up and running on port 8080.
      Visit default page using URL: http://localhost:8080/
   
B] Import project from GitHub:
1. Import this project into Eclipse IDE
   a. In Eclipse, Click File -> Import
   b. Under Git folder , select "Projects from Git". Click Next
   c. Select "Clone URI". Click Next.
   d. Paste the below URI of this project in the URI field-
      https://github.com/BhagyashreeVK/SensorTwitterFeeds.git  
      Click next
   e. Select "master" branch. Click Next.
   f. Set the destination directory and click next.
   g. Select "import as general project" and click next.
   h. Set the "project name" and click Finish.
2. Right Click on Tomcat Server
3. Select "Add and Remove"
4. Select this project and click Add.
5. Right Click on Server and select Restart.

Web Service Operations:
There are two ways of making web service operation calls using this project.

1. Run "WebServiceClientMain.java" as Java Application.

2. Open a web browser and access the url to the operations 
   and pass the required parameters through the url
   The base url is "http://localhost:8080/SensorTwitterFeeds/twitterfeeds/"
   Add in below parameters to invoke the operations -
   
   a. getTweets: This operation has one parameter "count"
      "count" is the number of tweets to be returned from the timeline. 
       At the most 200 results can be obtained at a time. 
       An sample operation call would look like 
      "http://localhost:8080/SensorTwitterFeeds/twitterfeeds/getTweets?count=4"
   
   b. searchTweets: This operation has two parameters - the "query" and "count"
      "query" is the search term and "count" is the number of results to be returned.
       At the most 100 results can be obtained at a time.  
       An sample operation call would look like 
      "http://localhost:8080/SensorTwitterFeeds/twitterfeeds/searchTweets?query=Maryland&count=5"  
   
   c. postTweet: This operation has two parameters - the "text" and "media"
      The "text" parameter is the text of the status to be posted.
      The "media" parameter is optional. As of now the web service only supports posting images through this parameter, using the URL of the image. 
      The supported formats are - PNG, JPEG, BMP, WEBP, GIF, Animated GIF
      An sample operation call would look like 
      "http://localhost:8080/SensorTwitterFeeds/twitterfeeds/postTweet?text=TestTweet&media=http://www.tesimages.test-post.jpg"
       
