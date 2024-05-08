document.addEventListener("DOMContentLoaded", () => {
  const inputs = document.querySelectorAll(".input-group");

  inputs.forEach((input) => {
    const upButton = input.querySelector(".up");
    const downButton = input.querySelector(".down");
    const quantityInput = input.querySelector(".form-control");

    upButton.addEventListener("click", () => {
      quantityInput.value++;
    });

    downButton.addEventListener("click", () => {
      if (quantityInput.value > 1) {
        quantityInput.value--;
      }
    });
  });
});
