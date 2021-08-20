
window.onload = function display(){
    getData();
    getReimb()
};

async function getData() {
    let apiURL = 'http://localhost:8080/project1/users/';
    let authToken = sessionStorage.getItem("token");
    fetch(apiURL + "/" + authToken.split(":")[0])
    .then(response => response.json())
    .then(data => {
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
        h6.innerHTML ="Role";// window.onload = 
        
        thr.appendChild(h1);
        thr.appendChild(h2);
        thr.appendChild(h3);
        thr.appendChild(h4);
        thr.appendChild(h5);
        thr.appendChild(h6);
        table.appendChild(thr);


        let tr = document.createElement('tr');
        let td1 = document.createElement('td');
        let td2 = document.createElement('td');
        let td3 = document.createElement('td');
        let td4 = document.createElement('td');
        let td5 = document.createElement('td');
        let td6 = document.createElement('td');
        td1.innerHTML = data.id;
        td2.innerHTML = data.firstname;
        td3.innerHTML = data.lastname;
        td4.innerHTML = data.username;
        td5.innerHTML = data.email; 
        td6.innerHTML = data.roleid.role;

        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);

        table.appendChild(tr);

        dataSection.appendChild(table);
})
}


async function getReimb(){
    let dataSection = document.getElementById('reimburse');
    dataSection.innerHTML = '';
    let thr = document.createElement('tr');
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


    let apiURL = 'http://localhost:8080/project1/reimbursement';
    let authToken = sessionStorage.getItem("token");

    fetch(apiURL)
    .then(response => response.json())
    .then(data => {
        console.log(data);
        var table = document.createElement('table');
        table.style.width = "100%";
        table.style.border = "1px solid black";
        table.style.borderCollapse = "collapse";
       
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
        console.log(authToken.split(":")[0]);
        if (data.length > 1){
            for (var i = 0; i < data.length; i++) {
                if (data[i].reimbAuthor.id ==  authToken.split(":")[0]){
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

                    td1.innerHTML = data[i].reimbId;
                    td2.innerHTML = data[i].reimbAmount;
                    td3.innerHTML = data[i].reimbSubmit;
                    td4.innerHTML = data[i].reimbResolve;
                    if (data[i].reimbDescript === null){
                        td5.innerHTML = "";
                    }else{
                        td5.innerHTML = data[i].reimbDescript;
                    }
                    if (data[i].reimbReceipt === null){
                        td6.innerHTML = "";
                    }else{
                        td6.innerHTML = data[i].reimbReceipt;
                    }
                    td7.innerHTML = data[i]. reimbAuthor.username;
                    if (data[i].reimbResolver === null){
                        td8.innerHTML = "";
                    }else{
                        td8.innerHTML = data[i].reimbResolver.username;
                    }
                    td9.innerHTML = data[i].reimbStatusId.status;
                    td10.innerHTML = data[i].reimbTypeId.type; 
                
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
                if (data.reimbAuthor.id ==  authToken.split(":")[0]){
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

                    td1.innerHTML = data.reimbId;
                    td2.innerHTML = data.reimbAmount;
                    td3.innerHTML = data.reimbSubmit;
                    td4.innerHTML = data.reimbResolve;
                    if (data.reimbDescript === null){
                        td5.innerHTML = "";
                    }else{
                        td5.innerHTML = data.reimbDescript;
                    }
                    if (data.reimbReceipt === null){
                        td6.innerHTML = "";
                    }else{
                        td6.innerHTML = data.reimbReceipt;
                    }
                    td7.innerHTML = data.reimbAuthor.username;
                    if (data.reimbResolver === null){
                        td8.innerHTML = "";
                    }else{
                        td8.innerHTML = data.reimbResolver.username;
                    }
                    td9.innerHTML = data.reimbStatusId.status;
                    td10.innerHTML = data.reimbTypeId.type; 
                
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
                    dataSection.appendChild(table);
                }
            }
         
        })  
}
