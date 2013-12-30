<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="exx" uri="/WEB-INF/tlds/hello"%>

<%-- 
    Document   : response
    Created on : 28.05.2013, 15:07:28
    Author     : Оля
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User's Page</title>
        <link rel="stylesheet" type="text/css" href="jsp\css\mainStyle.css">
        <link rel = "stylesheet" type="text/css" href = "jsp\css\menuStyle.css">
        <link rel = "stylesheet" type="text/css" href = "jsp\css\usersPageStyle.css">
    </head>
    <body>
        <h1>
            <!-- Menu -->
            <ul id="menu" class="grey">
                <li>
                    <form method="POST" action="controller">
                        <input type="hidden" name="command" value="goToTenantsPage"/>
                        <input type="submit" value=<c:out value='${sessionScope.MyPage}'/>/>
                    </form>
                </li>
                <li>
                    <input type="submit" value=<c:out value='${sessionScope.WorkTypes}'/>/>
                </li>
                <li>
                    <input type="submit" value=<c:out value='${sessionScope.Price}'/>/>
                </li>
                <li>
                    <form method="POST" action="controller">
                        <input type="hidden" name="command" value="goToBidsPage"/>
                        <input type="submit" value=<c:out value='${sessionScope.Bids}'/>/>
                    </form>
                </li>
                <li>
                    <input type="submit" value=<c:out value='${sessionScope.Contacts}'/>/>
                </li>
                <p><c:out value='${sessionScope.pageType}'/> [Login : <exx:hello message="${sessionScope.login}"/>]</p>
            </ul>
            <c:out value='${sessionScope.welcomeMessage}'/>
        </h1>
        <form method="POST" action="controller">
            <input type="hidden" name="command" value="LocaleCommand"/>
            <input type="submit" value="en"/>
        </form>
        <form method="POST" action="controller">
            <input type="hidden" name="command" value="LocaleCommandRU"/>
            <input type="submit" value="ru"/>
        </form>
        <a href="controller">Выход</a>
        <hr>
        <div>Персональные данные :</div>
        <hr>
        <jsp:useBean id="tenant" class="ua.kpi.model.Tenant" scope="session"> 
            <jsp:setProperty name="tenant" property="firstName" value='${sessionScope.LastFirstMiddleName}'/>
        </jsp:useBean>
        <table border="1">
            <tbody>
                <tr>
                    <td>Ф.И.О. :</td>
                    <td><jsp:getProperty name="tenant" property="firstName"/></td>
                </tr>
                <tr>
                    <td>Адрес :</td>
                    <td><c:out value='${sessionScope.Address}'/></td>
                </tr>
                <tr>
                    <td>Контактный телефон :</td>
                    <td><c:out value='${sessionScope.Telephone}'/></td>
                </tr>
                <tr>
                    <td>E-mail :</td>
                    <td><c:out value='${sessionScope.Email}'/></td>
                </tr>
            </tbody>
        </table>
    </body>
</html>
