const deleteButtons = document.querySelectorAll("#delete-action");

function findParentByClass(element, className) {
    while (element && element.parentElement) {
        if (element.parentElement.classList.contains(className)) {
            return element.parentElement;
        }
        element = element.parentElement;
    }
    return null; // Not found
}
deleteButtons.forEach(item => {
    item.addEventListener("click", (deleteEvent) => {

        deleteEvent.preventDefault();

        // Get the button's parent element
        const parentElement = findParentByClass(deleteEvent.target,"panel-item");

        // Check if parent element exists
        if (parentElement) {
            parentElement.querySelector('input[name="_method"]').value = 'DELETE';
            parentElement.submit();
        } else {
            console.log("Clicked button doesn't have a parent element.");
        }
    })
});