console.log("Page with div");

function randomPage() {
    let targetPages = document.getElementsByClassName('target-page');
    let randomIdx = Math.floor(Math.random() * targetPages.length);
    console.log("Random index: ", randomIdx)
    targetPages[randomIdx].getElementsByTagName('a')[0].click();
}