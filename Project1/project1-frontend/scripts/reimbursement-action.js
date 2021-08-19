let apiURL = 'http://localhost:8080/project1/reimbursement/pending';
document.getElementById('getData').addEventListener("click", submit);

window.onload = 
function getData() {
    fetch(apiURL)
    .then(response => response.json())
    .then(data => {
        console.log(data);
        let dataSection = document.getElementById('data');
        dataSection.innerHTML ='';
        let dataTag = document.createElement('tr');
        dataTag.innerHTML = JSON.stringify(data);
        dataSection.appendChild(dataTag);
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
            console.log("Reimbursement action requested");

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