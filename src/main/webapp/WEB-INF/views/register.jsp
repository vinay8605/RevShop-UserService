<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Buyer Registration</title>
    <style>
        .error-message {
            color: red;
            margin-bottom: 10px;
        }
        .field-error {
            color: red;
            font-size: 0.8em;
            margin-top: 2px;
        }
        .success-message {
            color: green;
            margin-bottom: 10px;
        }
        form {
            max-width: 500px;
            margin: 0 auto;
            padding: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: inline-block;
            width: 120px;
            margin-bottom: 5px;
        }
        input[type="text"], 
        input[type="password"], 
        input[type="email"], 
        input[type="file"] {
            width: 200px;
            padding: 5px;
        }
    </style>
</head>
<body>
    <h2>Buyer Registration</h2>
    
    <div id="successMessage" class="success-message" style="display: none;">
        Registration successful. Awaiting admin approval.
    </div>
    
    <c:if test="${not empty errorMessage}">
        <div class="error-message">${errorMessage}</div>
    </c:if>

    <form:form modelAttribute="buyer" 
               action="/registercr/register" 
               method="post" 
               enctype="multipart/form-data">
        
        <div class="form-group">
            <label>Name:</label>
            <form:input path="name" />
            <form:errors path="name" cssClass="field-error"/>
        </div>
        
        <div class="form-group">
            <label>Email:</label>
            <form:input path="email" type="email" />
            <form:errors path="email" cssClass="field-error"/>
        </div>
        
        <div class="form-group">
            <label>Password:</label>
            <form:password path="password" />
            <form:errors path="password" cssClass="field-error"/>
        </div>
        
        <div class="form-group">
            <label>Phone Number:</label>
            <form:input path="phoneNumber" />
            <form:errors path="phoneNumber" cssClass="field-error"/>
        </div>
        
        <div class="form-group">
            <label>Address:</label>
            <form:input path="address" />
            <form:errors path="address" cssClass="field-error"/>
        </div>
        
        <div class="form-group">
            <label>City:</label>
            <form:input path="city" />
            <form:errors path="city" cssClass="field-error"/>
        </div>
        
        <div class="form-group">
            <label>State:</label>
            <form:input path="state" />
            <form:errors path="state" cssClass="field-error"/>
        </div>
        
        <div class="form-group">
            <label>Pincode:</label>
            <form:input path="pincode" />
            <form:errors path="pincode" cssClass="field-error"/>
        </div>
        
        <div class="form-group">
            <label>Upload Image:</label>
            <form:input type="file" path="imageFile" accept="image/*" />
        </div>
        
        <div>
            <input type="submit" value="Register" />
        </div>
    </form:form>

    <script>
        if (window.location.search.includes('success=true')) {
            document.getElementById('successMessage').style.display = 'block';
        }
    </script>
</body>
</html>