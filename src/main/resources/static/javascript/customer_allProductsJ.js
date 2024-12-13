
let totalProductLength;
let totalPagingBtns;
let pd = new Object;
pd.pageNumber = 0;
pd.pageSize = 10;

function getProductsFromController(pageDetail) {
  $("#mainDivContainingAllCards").empty();
  $.ajax({
    url: "/api/rest-public/get-productsForCustomer",
    method: "POST",
    contentType: "application/json",
    data: JSON.stringify(pageDetail),
    success: function (products) {
      for (let product of products) {
        let div1 = document.createElement("div");
        div1.setAttribute("class", "col-lg-3");

        let div2 = document.createElement("div");
        div2.setAttribute("class", "card m-4 card-item");
        div2.setAttribute("style", "width: 18rem;");

        let image1 = document.createElement("img");
        let imagePath = 'data:image/jpg;base64,' + product.productImage;
        image1.setAttribute('src', imagePath);
        image1.setAttribute("class", "card-img-top")

        let div3 = document.createElement('div');
        div3.setAttribute("class", "card-body");

        let h51 = document.createElement("h5");
        h51.setAttribute("class", "card-title");
        h51.innerText = product["name"];

        let h52 = document.createElement("h5");
        h51.innerText = 'Total piece : ' + product["pieces"];

        let h53 = document.createElement("h5");
        h52.innerText = 'prices/Items : ' + product["price"];

        let div4 = document.createElement("div");

        let inputBox = document.createElement("input");
        inputBox.setAttribute("type", "number");
        inputBox.setAttribute("placeholder", "pieces");
        inputBox.setAttribute("name", "pieces");
        let inputId = 'inputPieces' + product['id'];
        inputBox.setAttribute("id", inputId);
        inputBox.setAttribute("class", "form-control mx-1 mb-2");

        let btn = document.createElement('button');
        btn.setAttribute("class", "addCartBtn");
        btn.setAttribute("data-id",product["id"]);
        btn.innerText = "Add Cart";

        div4.append(inputBox);
        div4.append(btn);

        div3.append(h51);
        div3.append(h52);
        div3.append(h53);
        div3.append(div4);

        div2.append(image1);
        div2.append(div3);

        div1.append(div2);

        $("#mainDivContainingAllCards").append(div1);
      }
    }
  });
}

$(document).on('click','.addCartBtn',function(){
  let productId = $(this).data('id');
  const currentId = '#inputPieces' + productId;
  let totalPieces = Number($(currentId).val());
 $.post("/api/rest-customer/add-cart",{id:productId,pieces:totalPieces},function(){

 });
});


function getSizeOfProductData() {
  $.get("/api/rest-public/get_allProducts", (products) => {
    totalProductLength = products.length;
    totalPagingBtns = totalProductLength / 10;
    if (totalPagingBtns.toString().indexOf('.') != -1) {
      totalPagingBtns = totalPagingBtns + 1;
    }
    creatingPagingBtns(totalPagingBtns);
  });
  getProductsFromController(pd);
}

function creatingPagingBtns(totalBtns) {
  for (let i = 0; i < totalBtns; i++) {
    let btnPg = document.createElement('button');
    btnPg.setAttribute("class", "currentPaging");
    btnPg.setAttribute("data-id", i);
    btnPg.innerText = i + 1;
    $("#pagintion-boxs").append(btnPg);
  }
}

getSizeOfProductData();

let prevId;
$(document).on('click', '.currentPaging', function () {
  $("#mainDivContainingAllCards").empty();
  $(".currentPaging").css({
    "background-color": "white"
  });
  let allProducts = new Object;
  allProducts.pageNumber = $(this).data('id');
  prevId = allProducts.pageNumber;
  allProducts.pageSize = 10;
  $(this).css({ "background-color": "blue" });
  getProductsFromController(allProducts);

});


