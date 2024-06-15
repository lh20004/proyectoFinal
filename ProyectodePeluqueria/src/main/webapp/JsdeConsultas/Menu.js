const hamBurger = document.querySelector(".toggle-btn");


hamBurger.addEventListener("click", function () {
  const sidebar = document.querySelector("#sidebar");
  sidebar.classList.toggle("expand");

  // Alternar visibilidad de la imagen en la sección
  const sectionImage = document.querySelector(".section-image");
  if (sidebar.classList.contains("expand")) {
    sectionImage.style.display = 'block';
  } else {
    sectionImage.style.display = 'none';
  }
});



