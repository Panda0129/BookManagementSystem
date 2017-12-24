<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: panda_0129
  Date: 2017/12/6
  Time: 下午4:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>modify Info</title>
</head>
<body>


<form action="<c:url value="/reader_modifyReaderInfoDo.html"/>" method="post">
    <div class="login">

        <h2 class="active">Modify Info</h2>
        <form>
            <br>
            <br>
            <br>
            <input type="text" class="text" name="sex" required>
            <span>sex</span>

            <br>

            <br>
            <input type="date" class="text" name="birth" style="color: #FFFFFF" required>
            <span>birth</span>

            <br>

            <br>
            <input type="text" class="text" name="address" required>
            <span>address</span>

            <br>

            <br>

            <input type="text" class="text" name="telcode" required>
            <span>telcode</span>
            <br>

            <button class="signIn" type="submit">
                Confirm
            </button>

            <button class="signIn" type="submit" onclick="history.back(-1)">
                Back
            </button>

        </form>
    </div>
</form>
</body>
</html>

<style>

    body,
    .signIn {
        background-color: #d3d3d3;
        font-family: 'Montserrat', sans-serif;
        color: #fff;
        font-size: 14px;
        letter-spacing: 1px;
    }

    .login {
        position: relative;
        height: 560px;
        width: 405px;
        margin: auto;
        padding: 60px 60px;
        background: url(http://mikeyoungweb.com/img/bg4.jpg) no-repeat   center center #505050;
        background-size: cover;
        box-shadow: 0px 30px 60px -5px #000;
    }

    form {
        padding-top: 80px;
    }

    .active {
        border-bottom: 2px solid #1161ed;
    }

    h2 {
        padding-left: 12px;
        font-size: 22px;
        text-transform: uppercase;
        padding-bottom: 5px;
        letter-spacing: 2px;
        display: inline-block;
        font-weight: 100;
    }

    h2:first-child {
        padding-left: 0px;
    }

    span {
        text-transform: uppercase;
        font-size: 12px;
        opacity: 0.4;
        display: inline-block;
        position: relative;
        top: -65px;
        transition: all 0.5s ease-in-out;
    }

    .text {
        border: none;
        width: 89%;
        padding: 10px 20px;
        display: block;
        height: 15px;
        border-radius: 20px;
        background: rgba(255, 255, 255, 0.1);
        border: 2px solid rgba(255, 255, 255, 0);
        overflow: hidden;
        margin-top: 15px;
        transition: all 0.5s ease-in-out;
    }

    .text:focus {
        outline: 0;
        border: 2px solid rgba(255, 255, 255, 0.5);
        border-radius: 20px;
        background: rgba(0, 0, 0, 0);
    }

    .text:focus + span {
        opacity: 0.6;
    }

    input[type="text"],
    input[type="password"] {
        font-family: 'Montserrat', sans-serif;
        color: #fff;
    }



    input {
        display: inline-block;
        padding-top: 20px;
        font-size: 14px;
    }

    h2,
    span,
    .custom-checkbox {
        margin-left: 20px;
    }

    .custom-checkbox {
        -webkit-appearance: none;
        background-color: rgba(255, 255, 255, 0.1);
        padding: 8px;
        border-radius: 2px;
        display: inline-block;
        position: relative;
        top: 6px;
    }

    .custom-checkbox:checked {
        background-color: rgba(17, 97, 237, 1);
    }

    .custom-checkbox:checked:after {
        content: '\2714';
        font-size: 10px;
        position: absolute;
        top: 1px;
        left: 4px;
        color: #fff;
    }

    .custom-checkbox:focus {
        outline: none;
    }

    label {
        display: inline-block;
        padding-top: 10px;
        padding-left: 5px;
    }

    .signin {
        background-color: #1161ed;
        color: #FFF;
        width: 100%;
        padding: 10px 20px;
        display: block;
        height: 39px;
        border-radius: 20px;
        margin-top: 30px;
        transition: all 0.5s ease-in-out;
        border: none;
        text-transform: uppercase;
    }

    .signin:hover {
        background: #4082f5;
        box-shadow: 0px 4px 35px -5px #4082f5;
        cursor: pointer;
    }

    .signin:focus {
        outline: none;
    }

    hr {
        border: 1px solid rgba(255, 255, 255, 0.1);
        top: 85px;
        position: relative;
    }

    a {
        text-align: center;
        display: block;
        top: 120px;
        position: relative;
        text-decoration: none;
        color: rgba(255, 255, 255, 0.2);
    }
</style>