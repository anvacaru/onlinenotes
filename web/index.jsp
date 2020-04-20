<%@page import="java.lang.String"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>Online Notes</title>

    <!-- Bootstrap -->
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <script src="resources/js/mainJS.js"></script>
    <link href="resources/css/mainCSS.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="resources/js/bootstrap.min.js"></script>
  
    <script>
        $(function () {
            $('[data-toggle="tooltip"]').tooltip()
        })
        $(function () {
            $('[data-toggle="popover"]').popover()
        })
    </script>
  </head>
  <body>

      <form id="myForm" action="/insert" method ="POST">
       <nav class="navbar navbar-default">
        <div class="container-fluid">
          <!-- Brand and toggle get grouped for better mobile display -->
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Online Notes</a>
          </div>

          <!-- Collect the nav links, forms, and other content for toggling -->
          <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav" id="navbar-ul">
                <%
                String isEditable = (String) session.getAttribute("isEditable");
                if(isEditable != null){
                    %>
                    <script>
                        isEditable =<%out.print("'"+isEditable+"'");%>
                    </script>
                    <%
                }
                
                String isPrivate = (String) session.getAttribute("isPrivate");
                if(isPrivate != null){
                    %>
                    <script>
                        isPrivate =<%out.print("'"+isPrivate+"'");%>
                    </script>
                    <%
                }
                String isLogged = (String) session.getAttribute("isLogged");
                if(isPrivate != null){
                    %>
                    <script>
                        isLogged =<%out.print("'"+isPrivate+"'");%>
                    </script>
                    <%
                }
                 %>
              </ul>
            
          </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
      </nav>
     

        <!-- Modal -->
        <div class="modal fade" id="optionsModal" tabindex="-1" role="dialog" aria-labelledby="optionsModalLabel">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="optionsModalLabel">Options</h4>
              </div>
              <div class="modal-body">
                <div class="form-group">
                    <label for="apwd">
                        <button type="button" class="btn btn-md btn-default" data-container="body" data-toggle="popover" data-placement="right" data-content="Insert an access password to make this note private! This has to be inserted everytime you edit the note!">
                        <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
                        </button>
                        Access Password:
                    </label>
                    <input type="password" class="form-control" id="apwd" name="apwd">
                  </div>
                  <div class="form-group">
                    <label for="epwd">
                        <button type="button" class="btn btn-md btn-default" data-container="body" data-toggle="popover" data-placement="right" data-content="Insert an edit password to make this note editable! This has to be inserted everytime you edit the note!">
                        <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
                        </button>
                        Edit Password:
                    </label>
                    <input type="password" class="form-control" id="epwd" name = "epwd">
                  </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-success" data-dismiss="modal">Save changes</button>
              </div>
            </div>
          </div>
        </div>
      
        <div class="modal fade" id="enableEditModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editModalLabel">Enable Note Edit</h4>
              </div>
              <div class="modal-body">
                <div class="form-group">
                    <label for="epwd">Edit Password:</label>
                    <input type="password" class="form-control" id="editPassword" name="epwd" required>
                  </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-success" data-dismiss="modal" id="editModalButton">Enable Edit</button>
              </div>
            </div>
          </div>
        </div>
        
      <div class="form-group">
          
          <textarea name = "textarea_content" class="form-control" rows="20" id="textarea_content" ><%
              String content = (String)session.getAttribute("content");
              if(content != null){
                  out.print(content);             
              }
              %></textarea>
          
        </div>
      </form>
      <div id ="alerts">
      </div>
      <div id="loginFormContainer">
      </div>
       
  </body>
</html>
