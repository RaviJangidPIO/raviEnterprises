document.getElementById("loginForm").addEventListener("submit", async function (event) {
    event.preventDefault();
    let loginRequest = new Object;
    loginRequest.username = document.getElementById("email").value;
    loginRequest.password = document.getElementById("password").value;

    try {
        const response = await fetch("signin-login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(loginRequest),
        });

        if (response.ok) {
            const data = await response.json();
            const token = data.jwtToken;
            const roles = data.roles;

            document.cookie = "jwt-token=" + token + ";  path=/;"

            $.ajax({
                url: '/authenticated-user',
                type: 'GET',
                success: function (user) {
                    if (user.role === 'ADMIN') {
                        window.location.href = '/api/admin/dashBoard';
                    } else if (user.role === 'USER') {
                        window.location.href = '/api/public/home';
                    }else if(user.role === 'OWNER'){
                        localStorage.setItem('owner','ownerAuth');
                        window.location.href = '/api/admin/dashBoard';
                    }

                },
                error: function (xhr) {
                    alert("error............");
                }
            });
        } else {
            alert("Invalid credentials");
        }
    } catch (error) {
        console.error("Error during login:", error);
        alert("Something went wrong");
    }
});


// let currentUser = localStorage.getItem("currentUser");
// if(currentUser!="null"){
//     let errorMessage = document.createElement("h5");
//     errorMessage.innerText = "Already a user , please login...";
//     $("#error-message-div").append(errorMessage);
// }
