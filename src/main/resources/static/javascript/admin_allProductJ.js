
let totalProductLength;
let totalPagingBtns;
let pd = new Object;
pd.pageNumber = 0;
pd.pageSize = 10;

function getProductsFromController(pageDetail) {
  $("#productTable").empty();
  $.ajax({
    url: "/api/restAdmin/get-productsPaging",
    method: "POST",
    contentType: "application/json",
    data: JSON.stringify(pageDetail),
    success: function (products) {
      for (let product of products) {
        let tr = document.createElement('tr');
        let td1 = document.createElement('td');
        td1.innerText = product["id"];

        let td2 = document.createElement('td');
        let img = document.createElement('img');
        let imagePath = 'data:image/jpg;base64,' + product.productImage;
        img.setAttribute('src', imagePath);
        img.style.width = "50px";
        let td3 = document.createElement('td');
        td3.innerText = product["name"];
        let td4 = document.createElement('td');
        td4.innerText = product["price"];
        let td5 = document.createElement('td');
        td5.innerText = product["pieces"];
        let td6 = document.createElement('td');
        td6.innerText = product["description"];
        let td7 = document.createElement('td');

        const deleteBtn = document.createElement('button');
        deleteBtn.innerText = "delete";
        deleteBtn.setAttribute("data-id", product["id"]);
        deleteBtn.setAttribute("class", "delBtn btn btn-info");

        td2.append(img);
        td7.append(deleteBtn);

        tr.append(td1);
        tr.append(td2);
        tr.append(td3);
        tr.append(td4);
        tr.append(td5);
        tr.append(td6);
        tr.append(td7);
        $("#productTable").append(tr);
      }
    }
  });
}


function getSizeOfProductData() {
  $.get("/api/restAdmin/get-products", (products) => {
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
  $("#productTable").empty();
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


$(document).on('click', '.delBtn', function () {
  let row = $(this).closest('tr');
  console.log(row);
  let rowid = $(this).data('id');
  console.log(rowid);

  $.ajax({
    url: "/api/restAdmin/deleteProductById",
    method: "DELETE",
    data: {
      id: rowid
    },
    success: () => {
      let allProducts = new Object;
      allProducts.pageNumber = $(this).data('id');
      allProducts.pageSize = 10;
      getProductsFromController(allProducts);
      console.log("delete successfully..");
    }
  });

});
