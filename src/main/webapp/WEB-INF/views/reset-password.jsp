<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Reset Password</title>
    <style>
		body {
		    font-family: Arial, sans-serif;
		    background-color: #f0f0f0;
		}

		.container {
		    max-width: 400px;
		    margin: 0 auto;
		    padding: 40px;
		    border-radius: 10px;
		    background-color: #fff;
		    box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
		}

		h2 {
		    text-align: center;
		    margin-bottom: 30px;
		}

		.form-group {
		    margin-bottom: 20px;
		}

		label {
		    display: block;
		    font-weight: bold;
		    margin-bottom: 5px;
		}

		input[type="text"],
		input[type="password"],
		input[type="email"],
		input[type="number"] {
		    width: 100%;
		    padding: 10px;
		    border: 1px solid #ccc;
		    border-radius: 5px;
		}

		button {
		    background-color: #007bff;
		    color: #fff;
		    padding: 10px 20px;
		    border: none;
		    border-radius: 5px;
		    cursor: pointer;
		    transition: background-color 0.3s ease-in-out;
		}

		button:hover {
		    background-color: #0062cc;
		}

		.error-message,
		.success-message {
		    text-align: center;
		    margin-top: 10px;
		    padding: 10px;
		    border-radius: 5px;
		}

		.error-message {
		    background-color: #f8d7da;
		    color: #dc3545;
		}

		.success-message {
		    background-color: #d4edda;
		    color: #28a745;
		}
    </style>
    <script>
        // JavaScript function to validate the password fields
        function validateForm() {
            const password = document.getElementById("password").value;
            const confirmPassword = document.getElementById("confirmPassword").value;

            if (password !== confirmPassword) {
                document.getElementById("error-message").innerText = "Passwords do not match!";
                return false; // Prevent form submission
            }

            // If passwords match, allow form submission
            return true;
        }
    </script>
</head>
<body>
    <div class="container">
        <h2>Reset Password</h2>
        <form action="/registercr/reset-password" method="post" onsubmit="return validateForm();">
            <input type="hidden" name="email" value="${email}">
            <div>
                <label for="password">New Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div>
                <label for="confirmPassword">Confirm Password:</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required>
            </div>
            <button type="submit">Reset Password</button>
        </form>

        <!-- Display error message if passwords do not match -->
        <p id="error-message" class="error-message"></p>

        <!-- Display success message if password is reset successfully -->
        <c:if test="${not empty successMessage}">
            <p style="color:green;">${successMessage}</p>
        </c:if>
    </div>
</body>
</html>