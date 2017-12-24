<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: panda_0129
  Date: 2017/12/8
  Time: 上午11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>admin add new book</title>
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
                    <a href="admin_searchBook.html">Query Reader</a>
                </li>
                <li>
                    <a href="admin_lend_list.html">Lend list</a>
                </li>
                <li>
                    <a href="admin_readerList.html">Reader list</a>
                </li>
                <li class="active">
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
                Add new book
            </h3>
            <form role="form" action="<c:url value="admin_addBookDo.html"/> " method="post">
                <div class="form-group">
                    <label>Book_id</label><input type="text" class="form-control" name="book_id" placeholder="唯一的图书id" required/>
                </div>
                <div class="form-group">
                    <label>Book name</label><input type="text" class="form-control" name="name" placeholder="book name" required/>
                </div>
                <div class="form-group">
                    <label>Author</label><input type="text" class="form-control" name="author" placeholder="book author" required/>
                </div>
                <div class="form-group">
                    <label>Publish</label><input type="text" class="form-control" name="publish" placeholder="publish" required />
                </div>
                <div class="form-group">
                    <label>ISBN</label><input type="text" class="form-control" name="ISBN" placeholder="ISBN" required/>
                </div>
                <div class="form-group">
                    <label>Language</label><input type="text" class="form-control" name="language" placeholder="language" required/>
                </div>
                <div class="form-group">
                    <label>Price</label><input type="text" class="form-control" name="price" placeholder="price" required/>
                </div>
                <div class="form-group">
                    <label>Publish Date</label><input type="date" class="form-control" name="pubdate" placeholder="publish date" required/>
                </div>
                <div class="form-group">
                    <label>Class id</label><input type="text" class="form-control" name="class_id"/>
                </div>
                <%--<div class="btn-group">--%>
                    <%--<button class="btn btn-default">Action</button> <button data-toggle="dropdown" class="btn btn-default dropdown-toggle"><span class="caret"></span></button>--%>
                    <%--<ul class="dropdown-menu">--%>
                        <%--<li>--%>
                            <%--<a href="#">操作</a>--%>
                        <%--</li>--%>
                        <%--<li class="disabled">--%>
                            <%--<a href="#">另一操作</a>--%>
                        <%--</li>--%>
                        <%--<li class="divider">--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<a href="#">其它</a>--%>
                        <%--</li>--%>
                    <%--</ul>--%>
                <%--</div>--%>
                <div class="form-group">
                    <label>PressMark</label><input type="text" class="form-control" name="pressmark"/>
                </div>
                <div class="form-group">
                    <label>Detail Link</label><input type="text" class="form-control" name="link"/>
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
