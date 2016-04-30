# SensorTwitterFeeds

Project Description: 
Restful web service that employs Twitter's REST API to post data to 
and get data from Twitter timeline

Prerequisites For Project Setup:
1. Java 1.8
2. Eclipse IDE or any IDE that supports Java Web Projects

Project Setup Instructions:

A] Setup Tomcat Server it is not already configured in the workspace:
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
	      If the server is giving error 404, then please follow steps in the "Error Resolution" section below
   
B] Import project from GitHub into Eclipse IDE:
   a. In Eclipse, Click File -> Import
   b. Under Git folder , select "Projects from Git". Click Next
   c. Select "Clone URI". Click Next.
   d. Paste the below URI of this project in the URI field-
      https://github.com/BhagyashreeVK/SensorTwitterFeeds.git  
      Click next
   e. Select "master" branch. Click Next.
   f. Set the destination directory and click next.
   g. Select "Import existing Eclipse projects" and click next.
   h. Set the "project name" and click Finish.
 
C] Add project to Tomcat Server:   
	1. Right Click on Tomcat Server
	2. Select "Add and Remove"
	3. Select this project and click Add.
	4. Right Click on Server and select Restart.

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
   
   c. postTweet: This operation has two parameters - "text" and second parameter could either be a "mediaFile" or a "mediaLink"
      The "text" parameter is the text of the status to be posted.
      The second parameter is either "mediaLink" or "mediaFile", which is optional. 
      Only one of these could be used at a time,since only one image can be uploaded at a time using the web service.
      "mediaLink" is the URL of the image to be posted along with the text.
      "mediaFile" is the name of the image file saved in the resources folder of the project i.e. src/main/resources folder.
      The supported formats are - PNG, JPEG, BMP, WEBP, GIF, Animated GIF
      
      An sample operation call would look like 
      "http://localhost:8080/SensorTwitterFeeds/twitterfeeds/postTweet?text=TestTweet&mediaLink=http://www.construplan.co.uk/wp-content/uploads/2015/07/Test-Post.jpg" 
                                                 OR
      "http://localhost:8080/SensorTwitterFeeds/twitterfeeds/postTweet?text=TestTweet&mediaFile=index.png"
      where index.png is an image file saved in src/main/resources folder.
      
Unit Test Cases:

Following unit test cases are defined in the WebServiceTest.java class to test the 
operations of the web service using "TweetUsingJava" object.

1. Test 001a : Tweet only text
   Test 001b : Tweet text and image from system
2. Test 002 :  Search five tweets with search term "Maryland" in them.
3. Test 003 :  Get recent three tweets posted from timeline.
       
Error Resolution:

I]Error 404:
   1. Stop the server if it is running. Remove this project, if it has been added to the server.
      To remove projects: 
      a. Right click on Tomcat Server -> Select Add and Remove -> Click Remove All -> Click Finish.
      b. Right click on Tomcat Server -> Select Publish. 
   2.Right click on the Tomcat Server -> Select Properties -> Click Switch Location -> Click Apply. 
     The location should switch from "workspace metadata" to some other path. 
   2.Double click on the Tomcat Server. An "Overview" tab will open up.
   3.In the "Server Locations" choose "use Tomcat installation".
   3.Click "Set deploy path to the default value". Deploy Path field should be "wtpwebapps". Save changes.
   4.Start the server and check again for "http://localhost:8080/". The server should be error free now.
   