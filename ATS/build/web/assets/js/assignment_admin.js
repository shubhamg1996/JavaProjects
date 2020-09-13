$(document).ready(function() {

    $("#assignment_create").hide();
    $("#view").hide();

    var form = $("#assignment_create").find("form");
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth() + 1; //January is 0!
    var yyyy = today.getFullYear();
    if (dd < 10) {
        dd = '0' + dd;
    }
    if (mm < 10) {
        mm = '0' + mm;
    }

    today = yyyy + '-' + mm + '-' + dd;

    $(form).find("input[type='date']").attr('min',today);

    $("#create_assignment").click(function() {
        $("#assignment_create").show();
        $("#option").hide();
    });
    $("#view_assignment").click(function() {
        $("#assignment_create").hide();
        $("#option").hide();
        $("#view").show();
        $.ajax({
            url: "ViewAssignment",
            method: "POST",
            success: function(data) {
                var html = "";
                for (var i = 0; i < data.length; i++) {
                    var obj = data[i];
                    html += '<tr>'
                            + '<td >' + (i + 1) + '</td>'
                            + '<td><form action="DownloadZip" method="POST"><input type="hidden" name="assid" value="' + obj.assignment_id + '">\n\
<button class="btn btn-link">' + obj.title + '</button></form></td>'
                            + '<td >' + obj.description + '</td>'
                            + '<td >' + obj.created_on + '</td>'
                            + '<td >' + obj.deadline + '</td>'
                            + '<td ><button onclick="getDefaulters(this,' + obj.assignment_id + ');" class="btn btn-default btn-md">Defaulters</button></td>'
                            + '<td ><button  name="edit" class="btn btn-default btn-md" data-id="' + obj.assignment_id + '">Edit</button></td>'
                            + '</tr>';

                }
                $('#mytableBody').html(html);
                $('#viewTable').DataTable();
            },
            error: function(data) {
                console.log(data);
            }
        });
    });
    $("#viewTable").on('click', 'button[name="edit"]', function() {
        var tds = $(this).parent('td').siblings('td');
        var assignment = tds[1].innerText;
        var desc = tds[2].innerHTML;
        var deadline = tds[3].innerHTML;
//        console.log(assignment, desc, deadline);
//        alert("Assignment ID " + $(this).data('id'));
        var form = $("#assignment_create").find("form");
        $(form).find("input[name='title']").val(assignment);
//        console.log(tds[1].innerText);
        $(form).find("input[name='description']").val(desc);
        $(form).find("input[name='deadline']").val(deadline);
        $(".panel-heading").html("Update Assignment");
        $(form).find("input[type='submit']").val("Update");
        $(form).find("input[type='hidden']").val($(this).data('id'));
        $("#option").hide();
        $("#view").hide();
        $("#assignment_create").show();

    });
});

function createTask(button)
{
    var form = $(button).parents('form');
    var file = $("#file").val();
}

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

function getDefaulters(btn, taskid)
{
    var tds = $(btn).parent('td').siblings('td');
    $.ajax({
        url: "ViewAssignment",
        method: "POST",
        data: {taskid: taskid},
        success: function(data) {
            $(".modal-header").html(tds[1].innerText);
            $("#deafulters").dataTable({
                data: data,
                columns: [{data: 'name'}, {data: 'email'}],
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
}