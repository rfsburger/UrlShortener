# UrlShortener

To start up the project:

1. Either change the qualifier in the DefaultUrlService to use the urlFileDao or add the required properties for a db connection in the app.properties in webapp/WEB-INF/config. And use the CreateDatabase.sql to setup your db. 
2. start the server up using mvn tomcat7:redeploy.
3. go to http://localhost:8080/urlshortener/create to enter a url and have it "shortened"

What needs to be done still:

1. Logging
2. Unit & Integration Tests

