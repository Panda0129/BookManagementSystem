<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: panda_0129
  Date: 2017/12/10
  Time: 上午10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>admin search reader</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
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
                <li class="active">
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
</div>
</body>
</body>
</html>
