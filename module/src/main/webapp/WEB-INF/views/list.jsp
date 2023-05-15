<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<body>
<!-- 		다운로드      	 -->
	<form  id="ExcelForm" name="ExcelForm" action="/free/excelDown" method="post">
<!-- 		엑셀 값 확인용 테이블 -->
		<table border="1">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">ID</th>
					<th scope="col">이름</th>
					<th scope="col">성별</th>
					<th scope="col">비밀번호</th>
					<th scope="col">good</th>
				</tr>
			</thead>
			<tbody>
				<c:set value="${listAll }" var="data" />
				<c:forEach items="${listAll }" var="data">
					<tr>
						<td>#</td>
						<td>${data.id }</td>
						<td>${data.name }</td>
						<td>${data.gender }</td>
						<td>${data.password }</td>
						<td>${data.good }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<button type="submit">엑셀 다운</button>
	</form>
	<br>
<!-- 	업로드  -->
			<form id="excelUploadForm" name="excelUploadForm" enctype="multipart/form-data"
      		  method="post" action= "excelUpload">
    
      		    첨부 파일	<br>
   			<input id="excelFile" type="file" name="excelFile" />
          <button type="button" id="addExcelImpoartBtn" class="btn" onclick="check()" ><span>추가하기</span></button>
      </form>


<script type="text/javascript">
function checkFileType(filePath) {
    var fileFormat = filePath.split(".");

    if (fileFormat.indexOf("xls") > -1 || fileFormat.indexOf("xlsx") > -1) {
      return true;
      } else {
      return false;
    }
  }

  function check() {

    var file = $("#excelFile").val();

    if (file == "" || file == null) {
    alert("파일을 선택해주세요.");

    return false;
    } else if (!checkFileType(file)) {
    alert("엑셀 파일만 업로드 가능합니다.");

    return false;
    }

    if (confirm("업로드 하시겠습니까?")) {

      var options = {

        success : function(data) {
            console.log(data);
          alert("모든 데이터가 업로드 되었습니다.");

        },
        type : "POST"
        };
      
      $("#excelUploadForm").submit();
      
    }
  }

</script>
</body>
</html>