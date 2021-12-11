

<!DOCTYPE html>
<html lang="en">

<!--
-- AUTHORS --
+ Hamza Kamran
+ Adam Gibbons
+ Kimmy Thach

-- DESCRIPTION: --
This is the file that shows the upload page. It will process any file that the user uploads and will pass that data to the java code


-- FUNCTIONAL REQUIREMENT(S) MET: --
+ FR.5 Found in: attemptToAddFunction
+ FR.6 Found in: attemptToAddFunction

-- NONFUNCTIONAL REQUIREMENT(S) MET: --
+ None

-- USER INTERFACE REQUIREMENT(S) MET: --
+ UI.1 Found in: This File

-- SOFTWARE INTERFACE REQUIREMENT(S) MET: --
+ None

-- LOGICAL DATABASE REQUIREMENT(S) MET: --
+ None

--------------------------------------

The MIT License (MIT)

Copyright (c) 2021 OpenFin

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
-->


<!-- UI 1 SoCeRC shall have an upload page to process uploaded files-->
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

    <div class="critArea">
        <h3 class="criteria"> Acceptance Criteria for Uploaded Files</h3>
          <ul class="criteria-list">
            <li>Uploaded documents must end with the .cpp file extension.</li>
            <li>Uploaded C++ documents must follow proper syntax and contain no syntax errors.</li>
            <li>Files must be standalone files (cannot contain dependencies from other files).</li>
            <li>Function calls are expected to consist of one line (ex: funcEx(int x, int y).</li>
            <li>Braces are expected are loops (including single line loops).</li>
            <li>Naming conventions cannot be ambiguous (ex: int score = 4 is preferred over int x = 4).</li>
          </ul>
          </div>


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
        var functionsThatWereNotAdded;
        const dropArea = document.querySelector(".drag-area"),
            dragText = dropArea.querySelector(".header"),
            button = dropArea.querySelector("button"),
            input = dropArea.querySelector("input");
        let file = []; //this is a global variable and we'll use it inside multiple functions
        let fileDataToAdd = [];
        button.onclick = () => {
            input.click(); //if user click on the button then the input also clicked
        }
        input.addEventListener("change", function() {
            //getting user select file and [0] this means if user select multiple files then we'll select only the first one
            file = this.files;
            dropArea.classList.add("active");
            showFile(); //calling function

            setTimeout(function() { attemptToAddFunction(); }, 10);
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
            file = event.dataTransfer.files;
            console.log(file);
            showFile(); //calling function

            setTimeout(function() { attemptToAddFunction(); }, 10);

        });

        function showFile() {
            fileDataToAdd = [];
            for (let i=0; i<file.length; i++) {
                let fileType = file[i].name.split(".")[1]; //getting selected file type
                let validExtensions = ["cpp"];
                if (validExtensions.includes(fileType)) {
                    let fileReader = new FileReader();
                    fileReader.onload = () => {
                        let fileURL = fileReader.result; //passing user file source in fileURL variable
                        //console.log(fileURL);
                        //console.log(fileName);

                        let singleFileData = {};
                        singleFileData.fileURL = fileURL;
                        singleFileData.fileName = file[i].name;

                        fileDataToAdd.push(singleFileData);
                    }
                    fileReader.readAsText(file[i]);
                } else {
                    displayCard("error", "Error: .cpp files only");
                    dropArea.classList.remove("active");
                    dragText.textContent = "Drag & Drop to Upload File";
                }

            }

        }


        function attemptToAddFunction() {

            let data = fileDataToAdd;


               console.log(data);


            	let contextPath = "${pageContext.request.contextPath}";
            	let url = contextPath+ "/addFunction";

            	fetch(url, {
                           		method: "POST",
                           		body: JSON.stringify(fileDataToAdd),
                           		headers:{
                           			"Content-Type": "application/json"
                           		}
                           	})
            	.then(httpresponseservlet => {
                    //console.log(httpresponseservlet.json());
                    if (httpresponseservlet.ok) {
                        // FR 5 notify user if file was successfully uploaded

                        return httpresponseservlet.json();
                    } else {
                        //alert("NO!!!!!!!! Bad Http Status: " + httpresponseservlet.status);
                        // FR 6 notify user if file was not successfully uploaded
                        displayCard("error", "Error with Files");

                    }
                }).then(alertToDisplay => {
                    functionsThatWereNotAdded = alertToDisplay;
                    displayErrorMessages();
                    console.log(alertToDisplay);
                }).catch(error => {
                    //alert("NO!!!!!!! Error = " + error);
                }).finally(() => {

                });
        }

        function displayErrorMessages()
        {
            if(functionsThatWereNotAdded.length == 0)
            {
                displayCard("success", "File(s) successfully uploaded");
            }
            else if (functionsThatWereNotAdded.length == 1){
                displayCard("error", functionsThatWereNotAdded[i] + " contains errors");

            }
            else {
                 displayCard("error", functionsThatWereNotAdded.length + " files contain errors");

            }
        }
    </script>
</body>

</html>

<style>
    .critArea {

        height: px;
        width: 700px;
        border-radius: 5px;
        margin: 10px auto;
        margin-bottom: -70px;
        display: flex;
        align-items: center;
        justify-content: center;
        flex-direction: column;
        color: var(--purple);
    }


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