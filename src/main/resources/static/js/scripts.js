/*!
 * Start Bootstrap - SB Admin v7.0.7 (https://startbootstrap.com/template/sb-admin)
 */

window.addEventListener('DOMContentLoaded', event => {
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }
});

// Health check simple
function callBackendAPI() {
    fetch('http://localhost:8080/api/health')
        .then(response => response.text())
        .then(data => {
            document.getElementById('status').innerHTML = data;
        })
        .catch(error => console.error('Health check error:', error));
}

// Test de IA
function testAI() {
    const prompt = document.getElementById('prompt').value.trim();
    const resultDiv = document.getElementById('ai-result');

    if (!prompt) {
        resultDiv.innerHTML = "<div class='alert alert-warning'>Please enter a prompt first.</div>";
        return;
    }

    fetch('http://localhost:8080/api/ai/test', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({ prompt })
    })
    .then(response => response.text())
    .then(data => {
        resultDiv.innerHTML = `<pre class='bg-light p-3 border rounded'>${data}</pre>`;
    })
    .catch(error => {
        resultDiv.innerHTML = `<div class='alert alert-danger'>Error: ${error.message}</div>`;
    });
}

// Upload ZIP/TAR de microservicio
function uploadService() {
    event.preventDefault();

    const fileInput = document.getElementById('service-file');
    const file = fileInput.files[0];
    if (!file) {
        alert("Please select a service file to upload.");
        return;
    }

    const formData = new FormData();
    formData.append("file", file);

    fetch('http://localhost:8080/api/services/upload', {
        method: 'POST',
        body: formData
    })
    .then(response => response.text())
    .then(data => {
        alert(`Service uploaded: ${data}`);
    })
    .catch(error => {
        console.error("Upload error:", error);
        alert("Failed to upload service.");
    });
}
