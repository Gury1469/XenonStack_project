// Login function
function login() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    // Perform validations if required
    // Send the data to the server using fetch or XMLHttpRequest
    // For example, using fetch:

    fetch('http://localhost:80/loginServlet', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username: username, password: password }),
    })
    .then(response => {
        if (response.ok) {
            window.location.href = 'success.html'; // Redirect to success page
        } else {
            window.location.href = 'login.html'; // Redirect back to login page
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

// Submit Contact Form function
function submitContactForm() {
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const message = document.getElementById('message').value;

    // Perform validations if required
    // Send the data to the server using fetch or XMLHttpRequest
    // For example, using fetch:

    fetch('http://localhost:80/contactUsServlet', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name: name, email: email, message: message }),
    })
    .then(response => {
        // Handle the response if needed
        console.log('Contact form submitted successfully');
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

