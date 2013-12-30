<%-- 
    Document   : registration
    Created on : 29.05.2013, 22:24:26
    Author     : Оля
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Page</title>
        <link rel="stylesheet" type="text/css" href="jsp\css\registrationStyle.css">
        <link rel="stylesheet" type="text/css" href="jsp\css\mainStyle.css">
    </head>
    <body>
        <h1>Для регистрации заполните форму :</h1>
        <hr>
        <form name="registrationForm" method="POST" action="controller">
            <input type="hidden" name="command" value="registration"/>
            <table>
                <tbody>
                    <tr>
                        <td>
                            <strong>Фамилия :</strong>
                        </td>
                        <td>
                            <p><input type="text" name="lastName" value="" size="15" onkeyup="if(/[^A-Za-zА-Яа-я\-\ ]/.test(this.value))this.value=this.value.replace(/[^A-Za-zА-Яа-я\-\ ]+/g,'');"/>*</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <strong>Имя :</strong>
                        </td>
                        <td>
                            <p><input type="text" name="firstName" value="" size="15"  onkeyup="if(/[^A-Za-zА-Яа-я\-\ ]/.test(this.value))this.value=this.value.replace(/[^A-Za-zА-Яа-я\-\ ]+/g,'');"/>*</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <strong>Отчество :</strong>
                        </td>
                        <td>
                            <p><input type="text" name="middleName" value="" size="15"  onkeyup="if(/[^A-Za-zА-Яа-я]/.test(this.value))this.value=this.value.replace(/[^A-Za-zА-Яа-я]+/g,'');"/>*</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <strong>Город :</strong>
                        </td>
                        <td>
                            <p><input type="text" name="city" value="" size="15" />*</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <strong>Адрес :</strong>
                        </td>
                        <td>
                            <p><input type="text" name="address" value="" size="15" />*</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <strong>Телефон :</strong>
                        </td>
                        <td>
                            <p><input type="text" name="telephone" value="" size="15" onkeyup="if(/[^\d]/.test(this.value))this.value=this.value.replace(/[^\d]+/g,'');"/>*</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <strong>E-mail :</strong>
                        </td>
                        <td>
                            <p><input type="text" name="email" value="" size="15" onkeyup="if(/[^\w\.\_\@]/.test(this.value))this.value=this.value.replace(/[^\w\_\.\@]+/g,'');"/>&nbsp</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <strong>Логин :</strong>
                        </td>
                        <td>
                            <p><input type="text" name="login" value="" size="15" />*</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <strong>Пароль :</strong>
                        </td>
                        <td>
                            <p><input type="password" name="password" value="" size="15" />*</p>
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
                            <input type="submit" value="Зарегистрироваться" style="background: burlywood"/>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
        <a href="controller">На главную страницу</a>
    </body>
</html>
