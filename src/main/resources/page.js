window.onload = function() {
document.querySelector("#submit-button").onclick = function() {
name = document.querySelector("#name-input").value;
document.querySelector("#message").innerHTML = name;
}
}