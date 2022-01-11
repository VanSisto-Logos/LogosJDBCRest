let emailElement = document.getElementsByName("email")[0];
let passwordElement = document.getElementsByName("password")[0];
let loginButton = document.getElementById("login");

loginButton.addEventListener('click', e => {
    let emailL = emailElement.value;
    let passwordD = passwordElement.value;

    fetch('http://localhost:8080/login', {
        method: 'POST',
        body: JSON.stringify({
            email: emailL,
            password: passwordD
        })
    }).then(res => {
        if (res.status === 200) document.location.href = "/index.html"
        return res.json()
    })

})
