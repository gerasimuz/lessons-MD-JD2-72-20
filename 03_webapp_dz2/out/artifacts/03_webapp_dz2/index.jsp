<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Adding Users</title>
  </head>
  <body>
  <form action="controller" method="post">
  <input type="hidden" name="command" value="save_new_user">
  Enter name:<br/>
  <input type="text" name="name" value="" /> <br/>
  Enter Surname:<br/>
  <input type="text" name="surname" value="" /> <br/>

  <input type="submit" value="Отправить" /><br/>
  </form>

  </body>
</html>
