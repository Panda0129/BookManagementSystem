<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: panda_0129
  Date: 2017/12/7
  Time: 上午10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>admin main</title>
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
                <li class="active">
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
                All books
            </h3>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Book id</th>
                    <th>Name</th>
                    <th>Author</th>
                    <th>Publish</th>
                    <th>Price</th>
                    <th>State</th>
                    <th>return</th>
                    <th>delete</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${bookInfos}" var="info">
                    <c:if test="${info.state == -1}">
                        <tr class="danger">
                    </c:if>
                    <c:if test="${info.state == 0}">
                        <tr class="success">
                    </c:if>
                    <c:if test="${info.state == 1}">
                        <tr>
                    </c:if>
                        <td>${info.book_id}</td>
                        <td><a href="admin_bookInfo.html?id=${info.book_id}">${info.name}</a></td>
                        <td>${info.author}</td>
                        <td>${info.publish}</td>
                        <td>${info.price}</td>
                        <td>
                            <c:if test="${info.state == 1}">
                                在馆
                            </c:if>
                            <c:if test="${info.state == 0}">
                                借出
                            </c:if>
                            <c:if test="${info.state == -1}">
                                挂失
                            </c:if>
                        </td>
                        <td data-th="Return">
                            <a href="return_book.html?book_id=${info.book_id}" class="btn btn-xs btn-primary">Return</a>
                        </td>
                        <td>
                            <a href="admin_deleteBook.html?book_id=${info.book_id}" class="btn btn-xs btn-danger">Delete</a>
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
