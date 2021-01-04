console.log("Page with div");

function randomPage() {
    let targetPages = document.getElementsByClassName('target-page');
    let randomIdx = Math.floor(Math.random() * targetPages.length);
    console.log("Random index: ", randomIdx)
    targetPages[randomIdx].getElementsByTagName('a')[0].click();
}

// Based on https://www.abeautifulsite.net/adding-and-removing-elements-on-the-fly-using-javascript

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

// Based on https://javascript.info/closure#nested-functions

function nextFileId() {
    let fileId = 0;
    return function () {
        return fileId++;
    };
}

// Initialization a generator of unique fileIds
let fileIdGen = nextFileId();

function addFile() {
    let fileId = fileIdGen();
    console.log("Adding file of id:", fileId)
    let fileElementId = 'file-' + fileId;
    let html =
        '<input type="file" name="uploaded_files[]" /> ' +
        '<a href="" onclick="removeElement(' +
        '\'' + fileElementId + '\'' +
        '); return false;">Remove</a>';
    addElement('files', 'p', fileElementId, html);
}
