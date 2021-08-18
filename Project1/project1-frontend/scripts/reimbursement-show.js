document.getElementById('getData').addEventListener("click", data);

let apiURL = 'http://localhost:8080/project1/reimbursement';

async function getData() {
    let userInput =document.querySelector('input[name="status"]:checked').value;
    fetch(apiURL + "/" + userInput)
    .then(response => response.json())
    .then(data => console.log(data));
  
}

function data(){


    let xhr = new XMLHttpRequest();
    let userInput =document.querySelector('input[name="reimburse"]:checked').value;
    let dataInput = document.getElementById("dataInput").value;
    console.log(userInput);

    if (String(userInput) == "all"){
        xhr.open("GET", apiURL);
    }else{
        xhr.open("GET",apiURL + "/" + userInput);
    }

    let authToken = sessionStorage.getItem("token");
    xhr.setRequestHeader("Authorization",authToken);

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status === 200){
            let response = xhr.responseText;
                // Converting JSON to JS object
                response = JSON.parse(response);
                // Data processing let errorTag = document.createElement('h4');      
                
                if (response == null){
                    alert('User not found');
                }else{
                    populateData(response,dataInput);
                }
        } else if (xhr.readyState === 4){
            alert('Task unavailable needs manager privileges');
        }
    } 
   
    xhr.send();
}


function populateData(response, userInput){
    console.log(response);
    console.log(String(userInput) == "");
    let dataSection = document.getElementById('data');
   
    // Resets the innerHTML before loading new data
    dataSection.innerHTML ='';
    if (response.length > 1){
        for (var i = 0; i < response.length; i++) {
            if (String(response[i].reimbAuthor.username) === String(userInput) || String(userInput) == ""){
                let dataTag = document.createElement('tr');
                dataTag.innerHTML = JSON.stringify(response[i]);
                dataSection.appendChild(dataTag);
             }
        }
        
    }
}
