<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['Work',     11],
          ['Eat',      2],
          ['Commute',  2],
          ['Watch TV', 2],
          ['Sleep',    7]
        ]);

        var options = {
          title: 'My Daily Activities'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
    </script>

</head>
<body>

	<c:set var="contextPath" value="${pageContext.request.contextPath }" />
	
	<c:import url="../default/header.jsp" />
	<div class="wrap">
	<div style="margin: auto; width:500px;"> <!-- margin: auto - 가운데 정렬 -->
		<table border="1" width="600px">
			<tr>
				<th>번호</th>
				<th>id</th>
				<th>제목</th>
				<th>날짜</th>
				<th>조회수</th>
				<th>image_file_name</th>
			</tr>
			
			<c:if test="${boardList.size() == 0 }">
				<tr>
					<td colspan="6">저장 데이터 없음</td>
				</tr>
			</c:if>
			
			<c:forEach var="dto" items="${boardList }">
				<tr>
					<td>${dto.writeNo }</td>
					<td>${dto.id }</td>
					<td><a href="${contextPath }/board/contentView?writeNo=${dto.writeNo }">${dto.title }</a></td>
					<td>${dto.saveDate }</td>
					<td>${dto.hit }</td>
					<td>${dto.imageFileName }</td>
				</tr>
			</c:forEach>
			
			<tr>
				<td colspan="6">
					<div align="left">
						<c:forEach var="num" begin="1" end="${repeat }">
							<a href="boardAllList?num=${num}">${num} &nbsp; </a>
						</c:forEach>
					</div>
					<a href="${contextPath }/board/writeForm">글작성</a>
				</td>
			</tr>
		</table>
	</div>
	</div>
	
	<div id="piechart" style="width: 900px; height: 500px;"></div>
	
</body>
</html>
