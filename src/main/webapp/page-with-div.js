console.log("Page with div");

function randomPage() {
    let targetPages = document.getElementsByClassName('target-page');
    let randomIdx = Math.floor(Math.random() * targetPages.length);
    console.log("Random index: ", randomIdx)
    targetPages[randomIdx].getElementsByTagName('a')[0].click();
}

// From https://www.abeautifulsite.net/adding-and-removing-elements-on-the-fly-using-javascript

function addElement(parentId, elementTag, elementId, html) {
    console.log("Adding element:", elementTag, "of id:", elementId, "as a child of:", parentId);
    let p = document.getElementById(parentId);
    let newElement = document.createElement(elementTag);
    newElement.setAttribute('id', elementId);
    newElement.innerHTML = html;
    p.appendChild(newElement);
}

function removeElement(elementId) {
    console.log("Removing element of id:", elementId)
    let element = document.getElementById(elementId);
    element.parentNode.removeChild(element);
}

let fileId = 0; // used by the addFile() function to keep track of IDs
function addFile() {
    console.log("Adding file. FileId before:", fileId)
    fileId++; // increment fileId to get a unique ID for the new element
    let finalFileId = '\'file-' + fileId + '\'';
    let html =
        '<input type="file" name="uploaded_files[]" /> ' +
        '<a href="" onclick="removeElement(' +
        finalFileId +
        '); return false;">Remove</a>';
    addElement('files', 'p', 'file-' + fileId, html);
    console.log("Adding file. FileId after:", fileId)
}