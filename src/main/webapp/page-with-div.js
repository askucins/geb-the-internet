console.log("Page with div");

function randomPage() {
    let targetPages = document.getElementsByClassName('target-page');
    let randomIdx = Math.floor(Math.random() * targetPages.length);
    console.log("Random index: ", randomIdx)
    targetPages[randomIdx].getElementsByTagName('a')[0].click();
}

// Based on https://www.abeautifulsite.net/adding-and-removing-elements-on-the-fly-using-javascript

/**
 * @param {string} parentId
 * @param {string} elementTag
 * @param {string} elementId
 * @param {string} elementClass
 * @param {string} html
 * @param {number} timeout - in millis, a delay before displaying that element
 */
function addElement(parentId, elementTag, elementId, elementClass, html, timeout = 100) {
    console.log("Adding element:", elementTag, "of id:", elementId, "as a child of:", parentId);
    setTimeout(function () {
        let p = document.getElementById(parentId);
        let newElement = document.createElement(elementTag);
        newElement.setAttribute('id', elementId);
        newElement.setAttribute('class', elementClass);
        newElement.innerHTML = html;
        p.appendChild(newElement);
        console.log("...added element:", elementTag, "of id:", elementId, "as a child of:", parentId);
    }, timeout);

}

/**
 * @param {string} elementId
 * @param {number} timeout - in millis, a delay before removing that element
 */
function removeElement(elementId, timeout = 100) {
    console.log("Removing element of id:", elementId)
    setTimeout(function () {
        let element = document.getElementById(elementId);
        element.parentNode.removeChild(element);
        console.log("...removed element of id:", elementId)
    }, timeout);
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

// Adding global configuration controlled from Geb tests of 'wait'

let configTimeoutOfAdd = 1000;
let configTimeoutOfRemove = 2000;

/**
 * @param {number} timeoutOfAdd
 * @param {number} timeoutOfRemove
 * This is to be used in Geb, like e.g.
 * "js.configTimeouts(6000, 10000)"
 */
function configTimeouts(timeoutOfAdd, timeoutOfRemove) {
    configTimeoutOfAdd = timeoutOfAdd;
    configTimeoutOfRemove = timeoutOfRemove;
}

/**
 * @param {number} timeoutOfAdd - a time in millis after which a new element is added (after a click)
 * @param {number} timeoutOfRemove - a time in millis before an element is removed (after a click)
 */
function addFile(timeoutOfAdd = configTimeoutOfAdd, timeoutOfRemove = configTimeoutOfRemove) {
    let fileId = fileIdGen();
    console.log("Adding file of id:", fileId)
    let fileElementId = 'file-' + fileId;
    let html =
        '<input type="file" name="uploaded_files[]" /> ' +
        '<a href="" onclick="removeElement(' +
        '\'' + fileElementId + '\'' +
        ', ' + timeoutOfRemove +
        '); return false;">Remove</a>';
    addElement('files', 'p', fileElementId, 'added-file', html, timeoutOfAdd);
}
