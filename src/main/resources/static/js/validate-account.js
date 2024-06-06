document.getElementById("registerForm").addEventListener("submit", function (event) {
  event.preventDefault();

  document.querySelectorAll(".error-message").forEach((el) => (el.style.display = "none"));

  const email = document.getElementById("email").value.trim();
  const password = document.getElementById("password").value.trim();
  const firstName = document.getElementById("firstName").value.trim();
  const lastName = document.getElementById("lastName").value.trim();
  const month = document.getElementById("birthMonth").value;
  const year = document.getElementById("birthYear").value;

  let valid = true;

  if (!validateEmail(email)) {
    valid = false;
    document.getElementById("emailError").style.display = "block";
  }

  if (!validatePassword(password)) {
    valid = false;
    document.getElementById("passwordError").style.display = "block";
  }

  if (!firstName) {
    valid = false;
    document.getElementById("firstNameError").style.display = "block";
  }

  if (!lastName) {
    valid = false;
    document.getElementById("lastNameError").style.display = "block";
  }

  if (!month) {
    valid = false;
    document.getElementById("monthError").style.display = "block";
  }

  if (!year) {
    valid = false;
    document.getElementById("yearError").style.display = "block";
  }

  if (valid) {
    // alert("Form submitted successfully!");
    this.submit();
  }
});

function validateEmail(email) {
  const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return re.test(email);
}

function validatePassword(password) {
  const re = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{8,}$/;
  return re.test(password);
}
