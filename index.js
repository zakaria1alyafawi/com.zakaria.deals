function loadTable() {
  const xhttp = new XMLHttpRequest();
  const url = 'http://localhost:8080/deals/getall';
  // const username = 'deals';
  // const password = 'deals123';
  debugger;

  xhttp.onreadystatechange = function () {
    debugger;
    if (this.readyState == 4 && this.status == 200) {
      console.log(this.responseText);
      var trHTML = '';
      const objects = JSON.parse(this.responseText);
      for (let object of objects.deals) {
        trHTML += '<tr>';
        trHTML += '<td>' + object['id'] + '</td>';
        // trHTML += '<td><img width="50px" src="' + object['avatar'] + '" class="avatar"></td>';
        trHTML += '<td>' + object['fromCurrencyCode'] + '</td>';
        trHTML += '<td>' + object['toCurrencyCode'] + '</td>';
        trHTML += '<td>' + new Date(object['dealTime']).toISOString().split('T')[0] + '</td>';
        trHTML += '<td>' + object['dealAmount'] + '</td>';
        trHTML += '<td>' + new Date(object['creationDate']).toISOString().split('T')[0] + '</td>';
        trHTML += '<td>' + new Date(object['modificationDate']).toISOString().split('T')[0] + '</td>';

        trHTML += '<td><button type="button" class="btn btn-outline-secondary" onclick="showUserEditBox(' + object['id'] + ')">Edit</button>';
        trHTML += '<button type="button" class="btn btn-outline-danger" onclick="userDelete(' + object['id'] + ')">Del</button></td>';
        trHTML += "</tr>";
      }
      document.getElementById("mytable").innerHTML = trHTML;
    }
  };

  // xhttp.open("GET", url, false, username, password);
  xhttp.open("GET", url);
  xhttp.setRequestHeader("Content-type", "application/json");
  xhttp.setRequestHeader("Authorization", "Basic ZGVhbHM6ZGVhbHMxMjM=");
  xhttp.send();
}

loadTable();
function showUserCreateBox() {

  Swal.fire({
    title: 'Create user',
    html:
      '<input id="id" type="hidden">' +
      '<input id="fname" name = "fname" class="swal2-input" placeholder="From Currency Code" required minlength="3" maxlength="4">' +
      '<input id="lname" class="swal2-input" placeholder="To Currency Code" required minlength="3" maxlength="4">' +
      '<input id="username" type="date" class="swal2-input" placeholder="Deal Time">' +
      '<input id="email" class="swal2-input" placeholder="Deal Amount" type = "number">',

    focusConfirm: false,
    preConfirm: () => {
      userCreate();
    }
  })

}

function userCreate() {
  const fromCurrencyCode = document.getElementById("fname").value;
  const toCurrencyCode = document.getElementById("lname").value;
  const dealTime = document.getElementById("username").value;
  const dealAmount = document.getElementById("email").value;

  const url = 'http://localhost:8080/deals/add';
  // const username = 'deals';
  // const password = 'deals123';

  const xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      const objects = JSON.parse(this.responseText);
      Swal.fire(objects['message']);
      loadTable();
    }
  };

  // xhttp.open("POST", url, false, username, password);
  xhttp.open("POST", url);
  xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
  xhttp.setRequestHeader("Authorization", "Basic ZGVhbHM6ZGVhbHMxMjM=");
  xhttp.send(JSON.stringify({
    "fromCurrencyCode": fromCurrencyCode, "toCurrencyCode": toCurrencyCode, "dealTime": dealTime, "dealAmount": dealAmount
  }));
}
function showUserEditBox(id) {
  console.log(id);

  const url = 'http://localhost:8080/deals/getby/' + id;
  // const username = 'deals';
  // const password = 'deals123';

  const xhttp = new XMLHttpRequest();

  xhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      const objects = JSON.parse(this.responseText);
      user = objects['deals'];
      user = user[0];
      console.log(user);
      Swal.fire({
        title: 'Edit User',
        html:
          '<input id="id" type="hidden" value=' + user['id'] + '>' +
          '<input id="fname" class="swal2-input" placeholder="First" value="' + user['fromCurrencyCode'] + '">' +
          '<input id="lname" class="swal2-input" placeholder="Last" value="' + user['toCurrencyCode'] + '">' +
          '<input id="username" type="date" style="width:200px;" class="swal2-input" placeholder="Username" value="' + user['dealTime'] + '">' +
          '<input id="email" class="swal2-input" placeholder="Email" value="' + user['dealAmount'] + '">',
        focusConfirm: false,
        preConfirm: () => {
          userEdit();
        }
      })
    }
  };

  // xhttp.open("GET", url, false, username, password);
  xhttp.open("GET", url);
  xhttp.setRequestHeader("Content-type", "application/json");
  xhttp.setRequestHeader("Authorization", "Basic ZGVhbHM6ZGVhbHMxMjM=");
  xhttp.send();
}

function userEdit() {
  const id = document.getElementById("id").value;
  const fromCurrencyCode = document.getElementById("fname").value;
  const toCurrencyCode = document.getElementById("lname").value;
  const dealTime = document.getElementById("username").value;
  const dealAmount = document.getElementById("email").value;

  const url = 'http://localhost:8080/deals/edit';
  // const usernameApi = 'deals';
  // const password = 'deals123';

  const xhttp = new XMLHttpRequest();

  xhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      const objects = JSON.parse(this.responseText);
      Swal.fire(objects['message']);
      loadTable();
    }
  };

  // xhttp.open("POST", url, false, usernameApi, password);
  xhttp.open("POST", url);
  xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
  xhttp.setRequestHeader("Authorization", "Basic ZGVhbHM6ZGVhbHMxMjM=");
  xhttp.send(JSON.stringify({
    "id": id, "fromCurrencyCode": fromCurrencyCode, "toCurrencyCode": toCurrencyCode, "dealTime": dealTime, "dealAmount": dealAmount
  }));
  
}
function userDelete(id) {
  // const xhttp = new XMLHttpRequest();
  // xhttp.open("DELETE", "https://www.mecallapi.com/api/users/delete");
  // xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
  // xhttp.send(JSON.stringify({
  //   "id": id
  // }));
  // xhttp.onreadystatechange = function () {
  //   if (this.readyState == 4) {
  //     const objects = JSON.parse(this.responseText);
  //     Swal.fire(objects['message']);
  //     loadTable();
  //   }
  // };


  const url = 'http://localhost:8080/deals/delete/' + id;
  // const username = 'deals';
  // const password = 'deals123';

  const xhttp = new XMLHttpRequest();

  xhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      const objects = JSON.parse(this.responseText);
      const errorCode = objects['errorCode'];
      const errorMsg = objects['errorMsg'];

      if (errorCode !== null && errorMsg !== null) {
        alert("Success Delete");
      } else {
        alert("ErrorCode: " + errorCode + " , errorMsg:" + errorCode);
      }
      loadTable();

      // user = objects['deals'];
      // user = user[0];
      // console.log(user);
      // Swal.fire({
      //   title: 'Edit User',
      //   html:
      //     '<input id="id" type="hidden" value=' + user['id'] + '>' +
      //     '<input id="fname" class="swal2-input" placeholder="First" value="' + user['fromCurrencyCode'] + '">' +
      //     '<input id="lname" class="swal2-input" placeholder="Last" value="' + user['toCurrencyCode'] + '">' +
      //     '<input id="username" type="date" style="width:200px;" class="swal2-input" placeholder="Username" value="' + user['dealTime'] + '">' +
      //     '<input id="email" class="swal2-input" placeholder="Email" value="' + user['dealAmount'] + '">',
      //   focusConfirm: false,
      //   preConfirm: () => {
      //     userEdit();
      //   }
      // })
    }
  };

  // xhttp.open("GET", url, false, username, password);
  xhttp.open("GET", url);
  xhttp.setRequestHeader("Content-type", "application/json");
  xhttp.setRequestHeader("Authorization", "Basic ZGVhbHM6ZGVhbHMxMjM=");
  xhttp.send();
}