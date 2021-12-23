
let emailElement = document.getElementsByName("email")[0];
let passwordElement = document.getElementsByName("password")[0];
let passwordRepeatElement = document.getElementsByName("passwordRepeat")[0];
let firstNameElement = document.getElementsByName("firstName")[0];
let lastNameElement = document.getElementsByName("lastName")[0];
let registerButton = document.getElementById("register");

let isPasswordMatch = false;
registerButton.addEventListener("click", e => {
    let emailL = emailElement.value;
    let passwordD = passwordElement.value;
    let passwordRepeatT = passwordRepeatElement.value;
    let firstNameE = firstNameElement.value;
    let lastNameE = lastNameElement.value;

    if (passwordD !== passwordRepeatT) {
        isPasswordMatch = false;
        console.error("Password don't match")
        return;
    } else isPasswordMatch = true;

    let user = {
        email: emailL,
        password: passwordD,
        firstName: firstNameE,
        lastName: lastNameE
    }

    fetch('http://localhost:8080/registration', {
        method: 'POST',
        body: JSON.stringify(user)
    })
        .then(res => {
            if (res.status === 201) document.location.href = "../login/index.html";
            return res;
        })

})
