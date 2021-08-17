document.getElementById('getData').addEventListener("click", getData);

let apiURL = 'http://localhost:8080/project1/users/';

async function getData() {

    let userInput = document.getElementById('dataInput').value;
    console.log(userInput);
    let response = await fetch(apiURL + userInput);

    if(response.status >= 200 && response.status < 300){
        let data = await response.json();
        populateData(data);
    } else{
        console.log('Unable to retrieve data.')
    }
  
}


function populateData(response) {
    console.log(response);
    let dataSection = document.getElementById('data');
   
    // Resets the innerHTML before loading new data
    dataSection.innerHTML ='';
    let nameTag = document.createElement('h3');
    nameTag.innerHTML = response.username;
    dataSection.appendChild(nameTag);
    
}