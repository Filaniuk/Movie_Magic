// Function to validate file size
function validateFileSize(input) {
    const file = input.files[0];
    const maxSize = 5 * 1024 * 1024; // 5 MB in bytes

    if (file && file.size > maxSize) {
        alert('The file size exceeds 5 MB.');
        input.value = ''; // Clear the input
        return false;
    }
    return true;
}

// Attach event listeners when the DOM is fully loaded
document.addEventListener('DOMContentLoaded', function() {
    const fileInput = document.querySelector('input[name="attachedFile"]');
    if (fileInput) {
        fileInput.addEventListener('change', function() {
            validateFileSize(this);
        });
    }
});