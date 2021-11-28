<!DOCTYPE html>
<html lang="en">

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
            <input class="search" placeholder="Enter keywords or requirements..." type="text" onkeydown="search(this)">
            <img class="clear-icon" src="/imgs/exit.png">
        </div>
    </div>
    <div id="resultContainer" class="container hidden">
        <div class="leftContainer">
            <ul id="results"></ul>
        </div>
        <div class="rightContainer">
            <pre>
// display a number
void displayNum(int n1, float n2) {
    cout << "The int number is " << n1;
    cout << "The double number is " << n2;
}
            </pre>
            <img src="/imgs/download.png" alt="Download" class="downloadBtn">
        </div>
    </div>
    <div id="card">
        <img src="/imgs/cross.png">
        <p>Error: something went wrong</p>
    </div>
    <script>
        var fileArray = [];
        document.addEventListener("DOMContentLoaded", () => {
            // populate results

            populateData();


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
            document.querySelector(".downloadBtn").addEventListener("click", () => {
                let res =
                    "// display a number\n" +
                    "void displayNum(int n1, float n2) {\n" +
                    "   cout << \"The int number is \" << n1;\n" +
                    "   cout << \"The double number is \" << n2;\n" +
                    "}";
                download("test.cpp", res);
            });

             // download button event listener
            document.querySelector(".leftContainer").addEventListener("click", (e) => {
                console.log(e.target.innerHTML);
                let functionName = e.target.innerHTML;
                functionName.replace("<h3>", "");
                functionName.replace("</h3>", "");
                console.log(functionName);
                for(let i = 0; i<fileArray.length;i++)
                {
                    if (functionName == fileArray.at(i).fileName)
                    {
                        document.querySelector(".rightContainer").innerHTML = fileArray.at(i).functionContents;
                        break;
                    }
                }
            });
        });

        function populateData() {
            let docs = [];

                        for (let i = 0; i < fileArray.length; i++) {
                            console.log(fileArray.at(i).fileName);
                            docs.push(new Doc(
                                fileArray.at(i).fileName
                            ));
                        }
                        let ul = document.getElementById("results");
                        ul.innerHTML = "";
                        for (let doc of docs) {
                            let li = document.createElement("li");
                            let h3 = document.createElement("h3");

                            h3.appendChild(document.createTextNode(doc.title));
                            li.appendChild(h3);

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



        function searchFunctions(keywords) {
            let data = {};


                let contextPath = "${pageContext.request.contextPath}";
                let url = contextPath+ "/searchFunctions";
                console.log(url);

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
        position: absolute;
        bottom: 10px;
        right: 10px;
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