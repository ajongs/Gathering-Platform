$(document).ready(function() {
    console.log("Index page is ready");
    $("#createBtn").click(function(){
        connect();
    })

    $("#send").click(function() {
        sendMessage();
    });

});