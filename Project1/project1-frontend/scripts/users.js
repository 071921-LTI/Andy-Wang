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

    if (userInput > 1 && String(userInput) !== "all"){
        xhr.open("GET",apiURL + "/" + userInput);
    }else{
        xhr.open("GET", apiURL);
    }

    let authToken = sessionStorage.getItem("token");
    xhr.setRequestHeader("Authorization",authToken);

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status === 200){
            let response = xhr.responseText;
                // Converting JSON to JS object
                response = JSON.parse(response);
                // Data processing behavior
                if (response == null){
                    alert('User not found');
                }else{
                    populateData(response);
                }
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

    var table = document.createElement('table');
    table.style.width = "100%";
    table.style.border = "1px solid black";
    table.style.borderCollapse = "collapse";
    dataSection.innerHTML = '';
    let thr = document.createElement('tr');
    let h1 = document.createElement('th');
    let h2 = document.createElement('th');
    let h3 = document.createElement('th');
    let h4 = document.createElement('th');
    let h5 = document.createElement('th');
    let h6 = document.createElement('th');
    let h7 = document.createElement('th');
    
    h1.innerHTML = "Id"; 
    h2.innerHTML = "First name"; 
    h3.innerHTML ="Last name"; 
    h4.innerHTML = "Username";
    h5.innerHTML = "Email" ;
    h6.innerHTML ="Role";
    
    thr.appendChild(h1);
    thr.appendChild(h2);
    thr.appendChild(h3);
    thr.appendChild(h4);
    thr.appendChild(h5);
    thr.appendChild(h6);
    table.appendChild(thr);

    if (response.length > 1){
        for (var i = 0; i < response.length; i++) {
            let tr = document.createElement('tr');
            let td1 = document.createElement('td');
            let td2 = document.createElement('td');
            let td3 = document.createElement('td');
            let td4 = document.createElement('td');
            let td5 = document.createElement('td');
            let td6 = document.createElement('td');
            td1.innerHTML = response[i].id;
            td2.innerHTML = response[i].firstname;
            td3.innerHTML = response[i].lastname;
            td4.innerHTML = response[i].username;
            td5.innerHTML =  response[i].email; 
            td6.innerHTML = response[i].roleid.role;
            
            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            tr.appendChild(td4);
            tr.appendChild(td5);
            tr.appendChild(td6);

            table.appendChild(tr);
        }

        dataSection.appendChild(table);
    }else{
        let tr = document.createElement('tr');
        let td1 = document.createElement('td');
        let td2 = document.createElement('td');
        let td3 = document.createElement('td');
        let td4 = document.createElement('td');
        let td5 = document.createElement('td');
        let td6 = document.createElement('td');
        td1.innerHTML = response.id;
        td2.innerHTML = response.firstname;
        td3.innerHTML = response.lastname;
        td4.innerHTML = response.username;
        td5.innerHTML = response.email; 
        td6.innerHTML = response.roleid.role;
        
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);

        table.appendChild(tr);

        dataSection.appendChild(table);
    }
    
}