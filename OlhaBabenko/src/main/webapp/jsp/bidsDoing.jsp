<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : bidsDoing
    Created on : 05.06.2013, 16:34:45
    Author     : Оля
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Bids Doing Page</title>
        <link rel="stylesheet" type="text/css" href="jsp\css\mainStyle.css">
        <link rel = "stylesheet" type="text/css" href = "jsp\css\menuStyle.css">
        <link rel = "stylesheet" type="text/css" href = "jsp\css\bidsDoingStyle.css">
    </head>
    <body>
        <h1>
            <!-- Menu -->
            <ul id="menu" class="grey">
                <li>
                    <form method="POST" action="controller">
                        <input type="hidden" name="command" value="goToTenantsPage"/>
                        <input type="submit" value="Моя страница"/>
                    </form>
                </li>
                <li>
                    <input type="submit" value="Виды работ"/>
                </li>
                <li>
                    <input type="submit" value="Тарифы"/>
                </li>
                <li>
                    <form method="POST" action="controller">
                        <input type="hidden" name="command" value="goToBidsPage"/>
                        <input type="submit" value="Заявки"/>
                    </form>
                </li>
                <li>
                    <input type="submit" value="Контакты"/>
                </li>
                <p style="color: black"><c:out value='${sessionScope.pageType}'/> [Login : <c:out value='${sessionScope.login}'/>]</p>
            </ul>
            Оформление заявки :
            <hr>
        </h1>
        <form name="bidsDoingForm" method="POST" action="controller">
            <input type="hidden" name="command" value="bidsDoing"/>
            <table>
                <tbody>
                    <tr>
                        <td>
                            <strong>Тип работ :</strong>
                        </td>
                        <td>
                            <p>
                                <select name="workType">
                                    <option>-</option>
                                    <c:forEach items="${sessionScope.workTypeList}" var="workTypeList">
                                        <option> <c:out value="${workTypeList}"/></option>
                                    </c:forEach>
                                </select>
                                *
                            </p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <strong>Масштаб работ :</strong>
                        </td>
                        <td>
                            <p>
                                <select name="workScale">
                                    <option>-</option>
                                    <c:forEach items="${sessionScope.workScaleList}" var="workScaleList">
                                        <option> <c:out value="${workScaleList}"/></option>
                                    </c:forEach>
                                </select>
                                *
                            </p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <strong>Желаемое время<br> выполнения :</strong>
                        </td>
                        <td>
                            <p><input type="text" name="timeToDoWish" value="" size="44" />&nbsp&nbsp</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <strong>Дата заполнения :</strong>
                        </td>
                        <td>
                            <p><input type="text" name="dateOfFilling" value="<c:out value='${sessionScope.dateOfFilling}'/>&nbsp" size="44" readonly="readonly" />&nbsp&nbsp</p>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="color : red; font-size: 14px">
                            <jsp:expression>(request.getAttribute("errorMessage") != null)
                                    ? (String) request.getAttribute("errorMessage")
                                    : "* - обязaтельны для заполнения!"</jsp:expression>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="Отправить Заявку"/>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
        <hr>
        <form method="POST" action="controller">
            <input type="hidden" name="command" value="goToBidsPage"/>
            <input type="submit" value="Просмотр Заявок" style="margin-left: 50%"/>
        </form>
    </body>
</html>
