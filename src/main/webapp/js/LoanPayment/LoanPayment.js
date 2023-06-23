function confirmDeductBalance(authType, passedDate) {
    if (authType === "managing Customers" || authType === "read only" || authType === "none") {
        alert("권한이 없습니다.");
        return false;
    }
    else if(passedDate <= 0){
        alert("상환일이 아닙니다.");
        return false;
    }
    else {
        var confirmMsg = "잔액을 차감하시겠습니까?";
        var userChoice = confirm(confirmMsg);
        if (userChoice) {
            alert("잔액이 차감되었습니다.");
            return true;
        } else {
            return false;
        }
    }
}

function confirmHandleOverdue(authType, paymentStatus, passedDate) {
    if (authType === "managing Customers" || authType === "read only" || authType === "none") {
        alert("권한이 없습니다.");
        return false;
    }
    else if(passedDate <= 0){
        alert("상환기한이 지나지 않은 데이터입니다.");
        return false;
    }
    else {
        var confirmMsg, confirmAlert;
        if(paymentStatus === "연체"){
            confirmMsg = "연체금을 갱신하시겠습니까?";
            confirmAlert = "연체금이 갱신되었습니다."
        }
        else{
            confirmMsg = "해당 상환 데이터의 상태를 연체로 변경하시겠습니까?";
            confirmAlert = "연체 상태로 변경되었습니다.";
        }
        var userChoice = confirm(confirmMsg);
        if (userChoice) {
        alert(confirmAlert);
        return true;
        } else {
            return false;
        }
    }
}
