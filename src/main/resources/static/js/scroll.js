window.onload = function() {
    // Check if scroll position is stored in session storage
    if (sessionStorage.getItem("lastScrollPosition")) {
        var scrollPosition = parseInt(sessionStorage.getItem("lastScrollPosition"));
        window.scrollTo(0, scrollPosition); // Restore scroll position
    }
};

window.onbeforeunload = function() {
    // Store current scroll position before navigating away
    sessionStorage.setItem("lastScrollPosition", window.scrollY);
};