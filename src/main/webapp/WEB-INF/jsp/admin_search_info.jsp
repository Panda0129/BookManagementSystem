<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: panda_0129
  Date: 2017/12/7
  Time: 下午9:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>admin search info</title>
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
                <li class="active">
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
    <br>
    <form action="<c:url value="admin_searchBookDo.html"/>" method="post">
        <div class="col-md-3"></div>
        <div class="input-group col-md-6" style="margin-top:0px;positon:relative">
            <input type="text" class="form-control" placeholder="Please input book id or name or ISBN" name="input">
            <span class="input-group-btn">
                   <button class="btn btn-search btn-primary" type="submit">Search</button>
                </span>
        </div>
    </form>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 class="page-header">
                Query Result
            </h3>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Author</th>
                    <th>Publish</th>
                    <th>Price</th>
                    <%--<th>Detail</th>--%>
                    <th>State</th>
                    <th>Return</th>
                    <th>Report loss</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${bookInfos}" var="bookInfo">
                <tr>
                    <td data-th="Name"><a href="admin_bookInfo.html?id=${bookInfo.book_id}">${bookInfo.name}</a></td>
                    <td data-th="Author">${bookInfo.author}</td>
                    <td data-th="Publish">${bookInfo.publish}</td>
                    <td data-th="Price">${bookInfo.price}</td>
                    <%--<td data-th="Detail"><a href="${bookInfo.link}" target="_blank">${bookInfo.link}</a></td>--%>
                    <td data-th="State">
                        <c:if test="${bookInfo.state == 1}">
                            在馆
                        </c:if>
                        <c:if test="${bookInfo.state == 0}">
                            借出
                        </c:if>
                        <c:if test="${bookInfo.state == -1}">
                            挂失
                        </c:if>
                    </td>
                    <td data-th="Return">
                        <a href="return_book.html?book_id=${bookInfo.book_id}" class="btn btn-xs btn-primary">Return</a>
                    </td>
                    <td>
                        <a href="admin_reportBookLoss.html?book_id=${bookInfo.book_id}" class="btn btn-xs btn-danger">Report</a>
                    </td>
                </tr
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
