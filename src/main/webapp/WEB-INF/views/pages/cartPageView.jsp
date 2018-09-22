<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Cartpage</title>
<style>
.table {
	display: table;
	border-collapse: collapse;
	border: 1px solid grey;
}

.table div {
	display: table-row;
	border: 1px solid grey;
}

.table div span {
	padding: 3px;
	border: 1px solid grey;
	display: table-cell;
}

.nameField {
	border: 1px solid #333;
}

.error {
	color: red;
}

.error input {
	border: 1px solid red;
}

.errorField {
	border: 1px solid red;
}
</style>
</head>
<body>
	<h1>Cart</h1>
	<div class="table">

		<c:if test="${not empty cart.entries}">
			<div>
				<span>product</span> <span>quantity</span> <span>price</span>
			</div>
			<c:forEach items="${cart.entries}" var="entry">
				<div>
					<span> ${entry.product}</span> <span>${entry.quantity}</span> <span>${entry.price}</span>
				</div>
			</c:forEach>
		</c:if>
	</div>
	<div>
		<span>cart total</span> <span>${cart.total}</span>
	</div>
	<h2>Products</h2>
	<c:forEach items="${products}" var="product">
		<div>
			<form:form modelAttribute="cartEntry">
				<input id="product" name="product" type="hidden"
					value="${product.code}" />
				<input id="price" name="price" type="hidden"
					value="${product.price}" />
				<div>
					<div>
						<span><form:label path="product">Code</form:label></span> <span>
							${product.code} </span>
					</div>
					<div>
						<span><form:label path="price">Price</form:label></span> <span>${product.price}</span>
					</div>
					<div>
						<span><form:input path="quantity" placeholder="quanity" cssClass="nameField" /></span>
						<form:errors path="quantity" cssClass="error"/>
					</div>
					<span><input type="submit" value="add" /></span>
				</div>
			</form:form>
		</div>

	</c:forEach>

</body>
</html>

