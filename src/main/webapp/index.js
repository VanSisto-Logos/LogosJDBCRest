let user = {};

document.addEventListener('DOMContentLoaded', e => {
    fetch('http://localhost:8080/auth')
        .then(resp => resp.json())
        .then(userData => {
            user = userData;
            update();
        })
})

function update() {
    if (user.role === "ADMIN") {
        let forAdmin = document.getElementById("forAdminButton");

        let adminPanelElement = document.createElement("a");
        adminPanelElement.setAttribute("href", "pages/admin/index.html")
        adminPanelElement.innerText = "Admin panel";

        forAdmin.appendChild(adminPanelElement);
    }
}

document
    .getElementById("login")
    .addEventListener('click', e => {
        window.location.href = "pages/login/index.html"
})