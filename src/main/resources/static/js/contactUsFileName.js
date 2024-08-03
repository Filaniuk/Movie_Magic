// contactUsFileName.js

function displayFileName() {
    const fileInput = document.getElementById('attachedFile');
    const fileNameSpan = document.getElementById('fileName');
    const file = fileInput.files[0];

    if (file) {
        fileNameSpan.textContent = file.name;
    } else {
        fileNameSpan.textContent = 'No file chosen';
    }
}
