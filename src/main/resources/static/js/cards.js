import data from "./use-cases.js";

document.addEventListener("DOMContentLoaded", () => {
  generateCards(data);
  clickProduct();
});

function generateCards(data) {
  const productGrid = document.getElementById("tiles");

  data.forEach((item) => {
    let card = document.createElement("article");
    card.className = "card";
    card.innerHTML = `
          <div
            class="item"
            style="
                display: flex;
                flex-direction: column;
                height: 100%;
                justify-content: space-between;
                padding: 20px;
                position: relative;
            "
          ><div class="title">
          <div class="icon-placeholder">
          <img
              src="${item.icon}"
              alt="${item.title}""/>
</div>
</div>

          </div>
        `;
    productGrid.appendChild(card);
  });
}

function clickProduct() {
  const cards = document.querySelectorAll(".productCard");

  cards.forEach((item) => {
    item.addEventListener("click", (event) => {
      localStorage.setItem(
        "productID",
        item.querySelector(".product-image-container a").getAttribute("id")
      );
    });

    item.addEventListener("click", (event) => {
      cards.forEach((item) => {
        item.style.boxShadow = "none";
      });
      item.style.boxShadow = "0 0 5px 2px rgba(59, 56, 57, 0.868)";
    });
  });
}
