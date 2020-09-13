<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/register_page.css">
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/jquery.dataTables.min.js"></script>
        <script src="assets/js/dataTables.buttons.min.js"></script>
        <script src="assets/js/datatables.min.js"></script>
        <link href="assets/css/jquery.dataTables.min.css" rel="stylesheet">
        <script src="assets/js/users_Assignment.js"></script>

        <!--<link href="assets/css/jquery.dataTables.min.css" rel="stylesheet">-->
        <!--<script src="assets/js/dataTables.buttons.min.js"></script>-->
        <title><c:out value="${sessionScope.name}"/> Page</title>

        <style>
            .delayed{
                background-color: #ff9393 !important;
            }

            .intime{
                background-color: #c8ffc8 !important;
            }
        </style>

    </head>
    <body style="padding: 0">
        <%if (request.getSession().getAttribute("name") == null) {%>
        <c:redirect url="/index.jsp"/>
        <%;
            }%>
        <nav class="navbar navbar-default" style="z-index: 1;">
            <div class="container-fluid" style="background-color: #59B2E0;position:fixed; width: 100%">
                <div class="navbar-header" >
                    <a class="navbar-brand" href="users.jsp" style="color:white">Assignment Tracking System </a>
                </div>

                <button class="navbar-btn" name='submission' id='submission' data-id='<c:out value="${sessionScope.userId}"/>' style="margin-left: 2%;background-color:#59B2E0; color:white">Submissions </button>

                <a class="navbar-brand pull-right" onclick="logout();" href="index.jsp" style="color:white">Logout</a>
                <a class="navbar-brand pull-right" href="" style="color:white"><c:out value="${sessionScope.name}"/></a>
            </div>

        </nav>
        <div class="container" id="view">
            <table id="viewTable">
                <thead>
                <th >S_NO.</th>
                <th>Assignment</th>
                <th >Description</th>
                <th >Created On</th>
                <th>Deadline</th>    
                <th></th>
                </thead>
                <tbody id="mytableBody">
                </tbody>
            </table>
        </div>
        <div class="modal fade" id="myModal" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Modal Header</h4>
                    </div>
                    <div class="modal-body">
                        <table id='userSubmission' class="table" style='width: 100%'>
                            <thead>
                                <tr>
                                    <th>Assignment ID</th>
                                    <th>File</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody id='mytableBody'>
                                
                            </tbody>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>
    </body>
    <!--<button onclick="getDefaulters(this,' + obj.assignment_id + ');" class="btn btn-default btn-md">Defaulters</button>-->
</html>
