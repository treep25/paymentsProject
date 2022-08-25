let form = document.querySelector('.form')
let email = form.querySelector('.email-input')
let name = form.querySelector('.name-input')
let secName = form.querySelector('.secName-input')
let phone = form.querySelector('.phone-input')
let password = form.querySelector('input.input.password-input.form-control.field')
let repeatPassword = form.querySelector('.passwordRepeat-input')
let checkbox = form.querySelector('.form-check-input')
let fields = form.querySelectorAll('.field')


function validateEmail(mail) {
    let re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(mail).toLowerCase());
}
function validatePhone(phoneNum){
    let regular = /^(\s*)?(\+)?([- _():=+]?\d[- _():=+]?){10,14}(\s*)?$/;
    return regular.test(String(phoneNum));
}
function validatePassword(pass) {
    let regular = /^[0-9a-zA-Zа-яА-Я!@#$%^&*]{6,}$/;
    return regular.test(String(pass));
}
function validateNameOrSecName(text) {
    let regular = /^[a-zA-Zа-яА-Я]+$/;
    return regular.test(String(text));
}

form.onsubmit = function () {
    let emailValue = email.value,
        phoneValue = phone.value,
        passwordValue = password.value,
        passwordRepeatValue = repeatPassword.value,
        nameValue = name.value,
        secNameValue = secName.value,
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
        fields.item(1).value
        return false;
    }
    if(!validateNameOrSecName(nameValue)){
        console.log('name not valid');
        name.classList.add('error');
        return false;
    } else {
        name.classList.remove('error');
    }
    if(!validateNameOrSecName(secNameValue)){
        console.log('secName not valid');
        secName.classList.add('error');
        return false;
    } else {
        secName.classList.remove('error');
    }
    if(!validateEmail(emailValue)) {
        console.log('email not valid');
        email.classList.add('error');
        return false;
    } else {
        email.classList.remove('error');
    }
    if(!validatePhone(phoneValue)) {
        console.log('pass not valid');
        phone.classList.add('error');
        return false;
    } else {
        phone.classList.remove('error');
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
    if(!checkbox.checked) {
        console.log('checkbox not checked');
        checkbox.classList.add('error');
        return false;
    } else {
        checkbox.classList.remove('error')
    }


}

