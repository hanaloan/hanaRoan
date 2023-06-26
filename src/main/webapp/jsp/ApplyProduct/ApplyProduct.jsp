<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h5>상품 신청서</h5>
    <form action="applyProduct" method="post" accept-charset="UTF-8">
        <h5>신청인: ${customerName}</h5>
        <h5>신청상품명: ${applyProductRes.productName}</h5>
        <input type="hidden" class="customer-idx" name="customerIdx" value='${customerIdx}'>
        <input type="hidden" class="loan-idx" name="loanIdx" value='${applyProductRes.productId}'>
        <label for="lendAmount">대출금액: </label>
        <input id="lendAmount" name="lendAmount" type="text">
        <button id="loanApply" onclick="return applyProductConfirm('${customerName}', '${applyProductRes.productName}', '${applyProductRes.lendLimit}')">대출신청</button>
    </form>
</body>
<script>
    function applyProductConfirm(customerName, productName, lendLimit){
        var confirmMsg = customerName + " 고객의 정보로 " + productName + " 상품을 신청하시겠습니까?";
        var userChoice = confirm(confirmMsg);
        var applyAmount = parseFloat(document.getElementById("lendAmount").value);
        var numericLendLimit = parseFloat(lendLimit);

        if(applyAmount > numericLendLimit){
            alert("신청하시려는 상품의 최대 대출 한도는 " + lendLimit + "입니다. 금액을 확인하신 후 다시 신청해주세요.");
            return false;
        }
        else{
            if(userChoice){
                alert("신청이 완료되었습니다. 메인페이지로 이동합니다.");
                window.location.href = "/applyProduct";
            }
            else{
                return false;
            }
        }
    }
</script>
</html>
