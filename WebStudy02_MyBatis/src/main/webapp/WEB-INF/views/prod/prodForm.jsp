<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<script src="<c:url value='/resources/js/app/prod/prodForm.js'/>"></script>
</head>
<body>
<div class="card">
<div class="card-header">
	<h5>상품 등록</h5>
</div>
<div class="card-body">
<div class="row">
	<form method="post" enctype="application/x-www-form-urlencoded">
		<c:if test="${action eq 'update' }">
			<input type="hidden" id="prodId" name="prodId" value="${prod.prodId}"
				class="form-control" placeholder="상품코드">
			<span class="text-danger">${errors.prodId}</span>
		</c:if>
		<div class="form-group">
			<label class="form-label" for="prodName">상품명</label>
			<input type="text" id="prodName" name="prodName"
				value="${prod.prodName}" class="form-control" placeholder="상품명">
			<span class="text-danger">${errors.prodName}</span>
		</div>
		<div class="form-group">
			<label class="form-label" for="lprodGu">분류코드(외래키, 부모테이블:
				LPROD)</label>
			<select name="lpordGu" id="lprodGu" class="form-select"
				data-init-val="${prod.lprodGu}"
			>
				<option value="">분류선택</option>
			</select>
			<span class="text-danger">${errors.lprodGu}</span>
		</div>
		<div class="form-group">
			<label class="form-label" for="buyerId">거래처코드(외래키, 부모테이블:
				BUYER)</label>
			<select name="buyerId" id="buyerId" class="form-select"
				data-init-val="${prod.buyerId}"
			>
				<option value="">제조사 선택</option>
			</select>
			<span class="text-danger">${errors.buyerId}</span>
		</div>
		<div class="form-group">
			<label class="form-label" for="prodCost">매입단가</label>
			<input type="number" id="prodCost" name="prodCost"
				value="${prod.prodCost}" class="form-control" placeholder="매입단가">
			<span class="text-danger">${errors.prodCost}</span>
		</div>
		<div class="form-group">
			<label class="form-label" for="prodPrice">매출단가</label>
			<input type="number" id="prodPrice" name="prodPrice"
				value="${prod.prodPrice}" class="form-control" placeholder="매출단가">
			<span class="text-danger">${errors.prodPrice}</span>
		</div>
		<div class="form-group">
			<label class="form-label" for="prodSale">할인판매단가</label>
			<input type="number" id="prodSale" name="prodSale"
				value="${prod.prodSale}" class="form-control"
				placeholder="할인판매단가">
			<span class="text-danger">${errors.prodSale}</span>
		</div>
		<div class="form-group">
			<label class="form-label" for="prodOutline">대충 설명</label>
			<input type="text" id="prodOutline" name="prodOutline"
				value="${prod.prodOutline}" class="form-control"
				placeholder="대충 설명">
			<span class="text-danger">${errors.prodOutline}</span>
		</div>
		<div class="form-group">
			<label class="form-label" for="prodDetail">자세한 설명</label>
			<input type="text" id="prodDetail" name="prodDetail"
				value="${prod.prodDetail}" class="form-control"
				placeholder="자세한 설명">
			<span class="text-danger">${errors.prodDetail}</span>
		</div>
		<div class="form-group">
			<label class="form-label" for="prodImg">상품이미지</label>
			<input type="text" id="prodImg" name="prodImg" value="${prod.prodImg}"
				class="form-control" placeholder="상품이미지">
			<span class="text-danger">${errors.prodImg}</span>
		</div>
		<div class="form-group">
			<label class="form-label" for="prodTotalstock">전재고량</label>
			<input type="number" id="prodTotalstock" name="prodTotalstock"
				value="${prod.prodTotalstock}" class="form-control"
				placeholder="전재고량">
			<span class="text-danger">${errors.prodTotalstock}</span>
		</div>
		<%-- <div class="form-group">
			<label class="form-label" for="prodInsdate">입고일자</label>
			<input type="date" id="prodInsdate" name="prodInsdate"
				value="${prod.prodInsdate}" class="form-control"
				placeholder="입고일자">
			<span class="text-danger">${errors.prodInsdate}</span>
		</div> --%>
		<div class="form-group">
			<label class="form-label" for="prodProperstock">적정재고</label>
			<input type="number" id="prodProperstock" name="prodProperstock"
				value="${prod.prodProperstock}" class="form-control"
				placeholder="적정재고">
			<span class="text-danger">${errors.prodProperstock}</span>
		</div>
		<div class="form-group">
			<label class="form-label" for="prodSize">크기</label>
			<input type="text" id="prodSize" name="prodSize"
				value="${prod.prodSize}" class="form-control" placeholder="크기">
			<span class="text-danger">${errors.prodSize}</span>
		</div>
		<div class="form-group">
			<label class="form-label" for="prodColor">색상</label>
			<input type="text" id="prodColor" name="prodColor"
				value="${prod.prodColor}" class="form-control" placeholder="색상">
			<span class="text-danger">${errors.prodColor}</span>
		</div>
		<div class="form-group">
			<label class="form-label" for="prodDelivery">배달주의사항</label>
			<input type="text" id="prodDelivery" name="prodDelivery"
				value="${prod.prodDelivery}" class="form-control"
				placeholder="배달주의사항">
			<span class="text-danger">${errors.prodDelivery}</span>
		</div>
		<div class="form-group">
			<label class="form-label" for="prodUnit">판매단위</label>
			<input type="text" id="prodUnit" name="prodUnit"
				value="${prod.prodUnit}" class="form-control" placeholder="판매단위">
			<span class="text-danger">${errors.prodUnit}</span>
		</div>
		<div class="form-group">
			<label class="form-label" for="prodQtyin">포장수량</label>
			<input type="number" id="prodQtyin" name="prodQtyin"
				value="${prod.prodQtyin}" class="form-control" placeholder="포장수량">
			<span class="text-danger">${errors.prodQtyin}</span>
		</div>
		<div class="form-group">
			<label class="form-label" for="prodQtysale">판매단위(수량)</label>
			<input type="number" id="prodQtysale" name="prodQtysale"
				value="${prod.prodQtysale}" class="form-control"
				placeholder="판매단위(수량)">
			<span class="text-danger">${errors.prodQtysale}</span>
		</div>
		<div class="form-group">
			<label class="form-label" for="prodMileage">마일리지</label>
			<input type="number" id="prodMileage" name="prodMileage"
				value="${prod.prodMileage}" class="form-control"
				placeholder="마일리지">
			<span class="text-danger">${errors.prodMileage}</span>
		</div>
		<button class="btn btn-primary" type="submit">저장</button>
		<button class="btn btn-danger" type="reset">취소</button>
	</form>
</div>
</div>
</div>
</body>
</html>