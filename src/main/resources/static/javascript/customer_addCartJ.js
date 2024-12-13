function getProductsFromController() {
    $("#productTable").empty();
    $.ajax({
        url: "/api/rest-customer/placed-product",
        method: "GET",
        success: function (products) {
            for (let product of products) {
                let tr = document.createElement('tr');

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
                td5.innerText = product["orderedPieces"];
                let td6 = document.createElement('td');
                td6.innerText = product["totalPrice"];
                // let td7 = document.createElement('td');

                // const deleteBtn = document.createElement('button');
                // deleteBtn.innerText = "delete";
                // deleteBtn.setAttribute("data-id", product["id"]);
                // deleteBtn.setAttribute("class", "delBtn btn btn-info");

                td2.append(img);
                // td7.append(deleteBtn);


                tr.append(td2);
                tr.append(td3);
                tr.append(td4);
                tr.append(td5);
                tr.append(td6);
                // tr.append(td7);
                $("#productTable").append(tr);
            }
        }
    });
}

getProductsFromController();

$(document).on('click', '#placeOrderbtn', () => {
    $.get("/api/rest-customer/confirm-order", (response) => {
        if (response == true) {
          $('#placeOrderbtn').text("Order Placed");
        }
    });
});