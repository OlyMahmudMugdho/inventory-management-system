setTimeout(function() {
    var alerts = document.querySelectorAll(".alert");
    alerts.forEach(function(alert) {
        var fadeOutInterval = setInterval(function() {
            if (!alert.style.opacity) {
                alert.style.opacity = 1;
            }
            if (alert.style.opacity > 0) {
                alert.style.opacity -= 0.1;
            } else {
                clearInterval(fadeOutInterval);
                alert.style.display = "none";
                alert.parentNode.removeChild(alert);
            }
        }, 50);
    });
}, 4000);
