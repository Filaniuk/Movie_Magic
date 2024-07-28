setTimeout(function() {
    var notificationBar = document.querySelector(".notification-bar");
    if (notificationBar) {
        notificationBar.style.top = "-50px"; // Slide up effect
        setTimeout(function() {
            notificationBar.style.display = "none";
        }, 300); // Wait for slide up transition to complete
    }
}, 3000);