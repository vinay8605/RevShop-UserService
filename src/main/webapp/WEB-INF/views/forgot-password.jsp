<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Forgot Password</title>
    <style>
		.container {
		    max-width: 400px;
		    margin: 0 auto;
		    padding: 20px;
		    border: 1px solid #ccc;
		    border-radius: 5px;
		    text-align: center;
		}

		h2 {
		    margin-bottom: 20px;
		}

		.form-group {
		    margin-bottom: 15px;
		}

		label {
		    display: block;
		    margin-bottom: 5px;
		}

		input[type="email"] {
		    width: 100%;
		    padding: 10px;
		    border: 1px solid #ccc;
		    border-radius: 3px;
		}

		button {
		    background-color: #007bff;
		    color: #fff;
		    padding: 10px 20px;
		    border: none;
		    border-radius: 3px;
		    cursor: pointer;
		}

		.error-message {
		    color: red;
		    text-align: center;
		    margin-top: 10px;
		}
    </style>
</head>
<body>
    <div class="container">
        <h2>Forgot Password</h2>
        <form action="/registercr/forgot-password" method="post">
            <div>
                <label for="email">Enter your registered email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <button type="submit">Send OTP</button>
        </form>

        <!-- Display error message if email is not registered -->
        <c:if test="${not empty errorMessage}">
            <p style="color:red;">${errorMessage}</p>
        </c:if>
    </div>
</body>
</html>