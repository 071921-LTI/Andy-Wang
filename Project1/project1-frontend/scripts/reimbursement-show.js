document.getElementById('getData').addEventListener("click", data);

let apiURL = 'http://localhost:8080/project1/reimbursement';

async function getData() {
    let userInput =document.querySelector('input[name="status"]:checked').value;
    fetch(apiURL + "/" + userInput)
    .then(response => response.json())
    .then(data => console.log(data[0].reimbId));
  
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
    var table = document.createElement('table');
    table.style.width = "100%";
    table.style.border = "1px solid black";
    table.style.borderCollapse = "collapse";
    dataSection.innerHTML = '';
    let thr = document.createElement('tr');
    thr.style.border = "1px solid black";
    let h1 = document.createElement('th');
    let h2 = document.createElement('th');
    let h3 = document.createElement('th');
    let h4 = document.createElement('th');
    let h5 = document.createElement('th');
    let h6 = document.createElement('th');
    let h7 = document.createElement('th');
    let h8 = document.createElement('th');
    let h9 = document.createElement('th');
    let h10 = document.createElement('th');
    h1.innerHTML = 'ID';
    h2.innerHTML = 'AMOUNT';
    h3.innerHTML = 'SUBMIT DATE';
    h4.innerHTML = 'RESOLVE DATE';
    h5.innerHTML = 'DESCRIPTION';
    h6.innerHTML = 'RECEIPT';
    h7.innerHTML = 'AUTHOR USERNAME';
    h8.innerHTML = 'RESOLVER USERNAME';
    h9.innerHTML = 'TYPE';
    h10.innerHTML = 'STATUS';
    // Resets the innerHTML before loading new data
    thr.appendChild(h1);
    thr.appendChild(h2);
    thr.appendChild(h3);
    thr.appendChild(h4);
    thr.appendChild(h5);
    thr.appendChild(h6);
    thr.appendChild(h7);
    thr.appendChild(h8);
    thr.appendChild(h9);
    thr.appendChild(h10);
    table.appendChild(thr);

    if (response.length > 1){
        for (var i = 0; i < response.length; i++) {
            if (String(response[i].reimbAuthor.username) === String(userInput) || String(userInput) == ""){
                let tr = document.createElement('tr');
                tr.style.border = "1px solid black";
                let td1 = document.createElement('td');
                let td2 = document.createElement('td');
                let td3 = document.createElement('td');
                let td4 = document.createElement('td');
                let td5 = document.createElement('td');
                let td6 = document.createElement('td');
                let td7 = document.createElement('td');
                let td8 = document.createElement('td');
                let td9 = document.createElement('td');
                let td10 = document.createElement('td');

                td1.innerHTML = response[i].reimbId;
                td2.innerHTML = response[i].reimbAmount;
                td3.innerHTML = new Date(response[i].reimbSubmit);
                td4.innerHTML = new Date(response[i].reimbResolve);
                if (response[i].reimbDescript === null){
                    td5.innerHTML = "";
                }else{
                    td5.innerHTML = response[i].reimbDescript;
                }
                if (response[i].reimbReceipt === null){
                    td6.innerHTML = "";
                }else{
                    td6.innerHTML = response[i].reimbReceipt;
                }
                td7.innerHTML = response[i]. reimbAuthor.username;
                if (response[i].reimbResolver === null){
                    td8.innerHTML = "";
                }else{
                    td8.innerHTML = response[i].reimbResolver.username;
                }
                td9.innerHTML = response[i].reimbStatusId.status;
                td10.innerHTML = response[i].reimbTypeId.type; 
           
                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(td3);
                tr.appendChild(td4);
                tr.appendChild(td5);
                tr.appendChild(td6);
                tr.appendChild(td7);
                tr.appendChild(td8);
                tr.appendChild(td9);
                tr.appendChild(td10);

                table.appendChild(tr);
             }
             dataSection.appendChild(table);
        }

        
    }else{
        let tr = document.createElement('tr');
        let td1 = document.createElement('td');
        let td2 = document.createElement('td');
        let td3 = document.createElement('td');
        let td4 = document.createElement('td');
        let td5 = document.createElement('td');
        let td6 = document.createElement('td');
        let td7 = document.createElement('td');
        let td8 = document.createElement('td');
        let td9 = document.createElement('td');
        let td10 = document.createElement('td');

        td1.innerHTML = response.reimbId;
        td2.innerHTML = response.reimbAmount;
        td3.innerHTML = new Date(response.reimbSubmit);
        td4.innerHTML = new Date(response.reimbResolve);
        if (response.reimbDescript === null){
            td5.innerHTML = "";
        }else{
            td5.innerHTML = response.reimbDescript;
        }
        if (response.reimbReceipt === null){
            td6.innerHTML = "";
        }else{
            td6.innerHTML = response.reimbReceipt;
        }
        td7.innerHTML = response. reimbAuthor.username;
        if (response.reimbResolver === null){
            td8.innerHTML = "";
        }else{
            td8.innerHTML = response.reimbResolver.username;
        }
        td9.innerHTML = response[i].reimbStatusId.status;
        td10.innerHTML = response[i].reimbTypeId.type; 
   
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
        tr.appendChild(td7);
        tr.appendChild(td8);
        tr.appendChild(td9);
        tr.appendChild(td10);

        table.appendChild(tr);
     }
     dataSection.appendChild(table);
}

