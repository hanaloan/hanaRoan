function validateInput() {
    var input = document.getElementById("lendAmount");
    var inputValue = input.value;
    var numericValue = inputValue.replace(/[^0-9]/g, ""); // 숫자 이외의 문자 제거

    if (inputValue !== numericValue) {
        input.value = numericValue; // 숫자 이외의 문자 제거된 값으로 입력값 변경
    }
}
function applyProductConfirm(customerName, customerCredit, productName, lendLimit, minCredit){
    let confirmMsg = customerName + " 고객님의 정보로 " + productName + " 상품을 신청하시겠습니까?";
    let userChoice = confirm(confirmMsg);
    let amountValue = document.getElementById("lendAmount").value;
    let numericLendLimit = parseFloat(lendLimit);

    if(amountValue === ""){
        alert("대출신청금액을 입력해주세요.");
        return false;
    }
    else{
        let applyAmount = parseFloat(amountValue);
        if(applyAmount > numericLendLimit){
            alert("신청하시려는 상품의 최대 대출 한도는 " + lendLimit + "입니다. 금액을 확인하신 후 다시 신청해주세요.");
            return false;
        }
        else if(customerCredit < minCredit){
            alert("신청하시려는 상품의 최소신용점수는 " + minCredit + "점으로 본 상품을 이용하실 수 없습니다.");
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
}