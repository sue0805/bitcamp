<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%String msg = (String)request.getAttribute("msg"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    label{
        width: 100px;
        display: inline-block;
    }
    input[type=submit]{
        width: 280px;
        margin: auto;
        color: white;
        background-color: black;
        border: 0px;
        padding: 10px 0;
    }
    
    select{
    	width:173px;
    }
    
    input[type=date]{
    	width: 167px;
    }
    
    form>*{
    	margin-bottom: 10px;
    }
    
    #wrap{
    	width: 300px;
    	margin: auto;
    }
    h1{
    	text-align: center;
    }
</style>
</head>
<body>
	<header>
		<jsp:include page="/header/header.jsp"/>
	</header>
    <div id="wrap">
    	<h1>사원 등록 페이지</h1>
    	<hr>
        <form action="/empReg.do">
            <label for="empno">사원번호</label>
            <input type="number" name="empno" id="empno"><br>
            <label for="ename">사원이름</label>
            <input type="text" name="ename" id="ename"><br>
            <label for="job">직급</label>
            <input type="text" name="job" id="job"><br>
            <label for="mgr">매니저</label>
            <select name="mgr" id="mgr">
                <option value="7698">BLAKE</option>
                <option value="7782">CLARK</option>
                <option value="7566">JONES</option>
            </select><br>
            <label for="hiredate">입사일</label>
            <input type="date" name="hiredate" id="hiredate"><br>
            <label for="sal">급여</label>
            <input type="number" name="sal" id="sal"><br>
            <label for="comm">커미션</label>
            <input type="number" name="comm" id="comm"><br>
            <label for="deptno">부서</label>
            <select name="deptno" id="deptno">
            	<option value="10">ACCOUNTING</option>
            	<option value="20">RESEARCH</option>
            	<option value="30">SALES</option>
            	<option value="40">OPERATIONS</option>
            </select>
            <input type="submit" value="등록">
        </form>
    </div>
    <script>
    	<%if(msg!=null){%>
    	alert("<%=msg%>");
    	<%}%>
    </script>
</body>
</html>