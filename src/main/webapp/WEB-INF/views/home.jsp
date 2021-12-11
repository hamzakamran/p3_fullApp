


<!DOCTYPE html>
<html lang="en">

<!--
-- AUTHORS --
+ Hamza Kamran
+ Adam Gibbons
+ Kimmy Thach

-- DESCRIPTION: --
This is the home page that contains the search functionality. It can display the results from a search as well as let you enter a new search

-- FUNCTIONAL REQUIREMENT(S) MET: --
+ FR.7 Found in: downloadFile()

-- NONFUNCTIONAL REQUIREMENT(S) MET: --
+ None

-- USER INTERFACE REQUIREMENT(S) MET: --
+ UI.2 Found in: input class="search" placeholder="Enter keywords or requirements..."

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


<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="/css/style.css">
    <script src="/js/main.js"></script>
    <script src="/js/Document.js"></script>

    <title>Search | SoCeRC++</title>
</head>

<body>
    <header class="navBar">
        <div class="container">
            <h1>SoCeRC++</h1>
            <ul>
                <li><a class="selected" href="home">Search</a></li>
                <li><a href="upload">Upload</a></li>
            </ul>
        </div>
    </header>
    <div class="container">
        <div class="wrapper">
            <img class="search-icon" src="/imgs/search.png">
            <!-- UI 2 SoCeRC shall have a searchbar to take in user queries-->
            <input class="search" placeholder="Enter keywords or requirements..." type="text" onkeydown="search(this)">
            <img class="clear-icon" src="/imgs/exit.png">
        </div>
    </div>
    <div id="resultContainer" class="container hidden">
        <div class="leftContainer">
            <ul id="results"></ul>
        </div>
        <div class="rightContainer">
            <pre id="preTagId"></pre>
            <img src="/imgs/download.png" alt="Download" class="downloadBtn" id="downloader" >
        </div>
    </div>
    <div id="card">
        <img src="/imgs/cross.png">
        <p>Error: something went wrong</p>
    </div>
    <script>
        var fileArray = [];
        var currentFunctionId;
        document.addEventListener("DOMContentLoaded", () => {
            // populate results

            // search bar
            const clearIcon = document.querySelector(".clear-icon");
            const searchBar = document.querySelector(".search");
            document.querySelector(".search").addEventListener("keyup", () => {
                if (searchBar.value && clearIcon.style.visibility != "visible") {
                    clearIcon.style.visibility = "visible";
                } else if (!searchBar.value) {
                    clearIcon.style.visibility = "hidden";
                }
            });
            document.querySelector(".clear-icon").addEventListener("click", () => {
                searchBar.value = "";
                clearIcon.style.visibility = "hidden";
                document.getElementById("resultContainer").classList.add("hidden");
            });

            // download button event listener
            document.getElementById("downloader").addEventListener("click", () => {
               let res = "";

               for(let i = 0; i<fileArray.length;i++)
               {
                   let formattedCode = [];
                   formattedCode = fileArray.at(i).fileContents.split("\r\n");
                   //console.log(formattedCode);
                   if (currentFunctionId == fileArray.at(i).functionId)
                   {
                        for (let j = 0; j<formattedCode.length; j++)
                        {
                            res += formattedCode + "\r\n";
                        }

                   }
               }
               download("test.cpp", res);


            });

             // download button event listener
            document.querySelector(".leftContainer").addEventListener("click", (e) => {
                console.log(e.target.id);
                let functionId = e.target.id;
                currentFunctionId = functionId;

                //console.log(functionName);
                for(let i = 0; i<fileArray.length;i++)
                {
                    let formattedCode = [];
                    formattedCode = fileArray.at(i).functionContents.split("\r\n");
                    //console.log(formattedCode);
                    if (functionId == fileArray.at(i).functionId)
                    {
                        console.log(fileArray);
                        document.querySelector(".rightContainer").innerHTML = "";
                        for(let i = 0; i<formattedCode.length;i++){


                            document.querySelector(".rightContainer").innerHTML = document.querySelector(".rightContainer").innerHTML +  "<pre>" + formattedCode.at(i) + "</pre>";

                        }

                        document.querySelector(".rightContainer").innerHTML = document.querySelector(".rightContainer").innerHTML + "<img src=\"/imgs/download.png\" alt=\"Download\" class=\"downloadBtn\" id=\"downloader\" onclick=\"downloadFile()\">";
                        break;
                    }
                }
            });
        });

        function populateData() {
            let docs = [];
                        if (fileArray.length == 0)
                        {
                            displayCard("error", "No matching functions!");
                        }
                        for (let i = 0; i < fileArray.length; i++) {
                            //console.log(fileArray.at(i).functionName);
                            docs.push(new Doc(
                                fileArray.at(i).functionName,
                                fileArray.at(i).functionDescription,
                                fileArray.at(i).fileName,
                                1,
                                fileArray.at(i).functionId
                            ));
                        }
                        console.log(fileArray);
                        let ul = document.getElementById("results");
                        ul.innerHTML = "";
                        for (let doc of docs) {
                            let li = document.createElement("li");
                            let h3 = document.createElement("h3");
                            let p1 = document.createElement("p");
                            let p2 = document.createElement("p");
                            let b1 = document.createElement("br");

                            h3.setAttribute("id", doc.functionId);

                            h3.appendChild(document.createTextNode(doc.title));
                            li.appendChild(h3);

                            p2.appendChild(document.createTextNode(doc.fileName));
                            li.appendChild(p2);
                            li.appendChild(b1);


                            p1.appendChild(document.createTextNode(doc.description));
                            li.appendChild(p1);




                            ul.appendChild(li);
                        }
        }

        // search bar event listener
        function search(e) {
            if (event.key === 'Enter') {
                if (e.value.length > 0) {
                    fileArray = [];
                    searchFunctions(e.value);
                    document.getElementById("resultContainer").classList.remove("hidden");
                } else {
                    displayCard("error", "Error: input cannot be blank");
                }
            }
        }

        //FR.7 SoCeRC++ shall allow users to download a function’s original file.
        function downloadFile()
        {
            let res = "";
            let functionName = "";
           console.log("download CLicked");
           for(let i = 0; i<fileArray.length;i++)
           {
               let formattedCode = [];
               formattedCode = fileArray.at(i).fileContents.split("\r\n");
               //console.log(formattedCode);
               if (currentFunctionId == fileArray.at(i).functionId)
               {
                    for (let j = 0; j<formattedCode.length; j++)
                    {
                        res += formattedCode[j] + "\r\n";
                        fileName = fileArray.at(i).fileName;
                    }

               }
           }
           download(fileName, res);
        }


        function searchFunctions(keywords) {
            let data = {};


                let contextPath = "${pageContext.request.contextPath}";
                let url = contextPath+ "/searchFunctions";
                //console.log(url);

                fetch(url, {
                                method: "POST",
                                body: JSON.stringify(keywords),
                                headers:{
                                    "Content-Type": "application/json"
                                }
                            })
                .then(httpresponseservlet => {
                    if (httpresponseservlet.ok) {
                        return httpresponseservlet.json();
                    } else {
                        //alert("NO!!!!!!!! Bad Http Status: " + httpresponseservlet.status);

                    }
                }).then(answer => {
                   console.log(answer);
                   fileArray = answer;
                   populateData();

                }).catch(error => {
                    //alert("NO!!!!!!! Error = " + error);

                }).finally(() => {

                });
        }


    </script>
</body>

</html>

<style>
    #resultContainer {
        width: 100%;
        margin-top: 50px;
    }

    .leftContainer {
        display: block;
        width: 50%;
        height: 400px;
        float: left;
    }

    .leftContainer::after {
        clear: left;
    }

    .rightContainer {
        position: relative;
        display: block;
        width: 50%;
        height: 400px;
        box-sizing: border-box;
        padding: 20px;
        float: right;
        background: var(--light-purple);
        overflow: scroll;
    }

    .rightContainer::after {
        clear: right;
    }

    .rightContainer pre {
        font-size: 22px;
        color: white;
        font-style: monospace !important;
    }

    .downloadBtn {
        display: block;
        justify-content: flex-end;
        display: flex;
        float: right;
        width: 40px;
    }

    .downloadBtn:hover {
        cursor: pointer;
    }

    #resultContainer ul {
        width: 100%;
        height: 100%;
        overflow: scroll;
        color: white;
    }

    #resultContainer li {
        list-style: none;
        box-sizing: border-box;
        padding: 10px 50px;
    }

    #resultContainer li:nth-child(even) {
        background: var(--purple);
    }

    #resultContainer li:nth-child(odd) {
        background: var(--dark-purple);
    }

    #resultContainer li:hover {
        cursor: pointer;
    }

    #resultContainer li h3 {
        margin-bottom: 10px;
    }

    .wrapper {
        position: relative;
        display: flex;
        min-width: 100px;
        margin-top: 50px;
    }

    .search {
        border: 2px solid var(--purple);
        color: var(--purple);
        border-radius: 5px;
        height: 50px;
        width: 100%;
        padding: 5px 70px;
        outline: 0;
        background-color: none;
        font-size: 24px;
        transition: .5s;
    }

    .search::placeholder {
        color: var(--purple);
    }

    .search-icon {
        position: absolute;
        top: 8px;
        left: 8px;
        width: 40px;
    }

    .clear-icon {
        position: absolute;
        top: 9px;
        right: 8px;
        width: 40px;
        cursor: pointer;
        visibility: hidden;
    }

    .search:hover,
    .search:focus {
        border: 2px solid var(--purple);
    }
</style>