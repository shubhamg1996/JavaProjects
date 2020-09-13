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
        <script src="assets/js/assignment_admin.js"></script>
        <title>Admin</title>
    </head>
    <body style="padding: 0">

        <%if(request.getSession().getAttribute("name") == null){%>
        <c:redirect url="/index.jsp"/>
        <%;}%>
        <%if(Integer.parseInt((String)request.getSession().getAttribute("role")) != 2){%>
        <c:redirect url="/users.jsp"/>
        <%;}%>
        
        <nav class="navbar navbar-default" style="z-index: 1;">
            <div class="container-fluid" style="background-color: #59B2E0;position:fixed; width: 100%">
                <div class="navbar-header" >
                    <a class="navbar-brand" href="admin.jsp" style="color:white">Assignment Tracking System </a>
                </div>

                <a class="navbar-brand pull-right" href='#' onclick="logout();" style="color:white">Logout</a>
                <a class="navbar-brand pull-right" href="" style="color:white"><c:out value="${sessionScope.name}"/></a>
            </div>
        </nav>
        <%--<c:out value="${sessionScope.name}"/>--%>
        <div class="row" id="option" style="margin-top: 4%;">
            <div class="col-sm-offset-2 col-sm-3" id="create_assignment" style="height: 150px;width: 20%;padding-top: 60px;color:white;background-color: #59B2E0;font-size: 20px;padding-left: 55px;border-radius: 8px;box-shadow: 9px 8px 7px 4px grey;">
                Create Assignment
            </div>
            <div class="col-sm-offset-2 col-sm-3" id="view_assignment" style="height: 150px;width: 20%;padding-top: 60px;color:white;background-color: #59B2E0;font-size: 20px;padding-left: 70px;border-radius: 8px;box-shadow: 9px 8px 7px 4px grey;">
                View Assignment
            </div>  
        </div>
        
        <div class="container" id="view">
            <table id="viewTable" class="table">

                <thead>
                    <tr>
                        <th >S_NO.</th>
                        <th>Assignment</th>
                        <th >Description</th>
                        <th >Created On</th>
                        <th>Deadline</th>  
                        <th>Defaulters</th>  
                        <th></th>    
                    </tr>
                </thead>

                <tbody id="mytableBody">

                </tbody>

            </table>
        </div>
        <div class="container-fluid" id="assignment_create">
            <div class="panel panel-default" style="width: 50%;margin-left: 25%;">
                <div class="panel-heading" style="background-color: #59B2E0;color: white">Assignment Create</div>
                <form action="Upload" enctype="multipart/form-data" method="POST">
                    <div class="panel-body" style="padding:3%;">
                        <div class="form-group">
                            <input type="text" name="title" class="form-control" placeholder="Assignment Title">
                        </div>
                        <div class="form-group">
                            <input type="text" name="description" class="form-control" placeholder="Assignment Description">
                        </div>
                        <div class="form-group">
                            <input type="date" name="deadline" class="form-control">
                        </div>

                        <div class="form-group">
                            <input type="file" id="file" name="file" class="form-control fileupload">
                        </div>
                        <input type="hidden" value="create" name="action">
                    </div>
                    <div class="panel-footer" style="background-color:#59B2E0;height: 10% ">
                        <input type="submit"  value="Create" class="btn" id="create" style="color: white;background-color: #316fda;">
                    </div>
                </form>
            </div>
        </div>
        
       <!-- Modal -->
        <div class="modal fade" id="myModal" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Modal Header</h4>
                    </div>
                    <div class="modal-body">
                        <table id='deafulters' class="table" style='width: 100%'>
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Email ID</th>
                                </tr>
                            </thead>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>
    </body>
</html>
