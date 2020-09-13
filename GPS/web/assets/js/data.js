$(document).ready(function() {
    $("#editData").hide();
    $('#viewTable').hide();
    $("#submit").click(function() {
        var description = $("#description").val();
        var category = $("#category").val();
        var requestor = $("#requestor").val();
        var vendor = $("#vendor").val();
        var amount = $("#amount").val();
        var gps_number = $("#gps_number").val();
        var request_date = $("#request_date").val();
        var asset_status = $("#asset_status").val();
        var po_number = $("#po_num").val();
        var asset_id = $("#asset_id").val();
//        var remarks = $("#remark").val();
        if (description === "" || category === "" || requestor === "" || amount === "" || request_date === "" || asset_status === "") {
            alert("Fill form completely");
        } else
        {
            $.ajax({
                url: "Servlet",
                method: "POST",
                data: {
                    description: description,
                    category: category,
                    requestor: requestor,
                    vendor: vendor,
                    amount: amount,
                    gps_number: gps_number,
                    request_date: request_date,
                    asset_status: asset_status,
                    po_number: po_number,
                    asset_id: asset_id,
//                    remarks: remarks,
                    action: "Send"
                },
                success: function(data) {
                    alert("Data Inserted Successfully");
                    window.location.href = "index.html";
                },
                error: function(err) {
                    alert("Request Error");
                }
            });
        }
    });
    $("#view").click(function() {
        showDB();
    });

    $("#viewTable").on('click', 'button[name="edit"]', function() {
        
        var tds = $(this).parents('tr')[0].cells;
        console.log(tds);
        $('#update').data('id', $(this).data('id'));
        $("#description_edit").val(tds[1].innerHTML);
        $("#category_edit").val(tds[2].innerHTML);
        $("#requestor_edit").val(tds[3].innerHTML);
        $("#vendor_edit").val(tds[4].innerHTML);
        $("#amount_edit").val(tds[5].innerHTML);
        $("#gps_number_edit").val(tds[6].innerHTML);
        $("#request_date_edit").val(tds[7].innerHTML);
        $("#asset_id_edit").val(tds[8].innerHTML);
        $("#asset_status_edit").val(tds[9].innerHTML);
        $("#po_num_edit").val(tds[10].innerHTML);
//        $("#remark_edit").val(tds[11].innerHTML);
    });

    $('#update').click(function() {
        var id = $(this).data('id');
        var description = $("#description_edit").val();
        var category = $("#category_edit").val();
        var requestor = $("#requestor_edit").val();
        var vendor = $("#vendor_edit").val();
        var amount = $("#amount_edit").val();
        var gps_number = $("#gps_number_edit").val();
        var request_date = $("#request_date_edit").val();
        var asset_status = $("#asset_status_edit").val();
        var po_number = $("#po_num_edit").val();
        var asset_id = $("#asset_id_edit").val();
//        var remarks = $("#remark_edit").val();
        if (description === "" || category === "" || requestor === "" || request_date === "" || asset_status === "") {
            alert("Fill form completely");
        } else
        {
            $.ajax({
                url: "Servlet",
                method: "POST",
                data: {
                    id: id,
                    description: description,
                    category: category,
                    requestor: requestor,
                    vendor: vendor,
                    amount: amount,
                    gps_number: gps_number,
                    request_date: request_date,
                    asset_status: asset_status,
                    po_number: po_number,
                    asset_id: asset_id,
//                    remarks: remarks,
                    action: "Update"
                },
                success: function(data) {
                    alert(data);
                    $('#myModal').modal('toggle');
                    showDB();
                },
                error: function(err) {
                    alert("Request Error");
                }
            });
        }
    });
});

function showDB()
{
    $.ajax({
        url: "Servlet",
        method: "POST",
        data: {
            action: "Recieve"
        },
        success: function(data) {
            if (data.status) {
                $("#panel").hide();
                $("#editData").hide();
                console.log(data);
                var datas = data.data;
                var html = "";

                for (var i = 0; i < datas.length; i++) {
                    var obj = datas[i];
                    html += '<tr>'
                            + '<td">' + (i + 1) + '</td>'
                            + '<td">' + obj.description + '</td>'
                            + '<td">' + obj.category + '</td>'
                            + '<td">' + obj.requestor + '</td>'
                            + '<td">' + obj.vendor + '</td>'
                            + '<td">' + obj.amount + '</td>'
                            + '<td">' + obj.gps_number + '</td>'
                            + '<td">' + obj.request_date + '</td>'
                            + '<td">' + obj.asset_id + '</td>'
                            + '<td">' + obj.asset_status + '</td>'
                            + '<td">' + obj.po_number + '</td>'
//                            + '<td">' + obj.remarks + '</td>'
                            + '<td"><button  name="edit" class="btn btn-default btn-md" data-id="' + obj.sno + '" data-toggle="modal" data-target="#myModal">Edit</button></td>'
                            + '</tr>';

                }
                $('#mytableBody').html(html);
                $('#assetsTable').dataTable({
                    destroy: true,
                    dom: 'Blfrtip',
                    buttons: [
                        'csv', 'excel', 'print'
                    ]
                });
                $('#viewTable').show();
            }
        },
        error: function() {
            alert("Request Error");
        }
    });
}