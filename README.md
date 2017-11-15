#Secure Spring Rest API Using Spring Security Oauth2 Example
------------------------------------------------------------------------------

What you'll need

JDK 1.8 or later

Maven 3 or later

Spring MVC 4.3.0.RELEASE

Spring Security 4.3.0.RELEASE

Build

mvn clean install    

Run The Script for creating admin with password admin@123
---------------------------------------------------------

INSERT INTO users(username,password,active)VALUES ('admin','$2a$10$rqQ.kW7/fWwjs1gG8cKhWOOLTHBnqzQL/7i9jGYhPbmKG9bl9b5N6', true);
INSERT INTO user_roles(role,username) VALUES('ROLE_ADMIN', 'admin');

Steps To Run
--------------

1.Access the URL 
------------------

http://10.30.30.39:8080/RestApiSpringSecurity/fruits without any authorization. The message is 401 Unauthorized

2.Get tokens using HTTP POST URL and set set Header Authorization with client credential test/test
---------------------------------------------------------------------------------------------------------------

http://10.30.30.39:8080/RestApiSpringSecurity/oauth/token?grant_type=password&username=admin&password=admin@123 

3.You will receive both access token and refresh token.
-------------------------------------------------------

{"access_token":"871ab7d5-ded1-4f48-8392-53c634ea44a1","token_type":"bearer","refresh_token":"c9472f25-d976-41d2-8245-b4bb85acfa72","expires_in":119,"scope":"read write trust"}

4.Use the access token to request resources Url. Notices that the access token is valid for 2 minutes.
-------------------------------------------------------------------------------------------------

http://10.30.30.39:8080/RestApiSpringSecurity/fruits?access_token=454b4db8-644e-4e72-bd8f-0b13f5c859fe

Admin Access For UserEnpoint and UserCearetion 
---------------------------------------------------------
http://10.30.30.39:8080/RestApiSpringSecurity/userEndPoint/users?access_token=759cb306-a50c-4630-9509-7bc262c5a155

			{
				"username": "satish",
				"password": "keshri",
				"userRoles":[ {
								"role": "ROLE_USER"
							  }],
				"active": true
			}



Get Token For User
------------------

http://10.30.30.39:8080/RestApiSpringSecurity/oauth/token?grant_type=password&username=satish&password=keshri 

Now User Can Access Other Api Calls
-------------------------------------

http://10.30.30.39:8080/RestApiSpringSecurity/api/fruits?access_token=05405783-f769-4f60-a677-ab4495edce61

5.Once Token InvalidWe get new access token by submitting a post with refresh-token. The URL looks like this: 
------------------------------------------------------------------------------------------------------------

http://10.30.30.39:8080/RestApiSpringSecurity/oauth/token?grant_type=refresh_token&refresh_token=5168074a-fb24-46c2-bb43-786ebd01d00f

That�s all on Secure Spring Rest API Using Spring Security Oauth2 Example.
--------------------------------------------------------------------------

