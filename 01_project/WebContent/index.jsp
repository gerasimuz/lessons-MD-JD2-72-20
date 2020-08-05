<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>User's Info</title>

</head>
<body>
<h2>Р’РІРµРґРёС‚Рµ РґР°РЅРЅС‹Рµ Рѕ СЃРµР±Рµ</h2>2
<form action="Controller" method="post">
	<input type="hidden" name ="command" value="forward" />
	<br/><input type="text" name="firstName" placeholder="РРјСЏ" />
	<br/><input type="text" name="secondName" placeholder="Р¤Р°РјРёР»РёСЏ" />
	<br/><input type="text" name="thirdName" placeholder="РћС‚С‡РµСЃС‚РІРѕ" />
	<br/><input type="email" name="email" placeholder="РїРѕС‡С‚Р° email" />
	<br/><input type="text" name="mobile" placeholder="РЅРѕРјРµСЂ С‚РµР»РµС„РѕРЅР°" />
	<br/><input type="submit" value="РћС‚РїСЂР°РІРёС‚СЊ" />
</form>
</body>
</html>