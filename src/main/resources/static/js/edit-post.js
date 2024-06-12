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

        edit.preventDefault();

        // Get the button's parent element
        const parentElement = findParentByClass(edit.target,"panel-item");

        // Check if parent element exists
        if (parentElement) {
            // Get the sibling element (assuming there's only one sibling)
            // const textArea = parentElement.querySelector(':not([id="' + edit.target.id + '"])');
            const textArea = parentElement.querySelector("textarea");
            const save = parentElement.querySelector(".save");
            const xmark = parentElement.querySelector(".cancel");

            save.classList.add("show");
            xmark.classList.add("show");

            if (textArea) {
                console.log("Sibling element:", textArea);
                textArea.removeAttribute("disabled");
                textArea.focus();
                textArea.style.fontWeight = "500";
                textArea.fontsize = "15px";
                parentElement.style.border = "1px solid #ccc";

            } else {
                console.log("No sibling found for the clicked button.");
            }

            originalValue = textArea.value;

            xmark.addEventListener("click", (x) => {
                x.preventDefault();
                save.classList.remove("show");
                xmark.classList.remove("show");
                textArea.setAttribute("disabled", true);
                textArea.style.fontWeight = "400";
                parentElement.style.border = "none";
                textArea.blur();
                textArea.value = originalValue;
            });

            save.addEventListener("click", (saveButton) => {
                saveButton.preventDefault();
                save.classList.remove("show");
                xmark.classList.remove("show");
                textArea.setAttribute("disabled", true);
                textArea.style.fontWeight = "400";
                parentElement.style.border = "none";
                textArea.blur();
                document.getElementById(textArea.id).setAttribute("value", textArea.value);
                parentElement.submit();
            });

        } else {
            console.log("Clicked button doesn't have a parent element.");
        }
    })
});