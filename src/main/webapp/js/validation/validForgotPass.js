let form = document.querySelector('.form')
let email = form.querySelector('.email-input')
let password = form.querySelector('.password-input')
let repeatPassword = form.querySelector('.passwordReap-input')
let fields = form.querySelectorAll('.field')

function validateEmail(mail) {
    let re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(mail).toLowerCase());
}
function validatePassword(pass) {
    let regular = /^[0-9a-zA-Zа-яА-Я!@#$%^&*]{6,}$/;
    return regular.test(String(pass));
}
form.onsubmit = function () {
    let emailValue = email.value,
        passwordValue = password.value,
        passwordRepeatValue = repeatPassword.value,
        emptyInputs = Array.from(fields).filter(input => input.value === '');

    fields.forEach(function (input) {
        if (input.value === '') {
            input.classList.add('error');
        } else {
            input.classList.remove('error');
        }
    });
    if (emptyInputs.length !== 0) {
        console.log('inputs not filled');
        return false;
    }
    if(!validateEmail(emailValue)) {
        console.log('email not valid');
        email.classList.add('error');
        return false;
    } else {
        email.classList.remove('error');
    }
    if(!validatePassword(passwordValue)) {
        console.log('pass not valid');
        password.classList.add('error');
        return false;
    } else {
        password.classList.remove('error');
    }
    if(!validatePassword(passwordRepeatValue)
        ||passwordRepeatValue!==passwordValue){
        console.log("pass are not same or password is incorrect");
        password.classList.add('error');
        repeatPassword.classList.add('error');
        return false;
    }else{
        password.classList.remove('error');
        repeatPassword.classList.remove('error');
    }

}

