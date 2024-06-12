function getSiblingsByClass(element, className) {
    const parent = element.parentElement;
    if (!parent) return [];

    const siblings = parent.getElementsByClassName(className);
    const allSiblings = Array.from(siblings);
    return allSiblings.filter(sibling => sibling !== element);
}

const accordion = document.querySelectorAll(".thread-header");

function toggleAccordion(item) {
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
}

accordion.forEach(item => {
    item.addEventListener("click", () => {
        toggleAccordion(item);
    })
});

window.onload = function() {
    // Check if opened accordion IDs are stored in session storage
    var openedAccordionIds = sessionStorage.getItem("openedAccordions");
    if (openedAccordionIds) {
        var ids = openedAccordionIds.split(",");
        for (var i = 0; i < ids.length; i++) {
            var accordionElement = document.getElementById(ids[i]);
            if (accordionElement) {
                toggleAccordion(accordionElement);
            }
        }
    }

    // Check if scroll position is stored in session storage
    if (sessionStorage.getItem("lastScrollPosition")) {
        var scrollPosition = parseInt(sessionStorage.getItem("lastScrollPosition"));
        window.scrollTo(0, scrollPosition); // Restore scroll position
    }
};

window.onbeforeunload = function() {

    // Get currently opened accordion IDs
    var openedAccordionIds = [];
    var accordionElements = document.querySelectorAll(".thread-header.active");
    for (var i = 0; i < accordionElements.length; i++) {
        openedAccordionIds.push(accordionElements[i].id);
    }
    sessionStorage.setItem("openedAccordions", openedAccordionIds.join(","));

    // Store current scroll position before navigating away
    sessionStorage.setItem("lastScrollPosition", window.scrollY);
};
