<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: panda_0129
  Date: 2017/12/8
  Time: 下午6:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>admin reader list</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    Backstage Management System
                </h1>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <ul class="nav nav-tabs">
                <li>
                    <a href="admin_allBooks.html">Home</a>
                </li>
                <li>
                    <a href="admin_searchBook.html">Query Book</a>
                </li>
                <li>
                    <a href="admin_searchReader.html">Query Reader</a>
                </li>
                <li>
                    <a href="admin_lend_list.html">Lend list</a>
                </li>
                <li class="active">
                    <a href="admin_readerList.html">Reader list</a>
                </li>
                <li>
                    <a href="admin_addBook.html">Add Book</a>
                </li>

                <li>
                    <a href="admin_addReader.html">Add Reader</a>
                </li>
                <li class="pull-right">
                    <a href="index.html">Quit</a>
                </li>
            </ul>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 class="page-header">
                Reader List
            </h3>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Reader Id</th>
                    <th>Reader Name</th>
                    <th>Reader Gender</th>
                    <th>Reader Birth</th>
                    <th>Reader Address</th>
                    <th>Reader Telcode</th>
                    <th>Lend count</th>
                    <th>Card state</th>
                    <th>Delete card</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${readerInfos}" var="readerInfo" varStatus="loop">
                    <tr>
                        <td>${readerInfo.id}</td>
                        <td>${readerInfo.name}</td>
                        <td>${readerInfo.sex}</td>
                        <td>${readerInfo.birth}</td>
                        <td>${readerInfo.address}</td>
                        <td>${readerInfo.telcode}</td>
                        <td>${readerCards[loop.count - 1].lendCount}</td>
                        <td>
                            <c:if test="${readerCards[loop.count - 1].state == 1}">
                                normal
                            </c:if>
                            <c:if test="${readerCards[loop.count - 1].state == 2}">
                                vip
                            </c:if>
                            <c:if test="${readerCards[loop.count - 1].state == 3}">
                                svip
                            </c:if>
                        </td>
                        <td>
                            <a href="admin_deleteReader.html?reader_id=${readerInfo.id}" class="btn btn-xs btn-danger">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
