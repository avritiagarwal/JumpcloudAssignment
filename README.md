---Executing  the server ------

We are storing the password hashing application in a public S3 bucket. You can get it in the
following manner:
Mac/Linux example:
$ wget --no-check-certificate --no-proxy
‘https://qa-broken-hashserve-jc.s3.amazonaws.com/broken-hashserve.tar

Setting up Port 
Note: the binary will be installed to /usr/local/bin
$ export PORT=8088

Make Sure Application is runnign and set to Port=8088



---------Guidelines for Applications------------
When launched the application should wait for http connections
It should support three endpoints:
A POST to /hash should accept a password; it should return a job identifier immediately; it should then wait 5 seconds and compute the password hash. The hashing algorithm should be SHA512.
A GET to /hash should accept a job identifier; it should return the base64 encoded password hash for the corresponding POST request.
A GET to /stats should accept no data; it should return a JSON data structure for the total hash requests since server start and the average time of a hash request in milliseconds
The software should be able to process multiple connections simultaneously.
The software should support a graceful shutdown request, it should allow any remaining password hashing to complete, reject any new requests, and shutdown.


---Steps to Execute Automation script in intellij----

1. Export Github repository to Intellij.
2. Right click on Pom file
3. select maven -> reload project
4. After all dependencies are downloaded goto testng.xml
5. Execute testng.xml 
6. Check Report index.html in JumpcloudAssignment/test-output/old/Practice Suite/
7. Please run report in browser to check execution status.
8. You might see failed cases in report but those are for test cases that are failing because of bugs .

Link to Execute Maven through command Prompt
https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html


