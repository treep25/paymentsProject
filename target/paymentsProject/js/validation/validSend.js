let form = document.querySelector('.form')
let card = form.querySelector('.card-input')
let amount = form.querySelector('.amount-input')
let fields = form.querySelectorAll('.field')


function validateCardNumber(cardNum) {
    let re = /^[0-9]{4}[\d]{1}[0-9]{4}[\d]{1}[0-9]{4}[\d]{1}[0-9]{4}[\d]{1}$/;
    return re.test(String(cardNum).toLowerCase());
}

form.onsubmit = function () {
    let cardValue = card.value,
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
    if(!validateCardNumber(cardValue)) {
        console.log('email not valid');
        email.classList.add('error');
        return false;
    } else {
        email.classList.remove('error');
    }


}

