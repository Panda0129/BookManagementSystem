<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: panda_0129
  Date: 2017/12/8
  Time: 下午5:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>admin add reader</title>
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
                <li>
                    <a href="admin_searchBook.html">Query Reader</a>
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

                <li class="active">
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
                Add new reader card
            </h3>
            <form role="form" action="<c:url value="admin_addReaderDo.html"/> " method="post">
                <div class="form-group">
                    <label>Reader_id</label><input type="text" class="form-control" name="reader_id" placeholder="唯一的读者id" required/>
                </div>
                <div class="form-group">
                    <label>Reader name</label><input type="text" class="form-control" name="name" placeholder="reader name" required/>
                </div>
                <div class="form-group">
                    <label>password</label><input type="password" class="form-control" name="pwd" required/>
                </div>
                <div class="form-group">
                    <label>state</label><input type="text" class="form-control" name="state" value="1" required />
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
        </div>
    </div>
</div>
</body>
</body>
</html>
