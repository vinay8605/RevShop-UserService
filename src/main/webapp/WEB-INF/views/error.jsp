<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            padding: 50px;
        }
        .error-container {
            display: inline-block;
            text-align: left;
            max-width: 500px;
            margin-top: 20px;
        }
        h1 {
            color: #d9534f;
        }
        p {
            color: #333;
        }
        .code {
            font-weight: bold;
            color: #d9534f;
        }
        .back-link {
            margin-top: 20px;
            display: inline-block;
            padding: 10px 15px;
            background-color: #5bc0de;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        .back-link:hover {
            background-color: #31b0d5;
        }
    </style>
</head>
<body>
    <h1>Oops! Something went wrong.</h1>
    <div class="error-container">
        <p>An error occurred while processing your request.</p>
        <p><span class="code">Error Code:</span> <%= request.getAttribute("jakarta.servlet.error.status_code") %></p>
        <p><span class="code">Message:</span> <%= request.getAttribute("jakarta.servlet.error.message") %></p>
        <p><span class="code">Exception:</span> <%= request.getAttribute("jakarta.servlet.error.exception") %></p>
        <p><span class="code">URI:</span> <%= request.getAttribute("jakarta.servlet.error.request_uri") %></p>
        <a href="/" class="back-link">Go Back to Home</a>
    </div>
</body>
</html>
