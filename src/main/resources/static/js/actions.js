document.addEventListener("DOMContentLoaded", function() {
    const sidebarToggle = document.querySelector(".lg:hidden button");
    const sidebarContent = document.querySelector(".lg:hidden");

    sidebarToggle.addEventListener("click", function() {
        sidebarContent.classList.toggle("hidden");
    });
});

function clickable() {
    alert("It is working!!");
}