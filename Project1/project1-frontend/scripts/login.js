document.getElementById("submitButton").addEventListener("click", login);
// document.getElementById("regButton").addEventListener("click", window.location.href = "register.html");

function login(){

    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    let xhr = new XMLHttpRequest();
    
    xhr.open("POST", "http://localhost:8080/project1/auth");

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status === 200){
            let authToken = xhr.getResponseHeader("Authorization");
            
            sessionStorage.setItem("token", authToken);
            console.log(authToken);
            if (String(authToken.split(":")[1]) == "manager"){
                window.location.href="dashboard.html";
            }else{
                window.location.href="employeescreen.html";
            }

        } else if (xhr.readyState === 4){
            alert('Unable to login invalid username or password');
        }
    } 

    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    let requestBody = `username=${username}&password=${password}`;
    xhr.send(requestBody);
}