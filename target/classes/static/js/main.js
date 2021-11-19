document.addEventListener("DOMContentLoaded", () => {

});

function displayCard(messageType, text) {
    var card = document.getElementById("card");
    card.className = "show";
    card.getElementsByTagName("p")[0].innerHTML = text;
    let img = card.getElementsByTagName("img")[0];
    if (messageType == "error") {
        img.src = "/imgs/cross.png";
    } else {
        img.src = "/imgs/check.png";
    }
    setTimeout(function() {
        card.className = card.className.replace("show", "");
    }, 5000);
}

function download(filename, text) {
    let element = document.createElement('a');
    element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(text));
    element.setAttribute('download', filename);
    element.style.display = 'none';
    document.body.appendChild(element);
    element.click();
    document.body.removeChild(element);
}