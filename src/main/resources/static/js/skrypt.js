function myFunction() {
    // Declare variables
    var input, filter, table, tr, td, i;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");

    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; tr.length>i ; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        if (td) {
            if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

if (window.self !== window.top && window.name!="view1") {;
    window.alert = function(){/*disable alert*/};
    window.confirm = function(){/*disable confirm*/};
    window.prompt = function(){/*disable prompt*/};
    window.open = function(){/*disable open*/};
}

// prevent href=# click jump
document.addEventListener("DOMContentLoaded", function() {
    var links = document.getElementsByTagName("A");
    for(var i=0; i < links.length; i++) {
        if(links[i].href.indexOf('#')!=-1) {
            links[i].addEventListener("click", function(e) {
                console.debug("prevent href=# click");
                if (this.hash) {
                    if (this.hash=="#") {
                        e.preventDefault();
                        return false;
                    }
                    else {
                        /*
                         var el = document.getElementById(this.hash.replace(/#/, ""));
                         if (el) {
                         el.scrollIntoView(true);
                         }
                         */
                    }
                }
                return false;
            })
        }
    }
}, false);