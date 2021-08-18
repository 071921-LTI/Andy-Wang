document.getElementById("submitButton").addEventListener("click", submit);
let apiURL = 'http://localhost:8080/project1/reimbursement';

function submit(){

    let amount = document.getElementById("amount").value;
    let descript = document.getElementById("description").value;
    let receipt = document.getElementById("receipt").value;
    let type =document.querySelector('input[name="type"]:checked').value;
    let date = document.getElementById("date").value;
    let authToken = sessionStorage.getItem("token");
    //console.log(amount + descript + type + date + receipt + authToken);
    

    let xhr = new XMLHttpRequest();
    xhr.open("POST", apiURL,true);
    xhr.setRequestHeader("Authorization",authToken);

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status >= 200  && xhr.status < 300){           
            var response = this.responseText;
        //    console.log(response);
            console.log("Reimbursement requested");

        } else if (xhr.readyState === 4){
            let errorSection = document.getElementById('error');
            // Resets the innerHTML before loading new data
            errorSection.innerHTML ='';
            let errorTag = document.createElement('h4');
            errorTag.innerHTML = "Unable to make reimbursement request";
            errorTag.style.color = "red";
            errorSection.appendChild(errorTag);
           // alert("Unable to register username or email taken");
        }
    } 

    xhr.setRequestHeader("Content-Type", "application/json");

    //  let body = {
    //     "reimbId": 1,
    //     "reimbAmount": 1200.0,
    //     "reimbSubmit": new Date(),
    //     "reimbResolve": null,
    //     "reimbDescript": null,
    //     "reimbReceipt": null,
    //     "reimbAuthor": {
    //         "id": 2,
    //         "firstname": "first",
    //         "lastname": "last",
    //         "username": "manager",
    //         "password": "password1",
    //         "email": "manager@email.com",
    //         "roleid": {
    //             "roleId": 1,
    //             "role": "manager"
    //         }
    //     },
    //     "reimbResolver": null,
    //     "reimbStatusId": {
    //         "statusId": 1,
    //         "status": "pending"
    //     },
    //     "reimbTypeId": {
    //         "typeId": 1,
    //         "type": "lodging"
    //     }

    let reimbAuthor = authToken.split(":")[0];
   // console.log(reimbAuthor)
    fetch('http://localhost:8080/project1/users/' + reimbAuthor)
    .then(response => response.json())
    .then(data => {
    let reimbType = {
            typeId: 1,
            type: type
        };

    switch(String(type)){
        case "travel":
            reimbType.typeId = 2;
            break;
        case "food":
            reimbType.typeId = 3;
            break;
        case "other":
            reimbType.typeId = 4;
            break;
    }    

    let reimburse = JSON.stringify({
        reimbAmount: amount,
        reimbSubmit: date,
        reimbResolve: null,
        reimbDescript: descript,
        reimbReceipt: null,
        reimbAuthor: data,
        reimbResolver: null,
        reimbStatusId: {
            statusId: 1,
            status: "pending"
        },
        reimbTypeId: reimbType
        
    });
     console.log(reimburse);
     xhr.send(reimburse);
})
     
}