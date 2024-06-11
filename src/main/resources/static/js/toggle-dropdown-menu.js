
const trigger = document.querySelectorAll('.dropdown-trigger');
const parentElement = document.querySelector(".foreground");

function findParentByClass(element, className) {
    while (element && element.parentElement) {
        if (element.parentElement.classList.contains(className)) {
            return element.parentElement;
        }
        element = element.parentElement;
    }
    return null; // Not found
}

trigger.forEach(ellipsis => {
    ellipsis.addEventListener('click', (threeDots)=> {
        let options = ellipsis.querySelector('.dropdown-menu');
        options.classList.toggle('show');

        parentElement.addEventListener("click", (clicked) => {

            // Check if clicked element is not the target element and not a descendant of it
            if (!clicked.target.isEqualNode(ellipsis) && !ellipsis.contains(clicked.target)) {
                // Clicked outside the target element
                console.log("Clicked outside the element!");
                options.classList.remove('show');
            }
        });
    });
})