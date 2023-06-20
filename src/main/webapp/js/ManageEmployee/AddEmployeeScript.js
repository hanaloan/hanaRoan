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
                <input type="text" id="empId" name="empId" required>
                <br>
                <label for="empPW">PW:</label>
                <input type="text" id="empPW" name="empPW" required>
                <br>
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required>
                <br>
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
                <br>
                <input type="submit" value="Submit">
            </form>

            <script src="popup-script.js"></script>
        </body>
        </html>
    `;
    popupWindow.document.write(popupContent);
    popupWindow.document.close();
}