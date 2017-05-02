<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {color: green;}
        .exceed {color: red;}
    </style>
</head>
<body>

<h2>Meal list</h2>
<table border="1">
    <tr>
        <td>
            DateTime
        </td>
        <td>
            Description
        </td>
        <td>
            Calories
        </td>
        <td>
            Exceed
        </td>
    </tr>
    <c:forEach var="meal" items="${list}" >
        <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.UserMealWithExceed"/>
            <tr class="${meal.exceed ? 'exceed': 'normal'}">
                <td>
                        ${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}
                </td>
                <td>
                        ${meal.description}
                </td>
                <td>
                        ${meal.calories}
                </td>
                <td>
                        ${meal.exceed}
                </td>
            </tr>
    </c:forEach>
</table>


</body>
</html>
