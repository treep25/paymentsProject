let form = document.querySelector('.form')
let fields = form.querySelectorAll('.field')


form.onsubmit = function () {
    let emptyInputs = Array.from(fields).filter(input => input.value === '');

    fields.forEach(function (input) {
        if (input.value === '') {
            input.classList.add('error');

        } else {
            input.classList.remove('error');
        }
    });
    if (emptyInputs.length !== 0) {
        console.log('inputs not filled');
        fields.item(0).value
        return false;
    }

}
