var stompClient = null;

$(document).ready(function() {
    console.log("Index page is ready");
    $("#connect").click(function(){
        connect();
    })

    $("#send").click(function() {
        sendMessage();
    });

});

let token = sessionStorage.getItem('access_token')
function getNickname(){
    fetch('http://localhost:8080/user/nickname',{
        method:"GET",
        headers: {
            "Content-Type":"application/json;charset=utf-8",
            "Authorization": token
        },
    }).then(response =>response.json())
        .then(response => {
        sessionStorage.setItem('user', response.nickname);
        console.log(response.nickname);
    });
}
let headers = {Authorization: token}

function connect() {
    var socket = new SockJS('/ws/stomp/chat');
    stompClient = Stomp.over(socket);

    getNickname();
    var user = sessionStorage.getItem('user');
    stompClient.connect(headers, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/sub/messages/'+user, function (message) {
            //showMessage(message.body);
            showMessage2(message);
        });
    });
}

function showMessage(message) {
    $("#messages").append("<tr><td>" + message + "</td></tr>");
}
function showMessage2(message){
    $("#messages").append("<tr><td>"+message.headers.sender+ " : " + message.body + "</td></tr>");
}
function sendMessage() {
    console.log("sending message");
    stompClient.send("/pub/message", {Authorization: token}, JSON.stringify({'content': $("#message").val(),
    'receiver':$("#receiver").val()}));
}

