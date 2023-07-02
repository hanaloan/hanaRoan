function applyConfirm(productName, productId) {
    let confirmMsg = productName + " 상품을 신청하시겠습니까?";
    let userChoice = confirm(confirmMsg);
    if (userChoice) {
        window.location.href = "applyProduct?productId=" + productId;
    } else {
        return false;
    }
}