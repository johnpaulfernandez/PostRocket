function getSiblingsByClass(element, className) {
    const parent = element.parentElement;
    if (!parent) return [];

    const siblings = parent.getElementsByClassName(className);
    const allSiblings = Array.from(siblings);
    return allSiblings.filter(sibling => sibling !== element);
}

const accordion = document.querySelectorAll(".thread-header");

accordion.forEach(item => {
    item.addEventListener("click", () => {
        item.classList.toggle("active");

        let siblings = getSiblingsByClass(item, "panel-item");

        siblings.forEach(post => {
            if (post.style.display === "flex") {
                post.style.display = "none";
            } else {
                post.style.display = "flex";
                post.style.flexDirection = "row";
                post.style.justifyContent = "space-between";
                post.style.paddingRight = "1rem";
            }
        })
    })
});