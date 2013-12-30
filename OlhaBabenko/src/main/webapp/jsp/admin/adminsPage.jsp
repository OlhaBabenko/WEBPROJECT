<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
        <title>Admin's Page</title>
        <link rel="stylesheet" type="text/css" href="jsp\css\mainStyle.css">
        <link rel = "stylesheet" type="text/css" href = "jsp\css\menuStyle.css">
        <link rel = "stylesheet" type="text/css" href = "jsp\css\adminsPageStyle.css">
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
                <p> <c:out value='${sessionScope.pageType}'/> [Login : <c:out value='${sessionScope.login}'/>]</p>
            </ul>
            <c:out value='${sessionScope.welcomeMessage}'/>
        </h1>
        <a href="controller">Выход</a>
        <hr>
        <hr>
        <div style="text-align: center">План Работ :</div>
        <hr>
        <table border="1">
            <tr>
                <th>№</th>
                <th>Номер заявки</th>
                <th>Номер Квартиросъёмщика</th>
                <th>Тип работы</th>
                <th>Масштаб работы</th>
                <th>Бригада</th>
                <th>Желаемое время выполнения</th>
                <th>Реальное время выполнения</th>
            </tr>
            <c:forEach items="${sessionScope.listOfBidsInWorkPlan}" var="list">
                <tr>
                    <td>
                        <c:out value="${list.id}"/> 
                    </td> 
                    <td>
                        <c:out value="${list.bid.id}"/> 
                    </td>
                    <td>
                        <c:out value="${list.bid.tenant.id}"/> 
                    </td>
                    <td>
                        <c:out value="${list.bid.workType.workType}"/> 
                    </td> 
                    <td>
                        <c:out value="${list.bid.workScale.workScale}"/> 
                    </td> 
                    <td>
                        <c:out value="${list.brigade.id} - ${list.brigade.brigadeKind}"/> 
                    </td>
                    <td>
                        <c:out value="${list.bid.timeToDoWish}"/> 
                    </td>
                    <td>
                        <c:out value="${list.timeToDo}"/> 
                    </td>
                </tr>
            </c:forEach>
            <form method="POST" action="controller">
                <input type="hidden" name="command" value="renewWorkPlanTable"/>
                <input type="submit" value="Обновить План Работ"/>
            </form>
    </body>
</html>
