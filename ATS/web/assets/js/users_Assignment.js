$(document).ready(function() {
    $.ajax({
        url: "ViewAssignment",
        method: "POST",
        success: function(data) {
            var html = "";

            for (var i = 0; i < data.length; i++) {
                var obj = data[i];
                var colorclass;
                var deadline = new Date(obj.deadline);
                var today = new Date();

                if (today > deadline)
                    colorclass = 'delayed';
                else
                    colorclass = 'intime';

                html += '<tr class="' + colorclass + '">'
                        + '<td >' + (i + 1) + '</td>'
                        + '<td><form action="DownloadServlet" method="POST"><input type="hidden" name="filepath" value="' + obj.filename + '">\n\
<button class="btn btn-link">' + obj.title + '</button></form></td>'
                        + '<td >' + obj.description + '</td>'
                        + '<td >' + obj.created_on + '</td>'
                        + '<td >' + obj.deadline + '</td>'
                        + '<td ><form action="Submit" method="POST" enctype="multipart/form-data"><input type="hidden" value="' + obj.assignment_id + '" name="assid"><input type="file" name="file" onchange="submit();"></form></td>'
                        + '</tr>';
            }
            $('#mytableBody').html(html);
            $('#viewTable').dataTable();
        },
        error: function(data) {
            console.log(data);
        }
    });
    $("#viewTable").on('click', 'button[name="submit"]', function() {
        alert("UserID " + $(this).data('id'));
        $.ajax({
            url: "",
            method: "POST",
            success: function(data) {
                console.log(data);
            },
            error: function(data) {
                console.log(data);
            }
        });
    });
    $("#submission").click(function() {
//        var tds = $(btn).parent('td').siblings('td');
        var html = "";
        var userid = $(this).data('id');
        $.ajax({
            url: "ViewAssignment",
            method: "POST",
            data: {userid: userid},
            success: function(data) {
                $("#userSubmission").dataTable({
                    data: data,
                    columns: [{data: 'id'}, {data: 'file'}, {data: 'action'}],
                    destroy: true,
                    dom: 'Bfrtip',
                    buttons: [
                        'csv', 'excel', 'print'
                    ]
                });
                $(".modal").modal();
            },
            error: function(data) {
                console.log(data);
            }
        });
    });
    $("#userSubmission").on('click', 'button[id="down"]', function() {
        var tds = $(this).parents('tr')[0].cells;
        var file = tds[1].innerHTML;
        $.ajax({
            url: "DownloadSubmission",
            method: "POST",
            data: {filepath:file},
            success: function() {
                console.log("success");
            },
            error: function() {
                console.log("Request Error");
            }
        });
    });
});
function logout()
{

    $.ajax({
        url: "Login",
        method: 'post',
        data: {action: 'logout'},
        success: function() {
//            location.replace('index.jsp');
            location.reload();
        }
    });
}
