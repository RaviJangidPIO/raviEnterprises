function getPopularProduct() {
    $("#mainDivContainingAllCards").empty();
    $.ajax({
        url: "/api/rest-public/get-someProducts",
        method: "GET",
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
                let btn = document.createElement('button');
                btn.setAttribute("class", "btn btn-primary");
                btn.innerText = "More";

                div3.append(h51);
                div3.append(h52);
                div3.append(h53);
                div3.append(btn);

                div2.append(image1);
                div2.append(div3);

                div1.append(div2);

                $("#mainDivContainingAllCards").append(div1);
            }
        }
    });
}

getPopularProduct();

// function createCardView(product) {
//     let div1 = document.createElement("div");
//     div1.setAttribute("class", "col-lg-3");

//     let div2 = document.createElement("div");
//     div2.setAttribute("class", "card m-4 card-item");
//     div2.setAttribute("style", "width: 18rem;");

//     let image1 = document.createElement("img");
//     let imagePath = 'data:image/jpg;base64,' + product.productImage;
//     image1.setAttribute('src', imagePath);
//     image1.setAttribute("class", "card-img-top")

//     let div3 = document.createElement('div');
//     div.setAttribute("class", "card-body");

//     let h51 = document.createElement("h5");
//     h51.setAttribute("class", "card-title");
//     h51.innerText = product["name"];

//     let h52 = document.createElement("h5");
//     h51.innerText = 'product piece : ' + product["pieces"];

//     let h53 = document.createElement("h5");
//     h52.innerText = 'product prices : ' + product["price"];
//     let btn = document.createElement('button');
//     btn.setAttribute("class", "btn btn-primary");
//     btn.innerText = "More";

//     div3.append(h51);
//     div3.append(h52);
//     div3.append(h53);
//     div3.append(btn);

//     div2.append(image1);
//     div2.append(div3);

//     div1.append(div2);

//     $("#mainDivContainingAllCards").append(div1);


// }