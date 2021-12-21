let role = "";

document.addEventListener('DOMContentLoaded', e => {
    fetch('http://localhost:8080/auth')
        .then(resp => resp.json())
        .then(data => {
            role = data.role
            console.log(role)
        })
})

