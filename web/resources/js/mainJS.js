isPrivate=""; // var which tells me if the note is private or public
isEditable="";//var which tells me if the note is editable or not
isLogged=""; // var which tells me if the user has verified his Access Password

address="http://onlinenotes.hopto.org";

window.onload = function(){
	editEnabled=""; //edit enabled tells me if the user has verified the Edit Password
	url = getQueryVariable("v"); // url is a variable which contains the note's id (8 chars)
	alerts = document.getElementById("alerts");
	loginContainer = document.getElementById("loginFormContainer");
	if(isPrivate == "false")
		isLogged="true"; // if the note is public, isLogged will be automatically true
	else 
		if(isPrivate=="true")
			isLogged="false"; // if the note is private, isLogged will be automatically false
		
	if(url == false){//Edit Mode is used when the page is accessed for the first time
		mode = "edit"
	}
	else mode = "view"; //this means that when the site is accessed there already is a created note which has to be displayed. We don't know yet if it is public/private or editable;
	changeMode();
	
}

function getQueryVariable(variable) //returns the value of a parameter inside a query, if the parameter is not found, returns false
{
       var query = window.location.search.substring(1);
       var vars = query.split("&");
       for (var i=0;i<vars.length;i++) {
               var pair = vars[i].split("=");
               if(pair[0] == variable){return pair[1];}
       }
       return(false);
}


function insertNote() { //post request to insert a note into the database
    $.ajax({
        url:'/insert',
        type:'post',
        data:$('#myForm').serialize(),
        success:function(responseJson){
		    url = responseJson.url;
			isPrivate = responseJson.isPrivate;
			isEditable = responseJson.isEditable;
			isLogged = "true";
			mode = responseJson.mode;
			changeMode();
			}
		
    });
	
}

function deleteNote() { //post request to insert a note into the database
    var data=$('#myForm').serialize().concat('&url=',url);
    $.ajax({
        url:'/delete',
        type:'post',
        data:data,
        success:function(responseJson){
                        mode="edit"
                        document.location.href=("/");
			}
		
    });
	
}

function updateNote(){ //post request to update an already created note
	var data=$('#myForm').serialize().concat('&url=',url);
	$.ajax({
        url:'/update',
        type:'post',
        data:data,
        success:function(responseJson){
		    alerts.innerHTML="";
			isPrivate = responseJson.isPrivate;
			isEditable = responseJson.isEditable;
			isLogged = "true";
			mode = responseJson.mode;
			changeMode();
			}
		
    });
	
}

function changeMode(){ //basically alters the page depending if the mode is set to "edit" or "view"
	navbar = document.getElementById("navbar-ul");
	content = document.getElementById("textarea_content");
	if(mode =="edit"){
		navbar.innerHTML = '<li><a role="button" data-toggle="modal" data-target="#optionsModal">Options <span class="sr-only">(current)</span></a></li>';
		navbar.innerHTML += '<li><input value="Share" type="submit" class="btn default-btn btn-success navbar-btn" id="form_submit" ></li> ';
                if(url !== false) {
                    navbar.innerHTML += '<li><input value="Delete" type="submit" class="btn default-btn navbar-btn" id="delete_btn" ></li>';
                }
		submit = document.getElementById("form_submit"); // this is the share/update button
		submit.onclick = function(e){
			e.preventDefault();
			if(content.value == ""){
				alert("The note cannot be empty");
			}
			else{
				if(url == false) 
					insertNote();
				else 
					updateNote();
			}
		}
                delete_btn = document.getElementById("delete_btn");
                delete_btn.onclick = function(e) {
                    e.preventDefault();
                    deleteNote();
                }
		if(url !== false){
			document.getElementById("form_submit").value = "Update";
		}
		if(content !== null){//reinitialize the fields of the page after a note is accessed and the page is refreshed
			content.disabled = false;
			if(editEnabled !== "true"){
			content.innerHTML =""; // clear textarea at refresh after a note is accessed
			}
			else{
				editEnabled="false";
			}
		}
	}
	else{ // VIEW MODE
		navbar.innerHTML =  '<li id=><button class="btn btn-default  navbar-btn" data-toggle="modal" type="button" data-target="#enableEditModal" id="editButton">Edit <span class="sr-only">(current)</span></button></li>';
		if(content !== null){
			content.disabled = true;
			content.style.opacity = 0.7;
		}
		if(isEditable === "false" || isLogged === "false"){
			document.getElementById("editButton").disabled = true; // if the note can't be edited, or the user hasn't verified the Access Password the edit button is disabled
		}
		else{
			checkEPwdButton = document.getElementById("editModalButton");
			checkEPwdButton.onclick = function(e){
				e.preventDefault();
				checkEPwd();
			}
		}
		
		var a1;
		if(isLogged === "true"){// if the note is public, or if the user has verified his Access Password, 3 alerts are displayed
			loginContainer.innerHTML="";
			a1 = document.createElement("div");
			a1.className="alert alert-success alert-dismissible";
			a1.role = "alert";
			a1.innerHTML = '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><strong>Note Link: </strong> <a id="anchorLink">'
                                     + address + '/view?v=' + url +'</a>';
			alerts.appendChild(a1);
			document.getElementById("anchorLink").href = document.getElementById("anchorLink").text;

			if(isPrivate !== null){
				if(isPrivate === "true"){
					a1 = document.createElement("div");
					a1.className="alert alert-info alert-dismissible";
					a1.role = "alert";
					a1.innerHTML = '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><strong>This note is private!</strong> Only those who have the access password can see it.';
					alerts.appendChild(a1);
				}
				else
					if(isPrivate === "false"){
						a1 = document.createElement("div");
						a1.className="alert alert-info alert-dismissible";
						a1.role = "alert";
						a1.innerHTML = '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><strong>This note is public!</strong> Everyone can see it.';
						alerts.appendChild(a1);
					}
			}
			
			if(isEditable !== null){
				if(isEditable === "true"){
					a1 = document.createElement("div");
					a1.className="alert alert-info alert-dismissible";
					a1.role = "alert";
					a1.innerHTML = '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><strong>This note can be edited!</strong> Only those who have the edit password can modify it.';
					alerts.appendChild(a1);
				}
				else
					if(isEditable === "false"){
						a1 = document.createElement("div");
						a1.className="alert alert-info alert-dismissible";
						a1.role = "alert";
						a1.innerHTML = '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><strong>This note cannot be edited!</strong> No one can modify it.';
						alerts.appendChild(a1);
					}
			}
		}
		else // If the note is private, and the user didn't verify his access Password
			{
			content.innerHTML="";
			a1 = document.createElement("div");
			a1.className="alert alert-warning alert-dismissible";
			a1.role = "alert";
			a1.innerHTML = '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><strong>Authentification required! </strong> This note is private.';
			loginContainer.appendChild(a1);
			a1 = document.createElement("div");
			a1.className=" container";
			a1.innerHTML ='<form class="form-signin" id="signInForm"><input type="password" id="accessPassword" class="form-control" placeholder="Password" required><button class="btn btn-lg btn-primary btn-block" type="submit" id="loginButton">Show Note</button></form>';
			loginContainer.append(a1);
			loginButton = document.getElementById("loginButton");
			loginButton.onclick = function(e){
				e.preventDefault();
				login();
			}
			}
	}
}

function login(){// post method to verify the Access Password
	var data ="apwd=";
	data = data.concat(document.getElementById("accessPassword").value.concat("&url=",url));
	$.ajax({
        url:'/AccessPassword',
        type:'post',
        data:data,
        success:function(responseJson){
			isLogged = "true";
			content.innerHTML = responseJson.content;
			isEditable = responseJson.isEditable;
			mode = "view";
			changeMode();
		},
		error:function(responseJson){
			isLogged="false";
			mode = "view";
			errorAlert("accessError");
		}
		
    });
}

function checkEPwd(){// post method to check the Edit Password
	var data ="epwd=";
	data = data.concat(document.getElementById("editPassword").value.concat("&url=",url));
	$.ajax({
        url:'/EditPassword',
        type:'post',
        data:data,
        success:function(responseJson){
			isLogged = "true";
			content.innerHTML = responseJson.content;
			editEnabled="true";
			mode = "edit";
			changeMode();
		},
		error:function(responseJson){
			isLogged="true";
			mode = "view";
			errorAlert("editError");
		}
		
    });
}
function errorAlert(type){// Displays an error based on type
	var a1;
	if(type === "accessError"){
		loginForm = document.getElementById("signInForm");
		a1 = document.createElement("div");
		a1.className="alert alert-danger alert-dismissible";
		a1.role = "alert";
		a1.innerHTML = '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><strong>Error! </strong> Incorrect access password.';
		loginForm.appendChild(a1);
	}
	else 
		if (type === "editError"){
			a1 = document.createElement("div");
			a1.className="alert alert-danger alert-dismissible";
			a1.role = "alert";
			a1.innerHTML = '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><strong>Error! </strong> Incorrect access password.';
			alerts.appendChild(a1);
		}
}

