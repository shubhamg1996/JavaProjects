$(function() {

    $('#login-form-link').click(function(e) {
        $("#login-form").delay(100).fadeIn(100);
        $("#register-form").fadeOut(100);
        $('#register-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('#register-form-link').click(function(e) {
        $("#register-form").delay(100).fadeIn(100);
        $("#login-form").fadeOut(100);
        $('#login-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('#login-submit').click(function() {
        var uname_login = $("#username-login").val();
        var password_login = $("#password-login").val();
        if (uname_login === "" || password_login === "")
            alert("Incomplete form");
        else {
            $.ajax({
                url: 'Login',
                method: 'POST',
                data: {username: uname_login, password: password_login},
                success: function(data) {
                    console.log(data);
                    if (data==="admin")
                    {
//                        alert("Login Successfully");
                        window.location.href = "admin.jsp";
                    }
                    else if(data==="user"){
                        window.location.href="users.jsp";
                    }
                    else{
                        alert("invalid credentials");
                        window.location.href="index.jsp";
                    }
                },
                error: function() {
                    alert("Login failed");
                    window.location.href="index.jsp";
                }
            });
        }
    });
    $("#register-submit").click(function() {
        var uname = $("#username").val();
        var email = $("#email").val();
        var password = $("#password").val();
        var cpassword = $("#confirm-password").val();
        if (uname === "" || email === "" || password === "")
            alert("Incomplete form");
        else if (password !== cpassword)
            alert("Password does not not match");
        else
        {
            $.ajax({
                url: 'User',
                method: 'POST',
                data: {username: uname, email: email, password: password},
                success: function() {
                    alert("Registered Successfully");
                    window.location.href="index.jsp";
                },
                error: function() {
                    alert("Registeration failed");
                }
            });
        }
    });

});
