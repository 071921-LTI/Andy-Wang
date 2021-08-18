document.getElementById('getData').addEventListener("click", data);

let apiURL = 'http://localhost:8080/project1/users';

async function getData() {
    let userInput = document.getElementById('dataInput').value;
    fetch(apiURL + "/" + userInput)
    .then(response => response.json())
    .then(data => console.log(data));
  
}

function data(){


    let xhr = new XMLHttpRequest();
    let userInput = document.getElementById('dataInput').value;
    console.log(userInput);

    if (userInput > 0){
        xhr.open("GET",apiURL + "/" + userInput);
    }else{
        xhr.open("GET", "http://localhost:8080/project1/users");
    }

    let authToken = sessionStorage.getItem("token");
    xhr.setRequestHeader("Authorization",authToken);

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status === 200){
            let response = xhr.responseText;
                // Converting JSON to JS object
                response = JSON.parse(response);
                // Data processing behavior
                populateData(response);

        } else if (xhr.readyState === 4){
            alert('Task unavailable needs manager privileges');
        }
    } 
   
    xhr.send();
}


function populateData(response) {
    console.log(response);
    let dataSection = document.getElementById('data');
   
    // Resets the innerHTML before loading new data
    dataSection.innerHTML ='';

      let titleTag = document.createElement('th');
    
    titleTag.innerHTML = 
    "Id" + 
    "       Firstname " + 
    "       Lastname " +  
    "       Username " +  
    "       Password " +  
    "       Email " +  
    "       Role";
    dataSection.appendChild(titleTag);

    if (response.length > 1){
        for (var i = 0; i < response.length; i++) {
            let dataTag = document.createElement('tr');
            dataTag.innerHTML = 
                "id " + response[i].id + 
                " firstname " + response[i].firstname + 
                " lastname " + response[i].lastname + 
                " username " + response[i].username + 
                " password " + response[i].password + 
                " email " + response[i].email + 
                " role" + response[i].roleid.role;
            dataSection.appendChild(dataTag);
        }
    }else{
        let dataTag = document.createElement('tr');
        dataTag.innerHTML = 
            response.id + "       " +
            response.firstname + "       " +
            response.lastname + "       " +
            response.username + "       " +
            response.password + "       " +
            response.email + "       " +
            response.roleid.role;
        dataSection.appendChild(dataTag);
    }
    
}