let form = document.querySelector('.form')
let email = form.querySelector('.email-input')
let amount = form.querySelector('.amount-input')
let fields = form.querySelectorAll('.field')


function validateEmail(mail) {
    let re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(mail).toLowerCase());
}

form.onsubmit = function () {
    let emailValue = email.value,
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
    if(!validateEmail(emailValue)) {
        console.log('email not valid');
        email.classList.add('error');
        return false;
    } else {
        email.classList.remove('error');
    }


}

