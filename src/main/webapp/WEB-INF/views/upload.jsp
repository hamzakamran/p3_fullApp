<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="/css/style.css">
    <script src="/js/main.js"></script>

    <title>Upload | SoCeRC++</title>
</head>

<body>
    <header class="navBar">
        <div class="container">
            <h1>SoCeRC++</h1>
            <ul>
                <li><a href="home">Search</a></li>
                <li><a class="selected" href="upload">Upload</a></li>
            </ul>
        </div>
    </header>
    <div class="drag-area">
        <div class="icon"><i class="fas fa-cloud-upload-alt"></i></div>
        <div class="header">Drag & Drop to Upload File</div>
        <span>OR</span>
        <button>Browse File</button>
        <input type="file" hidden>
    </div>
    <div id="card">
        <img src="/imgs/cross.png">
        <p>Error: something went wrong</p>
    </div>

    <script>
        const dropArea = document.querySelector(".drag-area"),
            dragText = dropArea.querySelector(".header"),
            button = dropArea.querySelector("button"),
            input = dropArea.querySelector("input");
        let file; //this is a global variable and we'll use it inside multiple functions
        button.onclick = () => {
            input.click(); //if user click on the button then the input also clicked
        }
        input.addEventListener("change", function() {
            //getting user select file and [0] this means if user select multiple files then we'll select only the first one
            file = this.files[0];
            dropArea.classList.add("active");
            showFile(); //calling function
        });
        //If user Drag File Over DropArea
        dropArea.addEventListener("dragover", (event) => {
            event.preventDefault(); //preventing from default behaviour
            dropArea.classList.add("active");
            dragText.textContent = "Release to Upload File";
        });
        //If user leave dragged File from DropArea
        dropArea.addEventListener("dragleave", () => {
            dropArea.classList.remove("active");
            dragText.textContent = "Drag & Drop to Upload File";
        });
        //If user drop File on DropArea
        dropArea.addEventListener("drop", (event) => {
            event.preventDefault(); //preventing from default behaviour
            //getting user select file and [0] this means if user select multiple files then we'll select only the first one
            file = event.dataTransfer.files[0];
            showFile(); //calling function
        });

        function showFile() {
            let fileType = file.name.split(".")[1]; //getting selected file type
            let validExtensions = ["cpp"];
            if (validExtensions.includes(fileType)) {
                let fileReader = new FileReader();
                fileReader.onload = () => {
                    let fileURL = fileReader.result; //passing user file source in fileURL variable
                    //console.log(fileURL);
                    // console.log(fileReader.readAsText(file));

                    attemptToAddFunction(fileURL,  file.name);

                }
                fileReader.readAsText(file);
            } else {
                displayCard("error", "Error: .cpp files only");
                dropArea.classList.remove("active");
                dragText.textContent = "Drag & Drop to Upload File";
            }
        }

        function attemptToAddFunction(fileContent, fileName) {
            let data = {};
            data.fileContents = fileContent;
            data.fileName = fileName;

            //console.log(fileContent);

            	let contextPath = "${pageContext.request.contextPath}";
            	let url = contextPath+ "/addFunction";

            	fetch(url, {
                           		method: "POST",
                           		body: JSON.stringify(data),
                           		headers:{
                           			"Content-Type": "application/json"
                           		}
                           	})
            	.then(httpresponseservlet => {
                    //console.log(httpresponseservlet.json());
                    if (httpresponseservlet.ok) {
                        displayCard("success", "File successfully uploaded");
                        return httpresponseservlet.json();
                    } else {
                        //alert("NO!!!!!!!! Bad Http Status: " + httpresponseservlet.status);
                        displayCard("error", "File Contains Errors");

                    }
                }).then(alertToDisplay => {
                    console.log(alertToDisplay);
                }).catch(error => {
                    //alert("NO!!!!!!! Error = " + error);
                }).finally(() => {

                });
        }
    </script>
</body>

</html>

<style>
    .drag-area {
        border: 2px dashed var(--purple);
        height: 500px;
        width: 700px;
        border-radius: 5px;
        margin: 100px auto;
        display: flex;
        align-items: center;
        justify-content: center;
        flex-direction: column;
        color: var(--purple);
    }

    .drag-area.active {
        border: 2px solid var(--purple);
    }

    .drag-area .icon {
        font-size: 100px;
        color: var(--purple);
    }

    .drag-area header {
        font-size: 30px;
        font-weight: 500;
        color: var(--purple);
    }

    .drag-area span {
        font-size: 25px;
        font-weight: 500;
        color: var(--purple);
        margin: 10px 0 15px 0;
    }

    .drag-area button {
        padding: 10px 25px;
        font-size: 20px;
        font-weight: 500;
        border: none;
        outline: none;
        background: var(--purple);
        color: #fff;
        border-radius: 5px;
        cursor: pointer;
    }

    .drag-area img {
        height: 100%;
        width: 100%;
        object-fit: cover;
        border-radius: 5px;
    }
</style>