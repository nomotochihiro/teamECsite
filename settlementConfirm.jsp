<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/table.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<title>決済確認画面</title>
<script type="text/javascript">
	function setAction(url) {
		document.getElementById("form").action = url;
	}
</script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="main">
		<div id="top">
			<h1>決済確認画面</h1>
		</div>
		<div class="info">
			<s:property value="message" />
		</div>
		<s:if
			test="destinationInfoDTOList != null && destinationInfoDTOList.size() > 0">
			<s:form id="form">
				<table class="horizontal_table">
					<tr>
						<th><label>#</label></th>
						<th><label>姓</label></th>
						<th><label>名</label></th>
						<th><label>ふりがな</label></th>
						<th><label>住所</label></th>
						<th><label>電話番号</label></th>
						<th><label>メールアドレス</label></th>
					</tr>
					<s:iterator value="destinationInfoDTOList" status="st">
						<tr>
							<td><s:if test="%{#st.index == 0}">
									<input type="radio" name="id" value="<s:property value='id' />"
										checked="checked" />
								</s:if> <s:else>
									<input type="radio" name="id" value="<s:property value='id' />" />
								</s:else></td>
							<td><s:property value="familyName" /></td>
							<td><s:property value="firstName" /></td>
							<td><s:property value="familyNameKana" /><span> </span> <s:property
									value="firstNameKana" /></td>
							<td><s:property value="userAddress" /></td>
							<td><s:property value="telNumber" /></td>
							<td><s:property value="email" /></td>
						</tr>
					</s:iterator>
				</table>
				<div class="submit_btn_box2">
					<s:submit value="決済" class="submit_btn"
						onclick="setAction('SettlementCompleteAction')" />
				</div>
				<div class="submit_btn_box2">
					<s:submit value="削除" class="submit_btn"
						onclick="setAction('DeleteDestinationAction')" />
				</div>
			</s:form>
		</s:if>
		<s:form action="CreateDestinationAction">
			<div class="submit_btn_box2">
				<s:submit value="新規宛先登録" class="submit_btn" />
			</div>
		</s:form>
	</div>
</body>
</html>