const editButtons = document.querySelectorAll("#edit-action");

function findParentByClass(element, className) {
    while (element && element.parentElement) {
        if (element.parentElement.classList.contains(className)) {
            return element.parentElement;
        }
        element = element.parentElement;
    }
    return null; // Not found
}
editButtons.forEach(item => {
    item.addEventListener("click", (edit) => {

        // Get the button's parent element
        const parentElement = findParentByClass(edit.target,"panel-item");

        // Check if parent element exists
        if (parentElement) {
            // Get the sibling element (assuming there's only one sibling)
            const textArea = parentElement.querySelector(':not([id="' + edit.target.id + '"])');

            if (textArea) {
                console.log("Sibling element:", textArea);
                textArea.removeAttribute("disabled");
                textArea.focus();
                textArea.fontWeight = 700;
                parentElement.style.border = "1px solid #ccc";
            } else {
                console.log("No sibling found for the clicked button.");
            }
        } else {
            console.log("Clicked button doesn't have a parent element.");
        }
    })
});