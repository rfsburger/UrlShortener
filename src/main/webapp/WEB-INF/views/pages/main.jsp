<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>ShortUrl</title>
</head>
<body>
<form action="<c:url value="/create"/>" method="POST">
    Long Url:<br>
    <input type="text" name="longUrl" placeholder="Url you wish to shorten..."><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
