<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : index
    Created on : 28.05.2013, 15:00:33
    Author     : Оля
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" type="text/css" href="jsp\css\loginStyle.css">
        <link rel="stylesheet" type="text/css" href="jsp\css\mainStyle.css">
    </head>
    <body>
        <h1><strong>Жилищно-коммунальные услуги</strong></h1>
        <hr>
        <form name="loginForm" method="POST" action="controller">
            <input type="hidden" name="command" value="login"/>
            <input type="hidden" name="command" value="getUserName"/>
            <table>
                <tbody>
                    <tr>
                        <td>
                            <strong>Логин :</strong>
                        </td>
                        <td>
                            <p><input type="text" name="login" value="" size="13" />*</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <strong>Пароль :</strong>
                        </td>
                        <td>
                            <p><input type="password" name="password" value="" size="13" />*</p>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="color : red; font-size: 14px">
                            <bean:parameter id="errorMessage" name="errorMessage"/>
                            <jsp:expression>(request.getAttribute("errorMessage") != null)
                                    ? (String) request.getAttribute("errorMessage")
                                    : "* - обязaтельны для заполнения!"</jsp:expression>
                        </td>
                    </tr>
                </tbody>
            </table>
            <p style="padding-left: 10px"><input type="submit" value="Войти" style="background: burlywood"/></p>
        </form>
        <hr>
        <form name="registrationForm" action="controller" method="POST">
            <input type="hidden" name="command" value="goToRegistrationPage"/>
            <p style="padding-left: 10px"><input type="submit" value="Регистрация" style="background: burlywood"/></p>
        </form>
    </body>
</html>
