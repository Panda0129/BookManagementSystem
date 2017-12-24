<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: panda_0129
  Date: 2017/12/5
  Time: 下午10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>myLendList</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body style="background-color: rgb(211, 211, 211)">

<div class="img-rounded">
    <img src="https://image.g-cores.com/414defa8-5c64-40eb-94c3-f9848d30efd1.jpg" style="width: 100%"/>
</div>

<nav id="primary_nav_wrap">
    <ul>
        <li><a href="allBooks.html">Home</a></li>
        <li><a href="readerInfo.html">Individual Info</a></li>
        <li><a href="reader_modifyReaderInfo.html">Modify Individual Info</a></li>
        <li class="current-menu-item"><a href="reader_my_lendList.html">My Lend</a></li>
        <li><a href="reader_rePwd.html">Change Password</a></li>
        <li><a href="searchBook.html">Query</a> </li>
        <li><a href="index.html">Quit</a></li>
    </ul>
</nav>

<br>
<div class="page-header">
    <h1>Lend list</h1>
</div>

<table class="rwd-table">
    <tr>
        <th>Book_id</th>
        <th>Book_name</th>
        <th>Lend_date</th>
        <th>Back_date</th>
        <th>Return before</th>
        <th>State</th>
        <%--<th>Return</th>--%>
    </tr>
    <c:forEach items="${reader_lend_list}" var="list">
        <tr>
            <td data-th="Book_id">${list.book_id}</td>
            <td data-th="Book_name">${list.book_name}</td>
            <td data-th="Lend_date">${list.lend_date}</td>
            <td data-th="Back_date">${list.back_date}</td>
            <td data-th="Return before">${list.deadline}</td>
            <c:if test="${empty list.back_date}">
                <td>未还</td>
            </c:if>
            <c:if test="${!empty list.back_date}">
                <td>已还</td>
            </c:if>
            <c:if test="">
                <td>超期</td>
            </c:if>

        </tr>
    </c:forEach>
</table>
<p>
</p>
</body>
</html>

<style>
    @import 'https://fonts.googleapis.com/css?family=Montserrat:300,400,700';
    .rwd-table {
        margin: 1em 0;
        min-width: 300px;
    }
    .rwd-table tr {
        border-top: 1px solid #ddd;
        border-bottom: 1px solid #ddd;
    }
    .rwd-table th {
        display: none;
    }
    .rwd-table td {
        display: block;
    }
    .rwd-table td:first-child {
        padding-top: .5em;
    }
    .rwd-table td:last-child {
        padding-bottom: .5em;
    }
    .rwd-table td:before {
        content: attr(data-th) ": ";
        font-weight: bold;
        width: 6.5em;
        display: inline-block;
    }
    @media (min-width: 480px) {
        .rwd-table td:before {
            display: none;
        }
    }
    .rwd-table th, .rwd-table td {
        text-align: left;
    }
    @media (min-width: 480px) {
        .rwd-table th, .rwd-table td {
            display: table-cell;
            padding: .25em .5em;
        }
        .rwd-table th:first-child, .rwd-table td:first-child {
            padding-left: 0;
        }
        .rwd-table th:last-child, .rwd-table td:last-child {
            padding-right: 0;
        }
    }

    body {
        padding: 0 2em;
        font-family: Montserrat, sans-serif;
        -webkit-font-smoothing: antialiased;
        text-rendering: optimizeLegibility;
        color: #444;
        background: #eee;
    }

    h1 {
        font-weight: normal;
        letter-spacing: -1px;
        color: #34495E;
    }

    .rwd-table {
        background: #34495E;
        color: #fff;
        border-radius: .4em;
        overflow: hidden;
    }
    .rwd-table tr {
        border-color: #46637f;
    }
    .rwd-table th, .rwd-table td {
        margin: .5em 1em;
    }
    @media (min-width: 480px) {
        .rwd-table th, .rwd-table td {
            padding: 1em !important;
        }
    }
    .rwd-table th, .rwd-table td:before {
        color: #dd5;
    }

    #primary_nav_wrap
    {
        margin-top:15px
    }

    #primary_nav_wrap ul
    {
        list-style:none;
        position:relative;
        float:left;
        margin:0;
        padding:0
    }

    #primary_nav_wrap ul a
    {
        display:block;
        color:#34495E;
        text-decoration:none;
        font-weight:700;
        font-size:18px;
        line-height:32px;
        padding:0 30px;
        font-family:Montserrat, sans-serif;
    }

    #primary_nav_wrap ul li
    {
        position:relative;
        float:left;
        margin:0;
        padding:0
    }

    #primary_nav_wrap ul li.current-menu-item
    {
        background:#ddd
    }

    #primary_nav_wrap ul li:hover
    {
        background:#f6f6f6
    }

    #primary_nav_wrap ul ul
    {
        display:none;
        position:absolute;
        top:100%;
        left:0;
        background:#fff;
        padding:0
    }

    #primary_nav_wrap ul ul li
    {
        float:none;
        width:200px
    }

    #primary_nav_wrap ul ul a
    {
        line-height:120%;
        padding:10px 15px
    }

    #primary_nav_wrap ul ul ul
    {
        top:0;
        left:100%
    }

    #primary_nav_wrap ul li:hover > ul
    {
        display:block
    }
</style>