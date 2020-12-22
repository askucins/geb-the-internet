console.log("Open link with a delay");

/**
 * @param {number} timeoutInMillis - The timeout in milliseconds
 */
function openGebInNewWindow(timeoutInMillis = 200) {
    setTimeout(function () {
        document.getElementById('geb-in-new-window').click();
    }, timeoutInMillis);
}