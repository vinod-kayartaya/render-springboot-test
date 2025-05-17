<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Health Check History</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        .status-up {
            color: green;
        }
        .status-down {
            color: red;
        }
    </style>
</head>
<body>
    <h1>Health Check History</h1>
    <table>
        <thead>
            <tr>
                <th>Time</th>
                <th>Status</th>
                <th>Details</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${healthChecks}" var="check">
                <tr>
                    <td>${check.checkTime}</td>
                    <td class="status-${check.status.toLowerCase()}">${check.status}</td>
                    <td>${check.details}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html> 