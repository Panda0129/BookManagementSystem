<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: panda_0129
  Date: 2017/12/10
  Time: 上午10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>admin search reader info</title>
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
                <li  class="active">
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
    <form action="<c:url value="admin_searchReaderDo.html"/>" method="post">
        <div class="col-md-3"></div>
        <div class="input-group col-md-6" style="margin-top:0px;positon:relative">
            <input type="text" class="form-control" placeholder="Please input reader id or name" name="input">
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
                    <th>Reader ID</th>
                    <th>Name</th>
                    <th>Sex</th>
                    <th>Birth</th>
                    <%--<th>Detail</th>--%>
                    <th>Address</th>
                    <th>Telcode</th>
                    <th>State</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${readerInfos}" var="readerInfo" varStatus="loop">
                    <tr>
                        <td data-th="Reader ID">${readerInfo.id}</td>
                        <td data-th="Name">${readerInfo.name}</td>
                        <td data-th="Sex">${readerInfo.sex}</td>
                        <td data-th="Bitrh">${readerInfo.birth}</td>
                            <%--<td data-th="Detail"><a href="${bookInfo.link}" target="_blank">${bookInfo.link}</a></td>--%>
                        <td>${readerInfo.address}</td>
                        <td>${readerInfo.telcode}</td>
                        <td>
                            <c:if test="${readerCards[loop.count - 1].state == 1 && readerCards[loop.count - 1].reader_id == readerInfo.id}">
                                normal
                            </c:if>
                            <c:if test="${readerCards[loop.count - 1].state == 2 && readerCards[loop.count - 1].reader_id == readerInfo.id}">
                                vip
                            </c:if>
                            <c:if test="${readerCards[loop.count - 1].state == 3 && readerCards[loop.count - 1].reader_id == readerInfo.id}">
                                svip
                            </c:if>
                        </td>
                        <td>
                            <a href="admin_deleteReader.html" class="btn btn-xs btn-danger">delete</a>
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
