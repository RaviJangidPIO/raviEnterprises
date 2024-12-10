
let totalCustomerLength;
let totalPagingBtns;
let pd = new Object;
pd.pageNumber = 0;
pd.pageSize = 10;

function getCustomerFromController(pageDetail) {
    $.ajax({
        url: "/api/restAdmin/get-users",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(pageDetail),
        success: function (users) {
            for (let user of users) {
                let tr = document.createElement('tr');
                let td1 = document.createElement('td');
                td1.innerText = user["id"];

                let td2 = document.createElement('td');
                let img = document.createElement('img');
                let imagePath = 'data:image/jpg;base64,' + user.profileImage;
                img.setAttribute('src', imagePath);
                img.style.width = "50px";
                let td3 = document.createElement('td');
                td3.innerText = user["name"];
                let td4 = document.createElement('td');
                td4.innerText = user["email"];
                let td5 = document.createElement('td');
                td5.innerText = user["mobileNumber"];
                let td6 = document.createElement('td');
                td6.innerText = user["role"];
                let td7 = document.createElement('td');

                const deleteBtn = document.createElement('button');
                deleteBtn.innerText = "delete";
                deleteBtn.setAttribute("data-id", user["id"]);
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
                $("#customerTable").append(tr);
            }
        },
        error: function (xhr, status, error) {
            console.error("Error adding gamer: ", error);
            console.error("Response: ", xhr.responseText);
        }
    });

}

function getSizeOfCustomerData() {
    $.get("/api/restAdmin/get-usersOnce", (users) => {
        totalCustomerLength = users.length;
        totalPagingBtns = totalCustomerLength / 10;
        if (totalPagingBtns.toString().indexOf('.') != -1) {
            totalPagingBtns = totalPagingBtns + 1;
        }
        creatingPagingBtns(totalPagingBtns);
    });
    
    getCustomerFromController(pd);
}

function creatingPagingBtns(totalBtns) {
    for (let i = 0; i < totalBtns; i++) {
        let btnPg = document.createElement('button');
        btnPg.setAttribute("class", "currentPaging");
        btnPg.setAttribute("data-id", i);
        btnPg.innerText = i+1;
        $("#pagintion-boxs").append(btnPg);
    }
}

getSizeOfCustomerData();




let prevId;
$(document).on('click','.currentPaging',function ()  {
    $("#customerTable").empty();
    $(".currentPaging").css({
       "background-color":"white"
    });
    let allUsers = new Object;
    allUsers.pageNumber = $(this).data('id');
    prevId = allUsers.pageNumber;
    allUsers.pageSize = 10;
    $(this).css({"background-color":"blue"});
    getCustomerFromController(allUsers);
    
});



$(document).on('click', '.delBtn', function () {
    let row = $(this).closest('tr');
    console.log(row);
    let rowid = $(this).data('id');
    console.log(rowid);

    $.ajax({
        url: "/api/restAdmin/deleteUserById",
        method: "DELETE",
        data: {
            id: rowid
        },
        success: () => {
            const pageDetail = new Object;
            pageDetail.pageNumber = 1;
            pageDetail.pageSize = 10;
            getCustomerFromController(pageDetail);
            console.log("delete successfully..");
        }
    });

});