<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset="ISO-8859-1">
<title>Order Success</title>
</head>
<body>
<%@include file="Header.jsp"%>
<table class="table table-bordered">
<tr>
<td>Name:</td>
<td>${orderdetail.username}</td>
</tr>
<tr>
<td>Date:
<td>${orderdetail.orderDate}</td>
</tr>
<tr>
<td>TotalAmount:</td>
<td>${orderdetail.totalAmount}</td>
</tr>
<tr>
<td>TransactionType:</td>
<td>${orderdetail.transactionType}</td>
</tr>
<tr>
<td>Shipping Address:</td>
<td>${orderdetail.shippingAddr}</td>
</tr>
</table>
<table class="table table-hover">
<tr>
<td>Product Image</td>
<td>Product Name</td>
<td>Product Price</td>
<td>Quantity</td>
</tr>
<c:forEach items="${listPaidCarts}" var="cart">
<tr>
<td><img src="<c:url value="/resources/images/${cart.productId}.jpg"/>" width="75px" height="75px"><br>
</td>
<td>${cart.productName}</td>
<td>${cart.total}</td>
<td>${cart.quantity}</td>
</tr>
</c:forEach>
<center><h3>Order Successful</h3></center><br>

<center>
<tr>
<td>
<a href="<c:url value="/ProductDisplay"/>" class="btn btn-success">Continue Shopping</a></td>
</tr>
</center>
</table>
</body>
</html>