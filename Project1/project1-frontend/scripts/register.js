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
    
    xhr.open("POST", apiURL,true);

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status >= 200  && xhr.status < 300){           
            var response = this.responseText;
            window.location.href='login.html';
            console.log(response);
            console.log("Successful log in");

        } else if (xhr.readyState === 4){
            let errorSection = document.getElementById('error');
            // Resets the innerHTML before loading new data
            errorSection.innerHTML ='';
            let errorTag = document.createElement('h4');
            errorTag.innerHTML = "Unable to register username or email taken";
            errorTag.style.color = "red";
            errorSection.appendChild(errorTag);
           // alert("Unable to register username or email taken");
        }
    } 

    // xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
     xhr.setRequestHeader("Content-Type", "application/json");
    // "id": 1,
    // "firstname": "first",
    // "lastname": "last",
    // "username": "username2",
    // "password": "password2",
    // "email": "employee2@email.com",
    // "roleid": {
    //     "roleId": 2,
    //     "role": "employee"
    // }
    let userrole = {
        roleId: 1,
        role: role
    }

    if (String(role.role) == "employee"){
        userrole.roleid = 2;
    }

    let user = JSON.stringify({
        firstname: firstname,
        lastname: lastname,
        username : username,
        password: password,
        email: email,
        roleid:userrole
    });

    // fetch(apiURL, {
    //     method: 'POST',
    //     headers: {
    //         'Content-Type': 'application/json',
    //     },
    //     body: JSON.stringify(user),
    //     })
    // .then(response => response.json())
    // .then(data => {
    //     console.log('Success:', user);
    // })
    // .catch((error) => {
    //     console.error('Error:', error);
    // });
     xhr.send(user);
}