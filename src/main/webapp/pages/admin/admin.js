let logoutButton = document.getElementById("logout");

let user = {};
document.addEventListener('DOMContentLoaded', e => {
    fetch('http://localhost:8080/auth')
        .then(resp => {
            if (resp.status === 401 || resp.status === 403)
                window.location.href = "../../index.html";

                return resp.json();
        })
        .then(userData => {
            user = userData;
            update();
        })
})

function update(){
    if (user.role !== "ADMIN") window.location.href = "../../index.html"
    getAllUsers();
}

let users = [];
function getAllUsers() {
    fetch('http://localhost:8080/user')
        .then(res => res.json())
        .then(usrs => {
            users = usrs;
            let tbodyElement = document.getElementById("usersTable").getElementsByTagName("tbody")[0];

            usrs.forEach(user => {
                let trElement = document.createElement("tr");
                trElement.innerHTML = `
                    <td>${user.email}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>
                        <select name="roles" id="role-${user.id}" onchange="changeRoleFor(${user.id})">
                          <option value="chooseRole" disabled>Choose role</option>
                          <option value="USER"${user.role === "USER" ? "selected" : ""}>USER</option>
                          <option value="ADMIN"${user.role === "ADMIN" ? "selected" : ""}>ADMIN</option>
                        </select>
                    </td>
                    <td><button type="button" class="btn btn-danger" onclick="deleteUser(${user.id})">Delete</button></td>
                `
                tbodyElement.appendChild(trElement);
            })
        })

}

logoutButton.addEventListener('click', e => {
    fetch('http://localhost:8080/logout')
        .then(res => {
            if (res.status === 200) window.location.href = "../../index.html"
        })
})

function deleteUser(id) {
    fetch('http://localhost:8080/user?id=' + id, {
        method: 'DELETE'
    }).then(res => {
        if (res.status == 200) refreshTable();
    })
}

function refreshTable() {
    let tbodyElement = document.getElementById("usersTable").getElementsByTagName("tbody")[0];
    tbodyElement.innerHTML = "";
    getAllUsers()
}

function changeRoleFor(id) {
    let selectEl =  document.getElementById("role-" + id);
    let role = selectEl.options[selectEl.selectedIndex].value;
    let user = users.filter(u => u.id == id)[0];
    user.role = role;
    fetch('http://localhost:8080/user', {
        method: 'PUT',
        body: JSON.stringify(user)
    })
        .then(res => {
            console.log(res.status)
            refreshTable();
        })
}

















