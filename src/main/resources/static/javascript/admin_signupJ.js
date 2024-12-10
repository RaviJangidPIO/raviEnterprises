document.getElementById('profileImage').addEventListener('change', function (event) {
    const file = event.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function (e) {
            document.getElementById('profileImagePreview').src = e.target.result;
        };
        reader.readAsDataURL(file);
    }
});


// Real-time form validation
document.getElementById('signupForm').addEventListener('submit', function (event) {
    let isValid = true;

    // Name validation
    const name = document.getElementById('name').value.trim();
    if (!name) {
        document.getElementById('nameError').textContent = 'Name is required.';
        isValid = false;
    } else {
        document.getElementById('nameError').textContent = '';
    }

    // Email validation
    const email = document.getElementById('email').value.trim();
    const emailPattern = /^[^@\s]+@[^@\s]+\.[^@\s]+$/;
    if (!email || !emailPattern.test(email)) {
        document.getElementById('emailError').textContent = 'Please enter a valid email.';
        isValid = false;
    } else {
        document.getElementById('emailError').textContent = '';
    }

    // Password validation
    const password = document.getElementById('password').value.trim();
    if (password.length < 6) {
        document.getElementById('passwordError').textContent = 'Password must be at least 6 characters.';
        isValid = false;
    } else {
        document.getElementById('passwordError').textContent = '';
    }

    // Confirm password validation
    const confirmPassword = document.getElementById('confirmPassword').value.trim();
    if (confirmPassword !== password) {
        document.getElementById('confirmPasswordError').textContent = 'Passwords do not match.';
        isValid = false;
    } else {
        document.getElementById('confirmPasswordError').textContent = '';
    }

    if (!isValid) event.preventDefault();
});