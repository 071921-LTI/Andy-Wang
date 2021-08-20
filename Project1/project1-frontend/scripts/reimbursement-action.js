let apiURL = 'http://localhost:8080/project1/reimbursement/pending';
document.getElementById('getData').addEventListener("click", submit);

window.onload = 
function getData() {
    fetch(apiURL)
    .then(response => response.json())
    .then(data => {
        console.log(data);
        populateData(data);
        });
  
}


function submit(){
    let authToken = sessionStorage.getItem("token");
    let status =document.querySelector('input[name="reimburse"]:checked').value;
    let id = document.getElementById("dataInput").value;

    let xhr = new XMLHttpRequest();
    xhr.open("PUT", apiURL,true);
    xhr.setRequestHeader("Authorization",authToken);

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status >= 200  && xhr.status < 300){           
            var response = this.responseText;
        //    console.log(response);
            alert("Reimbursement action requested");

        } else if (xhr.readyState === 4){
            let errorSection = document.getElementById('error');
            // Resets the innerHTML before loading new data
            errorSection.innerHTML ='';
            let errorTag = document.createElement('h4');
            errorTag.innerHTML = "Unable to make reimbursement action request";
            errorTag.style.color = "red";
            errorSection.appendChild(errorTag);
           // alert("Unable to register username or email taken");
        }
    } 

    xhr.setRequestHeader("Content-Type", "application/json");

    let reimbResolver = authToken.split(":")[0];

    fetch('http://localhost:8080/project1/users/' + reimbResolver)
    .then(response => response.json())
    .then(data => {
    let reimbStatus = {
            statusId: 2,
            status: status
        };

    if (String(reimbStatus.status) == "denied"){
            reimbStatus.statusId = 3;
    }

    let reimburse = JSON.stringify({
        reimbId: id,
        reimbAmount: null,
        reimbSubmit: null,
        reimbResolve: new Date(),
        reimbDescript: null,
        reimbReceipt: null,
        reimbAuthor: null,
        reimbResolver: data,
        reimbStatusId: reimbStatus,
        reimbTypeId: null
        
    });
     console.log(reimburse);
     xhr.send(reimburse);
})
}

function populateData(response){
    console.log(response);
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

    h1.innerHTML = 'ID';
    h2.innerHTML = 'AMOUNT';
    h3.innerHTML = 'SUBMIT DATE';
    h4.innerHTML = 'DESCRIPTION';
    h5.innerHTML = 'RECEIPT';
    h6.innerHTML = 'AUTHOR USERNAME';
    h7.innerHTML = 'TYPE';
    h8.innerHTML = 'STATUS';
    // Resets the innerHTML before loading new data
    thr.appendChild(h1);
    thr.appendChild(h2);
    thr.appendChild(h3);
    thr.appendChild(h4);
    thr.appendChild(h5);
    thr.appendChild(h6);
    thr.appendChild(h7);
    thr.appendChild(h8);
    table.appendChild(thr);

    if (response.length > 1){
        for (var i = 0; i < response.length; i++) {
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

            td1.innerHTML = response[i].reimbId;
            td2.innerHTML = response[i].reimbAmount;
            td3.innerHTML = new Date(response[i].reimbSubmit);
            if (response[i].reimbDescript === null){
                td4.innerHTML = "";
            }else{
                td4.innerHTML = response[i].reimbDescript;
            }
            if (response[i].reimbReceipt === null){
                td5.innerHTML = "";
            }else{
                td5.innerHTML = response[i].reimbReceipt;
            }
            td6.innerHTML = response[i].reimbAuthor.username;
            td7.innerHTML = response[i].reimbStatusId.status;
            td8.innerHTML = response[i].reimbTypeId.type; 
        
            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            tr.appendChild(td4);
            tr.appendChild(td5);
            tr.appendChild(td6);
            tr.appendChild(td7);
            tr.appendChild(td8);

            table.appendChild(tr);

            dataSection.appendChild(table);
        }

        
    }else{
        let dataTag = document.createElement('tr');
        dataTag.innerHTML = JSON.stringify(response);
        dataSection.appendChild(dataTag);
    }
}