
const trigger = document.querySelectorAll('.dropdown-trigger');

trigger.forEach(ellipsis => {
    ellipsis.addEventListener('click', ()=> {
        let options = ellipsis.querySelector('.dropdown-menu');
        options.classList.toggle('show');
    });
})
