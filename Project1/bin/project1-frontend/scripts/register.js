document.getElementById("submitButton").addEventListener("click", register);
let apiURL = 'http://localhost:8080/proect1/users/';

async function getData() {

    let userInput = document.getElementById('dataInput').value;

    let response = await fetch(apiURL + userInput);

    if(response.status >= 200 && response.status < 300){
        let data = await response.json();
        populateData(data);
    } else{
        console.log('Unable to retrieve data.')
    }
  
}

function register(){

    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let role = document.getElementById("role").value;

    let xhr = new XMLHttpRequest();
    
    xhr.open("POST", "http://localhost:8080/auth-demo/users");

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status === 200){
            console.log('successful')
            window.location.href="login.html";

        } else if (xhr.readyState === 4){
            console.log('Something went wrong...');
        }
    } 

     xhr.setRequestHeader("Access-Control-Allow-Origin", "*");
    // xhr.addHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
    // xhr.addHeader("Access-Control-Allow-Headers", "Content-Type, Accept, Authorization");
    // xhr.addHeader("Access-Control-Expose-Headers", "Content-Type, Accept, Authorization");
    
  //  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    let requestBody = `username=${username}&password=${password}&role=${role}`;
    xhr.send(requestBody);
}

