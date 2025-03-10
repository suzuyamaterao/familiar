document.addEventListener("DOMContentLoaded", function() {
    const userIcon = document.getElementById("userIcon");
    const userDropdown = document.getElementById("userDropdown");
    const closeDropdown = document.getElementById("closeDropdown");

    userIcon.addEventListener("click", function() {
        userDropdown.classList.toggle("show");
    });

    closeDropdown.addEventListener("click", function() {
        userDropdown.classList.remove("show");
    });
});

function confirmTransaction() {
    alert('振込が完了しました');
}