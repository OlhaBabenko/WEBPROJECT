<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 
    Document   : adminsInsertIntoWorkPlanPage
    Created on : 12.06.2013, 23:29:01
    Author     : Оля
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Bids For WorkPlan Page</title>
        <link rel="stylesheet" type="text/css" href="jsp\css\mainStyle.css">
        <link rel = "stylesheet" type="text/css" href = "jsp\css\menuStyle.css">
        <link rel = "stylesheet" type="text/css" href = "jsp\css\adminsWorkPlanDoingStyle.css">
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
            Занести заявки в План Работ :
        </h1>
        <a href="controller">Выход</a> 
        <hr>
        <jsp:expression>(request.getAttribute("successMessage") != null)
                ? (String) request.getAttribute("successMessage")
                : ""</jsp:expression>
            <table border="1">
                <tr>
                    <th>Номер заявки</th>
                    <th>Номер Квартиросъёмщика</th>
                    <th>Тип работы</th>
                    <th>Масштаб работы</th>
                    <th>Бригада</th>
                    <th>Желаемое время выполнения</th>
                    <th>Реальное время выполнения</th>
                    <th>Дата оформления заявки</th>
                    <th>Подтверждение номера заявки</th>
                    <th></th>
                </tr>
            <c:forEach items="${sessionScope.listOfNewBids}" var="list">
                <form method="POST"action="controller">
                    <input type="hidden" name="command" value="InsertIntoWorkPlan"/>
                    <tr>
                        <td>
                            <c:out value="${list.id}"/> 
                        </td> 
                        <td>
                            <c:out value="${list.tenant.id}"/> 
                        </td> 
                        <td>
                            <c:out value="${list.workType.workType}"/> 
                        </td> 
                        <td>
                            <c:out value="${list.workScale.workScale}"/> 
                        </td> 
                        <td>
                            <select name="brigade">
                                <option>-</option>
                                <c:forEach items="${sessionScope.brigadesList}" var="brigList">
                                    <option> <c:out value="${brigList}"/></option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <c:out value="${list.timeToDoWish}"/> 
                        </td> 
                        <td>
                            <input type="text" name="timeToDo" value="" />
                        </td>
                        <td>
                            <c:out value="${list.dateOfFilling}"/> 
                        </td> 
                        <td>
                            <input type="text" name="IDBid" value="" />
                        </td>
                        <td>
                            <input type="submit" value="В план работ"/>
                        </td> 
                    </tr>
                </form>
            </c:forEach>
            <hr>

            <form method="POST" action="controller">
                <input type="hidden" name="command" value="goToBidsPage"/>
                <input type="submit" value="Просмотр Заявок"/>
            </form>

            <form method="POST" action="controller">
                <input type="hidden" name="command" value="goToWorkPlanInsertingPage"/>
                <input type="submit" value="Обновить список Заявок"/>
            </form>


    </body>
</html>
