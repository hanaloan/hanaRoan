function openPopup() {
    const valueToPass = 'Hello, Popup!'; // The value you want to pass

    const popupWindow = window.open('', '_blank', 'width=400,height=300');
    const popupContent = `
        <html>
        <head>
            <title>관리자 추가</title>
            <link rel="stylesheet" type="text/css" href="popup-styles.css">
        </head>
        <body>
            <h2>Enter Add Employee:</h2>
            <form id="popupForm">
                <label for="empID">ID:</label>
                <input type="text" id="param1" name="empId"  placeholder="Parameter 1" required>
                <br>
                <label for="empPW">PW:</label>
                <input type="text" id="param2" name="empPW" required>
                <br>
                <label for="name">Name:</label>
                <input type="text" id="param3" name="empName" required>
                <br>
                
                <form id="popupForm" action="../../../Controller/EmployeeManagementController" method="POST">
                  <label for="authority">권한:</label>
                  <select name="authority" id="param4">
                    <option value="all">all</option>
                    <option value="managingProducts">managing Products</option>
                    <option value="managingCustomers">managing Customers</option>
                    <option value="readOnly">read only</option>
                    <option value="none">none</option>
                  </select>
<!--                  <input type="submit" value="Submit" />-->
                </form>
                <br>
                <button onclick="submitValue()">Submit</button>
            </form>

            <script>
                function submitValue() {
                    let param1 = document.getElementById("param1").value;
                    let param2 = document.getElementById("param2").value;
                    let param3 = document.getElementById("param3").value;
                    let param4 = document.getElementById("param4").value;
                    console.log("Submit 버튼 누름")
                    console.log(param1)
                    console.log(param2)
                    console.log(param3)
                    console.log(param4)
                    // const empId = document.getElementById('empId').value;
                    // const empPw = document.getElementById('empPw').value;
                    // const empName = document.getElementById('empName').value;
                    // const authority = document.getElementById('authority').value;
                    // window.opener.document.getElementById("popupForm").empId.value = empId;
                    // window.opener.document.getElementById("popupForm").empPw.value = empPw;
                    // window.opener.document.getElementById("popupForm").empName.value = empName;
                    // window.opener.document.getElementById("popupForm").authority.value = authority;
                    
                    window.opener.document.getElementById("param1").value = param1;
                    console.log( window.opener.document.getElementById("param1").value)
                    window.opener.document.getElementById("param2").value = param2;
                    window.opener.document.getElementById("param3").value = param3
                    window.opener.document.getElementById("param4").value = param4;
                    console.log("윈도우 닫히나?")

                    // window.opener.document.getElementById("popupForm").submit();
                    // window.opener.postMessage(inputValue, '*');
                    document.getElementById("popupForm").submit();
                    window.close();
                }
            </script>
        </body>
        </html>
    `;
    popupWindow.document.write(popupContent);
    popupWindow.document.close();
}