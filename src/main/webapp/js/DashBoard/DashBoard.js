// Session Timer
var counter = 10 * 60;
var interval;

function formatTime(counter) {
    var minutes = Math.floor(counter / 60);
    var seconds = counter % 60;
    return minutes + ":" + seconds;
}

function startSessionTimer() {
    interval = setInterval(function () {
        counter--;
        if (counter <= 0) {
            clearInterval(interval);
            $('#time').text("Session expired");
        } else {
            $('#time').text(formatTime(counter));
        }
    }, 1000);
}

function extendSession() {
    $.ajax({
        url: '${pageContext.request.contextPath}/extendSession',
        type: 'POST',
        success: function () {
            counter = 10 * 60;
        },
        error: function () {
            console.log("Error extending the session");
        }
    });
}

// AJAX requests
function handleAjaxRequest(url, method, onSuccess, onError) {
    $.ajax({
        url: url,
        method: method,
        success: onSuccess,
        error: onError || function (xhr, status, error) {
            console.log("Error: " + error);
        }
    });
}

$(document).ready(function () {
    startSessionTimer();

    handleAjaxRequest("/DashboardPending", "GET", function (response) {
        // Handle the success response if needed
    });

    handleAjaxRequest("/GetTotalLoanAmount", "GET", function (response) {
        var formattedLoanAmount = parseFloat(response.loanAmount).toLocaleString();
        $('#loanAmount').text(formattedLoanAmount);
    });

    handleAjaxRequest("/GetOverdueLoanAmount", "GET", function (response) {
        var formattedLoanAmount = parseFloat(response.overdueLoanAmount).toLocaleString();
        $('#overdueLoanAmount').text(formattedLoanAmount);
    });

    handleAjaxRequest("/GetCountPendingLends", "GET", function (response) {
        $('#getCountPendingLends').text(response.getCountPendingLends);
    });

    handleAjaxRequest("/GetOverduePercentage", "GET", function (response) {
        var percentage = parseFloat(response.getOverduePercentage).toFixed(1);
        $('#getOverduePercentage').text(percentage);
        $('#card-pg-bar').attr('aria-valuenow', percentage).css('width', percentage + '%');
    });

    handleAjaxRequest("/GetRatioOfLoanType", "GET", function (response) {
        var monthlyRentPercentage = parseFloat(response["월세대출"] || 0).toFixed(1);
        var yearlyRentPercentage = parseFloat(response["전세대출"] || 0).toFixed(1);
        var collateralRentPercentage = parseFloat(response["담보대출"] || 0).toFixed(1);
        $('#monthlyRentPercentage').text(monthlyRentPercentage + "%");
        $('#yearlyRentPercentage').text(yearlyRentPercentage + "%");
        $('#collateralRentPercentage').text(collateralRentPercentage + "%");
        $('#monthlyRentRentBar').attr('aria-valuenow', monthlyRentPercentage).css('width', monthlyRentPercentage + '%');
        $('#monthlyRentBar').attr('aria-valuenow', yearlyRentPercentage).css('width', yearlyRentPercentage + '%');
        $('#collateralRentBar').attr('aria-valuenow', collateralRentPercentage).css('width', collateralRentPercentage + '%');
    });
});
