// 비동기 통신을 위한 공통 함수
async function sendRequest(url, data) {
    const response = await fetch(url, {
        method: "POST",
        headers: {"Content-Type": "application/x-www-form-urlencoded"},
        body: new URLSearchParams(data)
    });

    if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
    }
}

async function updateLoanStatus(lendId, lendPeriod) {
    const loanStatusElement = document.getElementById('loan-status-' + lendId);
    const previousLoanStatus = loanStatusElement.value;
    if (authType !== "all" && authType !== "managing Customers") {
        alert(`대출 승인 관리에 대한 권한이 없는 관리자 입니다.\n\n현재 권한: ${authType}`);
        loanStatusElement.value = previousLoanStatus;
        location.reload();
        return;
    }

    const loanStatus = document.getElementById('loan-status-' + lendId).value;

    if (loanStatus === "approved") {
        await createLoanPayment(lendId);
    }

    await sendRequest("/ChangeLoanStatus", {lendId, loanStatus, lendPeriod});

    updateDropdownStatus(loanStatusElement, loanStatus);
    location.reload();
}

async function createLoanPayment(lendId) {
    await sendRequest("/CreateLoanPayment", {lendId});
    location.reload();
}

function updateDropdownStatus(selectElement, loanStatus) {
    selectElement.disabled = loanStatus !== 'pending';

    if (loanStatus === 'denied') {
        const cells = selectElement.parentNode.parentNode.getElementsByTagName('td');
        for (let i = 7; i < cells.length; i++) {
            cells[i].style.backgroundColor = '#F0F0F0';
            cells[i].style.color = '#999999';
        }
    }
}

window.onload = function () {
    if (window.location.pathname !== '/LoanApproval') {
        location.href = '/LoanApproval';
    }

    const selectElements = document.querySelectorAll("select[id^='loan-status-']");
    selectElements.forEach((selectElement) => {
        const lendId = selectElement.id.split('-')[2];
        const loanStatus = document.getElementById('loan-status-' + lendId).value;
        updateDropdownStatus(selectElement, loanStatus);
    });
}