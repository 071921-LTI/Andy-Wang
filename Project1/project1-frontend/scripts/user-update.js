document.getElementById("submitButton").addEventListener("click", register);
let apiURL = 'http://localhost:8080/project1/users';


function register(){

    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let role =document.querySelector('input[name="role"]:checked').value;
    let email = document.getElementById("email").value;
    let firstname = document.getElementById("firstname").value;
    let lastname = document.getElementById("lastname").value;

    console.log(username + password + role + email + firstname + lastname);

    let xhr = new XMLHttpRequest();
    
    xhr.open("PUT", apiURL,true);

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status >= 200  && xhr.status < 300){           
            var response = this.responseText;
            console.log("Successful update");

            let errorSection = document.getElementById('error');
            errorSection.innerHTML ='';
            let errorTag = document.createElement('h4');
            errorTag.innerHTML = response;
            errorSection.appendChild(errorTag);

        } else if (xhr.readyState === 4){
            let errorSection = document.getElementById('error');
            // Resets the innerHTML before loading new data
            errorSection.innerHTML ='';
            let errorTag = document.createElement('h4');
            errorTag.innerHTML = "Unable to update username or email taken";
            errorTag.style.color = "red";
            errorSection.appendChild(errorTag);
           // alert("Unable to register username or email taken");
        }
    } 

   xhr.setRequestHeader("Content-Type", "application/json");
    
    let userrole = {
        roleId: 1,
        role: role
    }

    let userid = sessionStorage.getItem("token").split(":")[0];

    if (String(role.role) == "employee"){
        userrole.roleid = 2;
    }

    let user = JSON.stringify({
        id:userid,
        firstname: firstname,
        lastname: lastname,
        username : username,
        password: password,
        email: email,
        roleid:userrole
    });

     xhr.send(user);
}