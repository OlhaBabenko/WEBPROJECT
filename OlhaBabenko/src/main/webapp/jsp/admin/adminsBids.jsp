<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : lookBids
    Created on : 05.06.2013, 11:46:00
    Author     : Оля
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bids Page</title>
        <link rel="stylesheet" type="text/css" href="jsp\css\mainStyle.css">
        <link rel = "stylesheet" type="text/css" href = "jsp\css\menuStyle.css">
    </head>
    <body>
        <h1>
            <!-- Menu -->
            <ul id="menu" class="grey">
                <li>
                    <form method="POST" action="controller">
                        <input type="hidden" name="command" value="goToTenantsPage"/>
                        <input type="submit" value="План работ"/>
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
            Поступившие Заявки Пользователей :
        </h1>
        <a href="controller">Выход</a>
        <hr>
        <table border="1">
            <tr>
                <th>Номер заявки</th>
                <th>ID Квартиросъёмщика</th>
                <th>ФИО Квартиросъёмщика</th>
                <th>Тип работы</th>
                <th>Масштаб работы</th>
                <th>Желаемое время выполнения</th>
                <th>Дата оформления заявки</th>
                <th>Статус заявки</th>
            </tr>
            <c:forEach items="${sessionScope.listOfNewBids}" var="list">
                <tr>
                    <td>
                        <c:out value="${list.id}"/> 
                    </td> 
                    <td>
                        <c:out value="${list.tenant.id}"/> 
                    </td> 
                    <td>
                        <c:out value="${list.tenant.lastName} ${list.tenant.firstName} ${list.tenant.middleName}"/> 
                    </td> 
                    <td>
                        <c:out value="${list.workType.workType}"/> 
                    </td> 
                    <td>
                        <c:out value="${list.workScale.workScale}"/> 
                    </td> 
                    <td>
                        <c:out value="${list.timeToDoWish}"/> 
                    </td> 
                    <td>
                        <c:out value="${list.dateOfFilling}"/> 
                    </td> 
                    <td>
                        <c:out value="${list.status}"/> 
                    </td> 
                </tr>
            </c:forEach>
            <hr>
            <form method="POST" action="controller">
                <input type="hidden" name="command" value="goToWorkPlanInsertingPage"/>
                <input type="submit" value="Занести Заявки в План Работ"/>
            </form>
            <form method="POST" action="controller">
                <input type="hidden" name="command" value="goToBidsDoingPage"/>
                <input type="submit" value="Оформить Заявку"/>
            </form>
            <form method="POST" action="controller">
                <input type="hidden" name="command" value="goToLookeUpAdminsBidsPage"/>
                <input type="submit" value="Просмотр Моих Заявок"/>
            </form>
    </body>
</html>
