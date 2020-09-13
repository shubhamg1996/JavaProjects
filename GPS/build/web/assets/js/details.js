$(document).ready(function() {
    $("#submit").click(function() {
        var username = $("username").val();
        var password = $("password").val();
        alert(username + " " + password);
        $.ajax({
            url: "",
            method: "",
            data: {},
            success: function() {

            },
            error: function() {

            }
        });
    });
});