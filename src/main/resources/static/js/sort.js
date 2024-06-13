const asc = document.getElementById("asc");
const desc = document.getElementById("desc");

function getSearchParamBool(paramName) {
    const urlParams = new URLSearchParams(window.location.search);
    const paramValue = urlParams.get(paramName);

    if (paramValue === "true") {
        return true;
    } else if (paramValue === "false") {
        return false;
    } else {
        return null;
    }
}

function checkUrlOnLoad() {
    const sortByAsc = getSearchParamBool("sortByTitleAsc");

    if (sortByAsc) {
        asc.classList.add("active");
    }

    if (!sortByAsc && sortByAsc !== null) {
        desc.classList.add("active");
    }
}

document.addEventListener("DOMContentLoaded", () => {
    checkUrlOnLoad();
});