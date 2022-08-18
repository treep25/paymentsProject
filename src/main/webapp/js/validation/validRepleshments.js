let form = document.querySelector('.form')
let number = form.querySelector('.input')

function validNum(num) {
    let re =/^\d+$/
    return re.test(String(num));
}
form.onsubmit = function () {
    let inputValue = number.value

    if (inputValue.length === 0) {
        console.log('inputs not filled');
        return false;
    }
    if(!validNum(inputValue)) {
        console.log('email not valid');
        number.classList.add('error');
        return false;
    } else {
        number.classList.remove('error');
    }

}

