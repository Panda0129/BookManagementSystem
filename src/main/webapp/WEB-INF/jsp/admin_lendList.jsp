<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: panda_0129
  Date: 2017/12/7
  Time: 下午10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>admin lend list</title>
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
                <li class="active">
                    <a href="admin_lend_list.html">Lend list</a>
                </li>
                <li>
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
            </li>
            </ul>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 class="page-header">
                Lend List
            </h3>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Book Name</th>
                    <th>Reader Name</th>
                    <th>Reader id</th>
                    <th>Lend date</th>
                    <th>Back date</th>
                    <th>Return before</th>
                    <th>State</th>
                    <th>Return</th>
                    <th>Report loss</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${bookInfos}" var="bookInfo" varStatus="loop">
                        <tr>
                            <td>${bookInfo.name}</td>
                            <td>${readerInfos[loop.count-1].name}</td>
                            <td>${readerInfos[loop.count-1].id}</td>
                            <td>${lendLists[loop.count-1].lend_date}</td>
                            <td>${lendLists[loop.count-1].back_date}</td>
                            <td>${lendLists[loop.count-1].deadline}</td>
                            <td>
                                <c:if test="${bookInfo.state == -1}">
                                    挂失
                                </c:if>
                                <c:if test="${bookInfo.state == 0}">
                                    借出
                                </c:if>
                                <c:if test="${bookInfo.state == 1}">
                                    在馆
                                </c:if>
                            </td>
                            <td data-th="Return">
                                <a href="return_book.html?book_id=${bookInfo.book_id}" class="btn btn-xs btn-primary">Return</a>
                            </td>
                            <td>
                                <a href="admin_reportBookLoss.html?book_id=${bookInfo.book_id}" class="btn btn-xs btn-danger">Report</a>
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
