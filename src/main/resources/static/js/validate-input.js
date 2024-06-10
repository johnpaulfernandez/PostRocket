const textarea = document.querySelectorAll('[name="text"]');
const charCountSpan = document.getElementById("charCount");
const limit = 4000; // Maximum allowed characters

textarea.forEach(text => {
  text.addEventListener("input", function() {
    const currentLength = text.value.length;
    charCountSpan.textContent = `${currentLength} characters`;

    if (currentLength > limit) {
      text.value = text.value.substring(0, limit); // Truncate value
      charCountSpan.textContent = `${limit} characters (limit reached)`;
    }
  });
});
