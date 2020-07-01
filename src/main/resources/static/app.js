var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#gameModel").html("");
}

function connect() {
    $("#namePanel").show();

    var socket = new SockJS('/fiveinarow-example');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/fiveinarow', function (greeting) {
            showGameMessage(JSON.parse(greeting.body).message);
        });
    });
}

function disconnect() {
    stompClient.send("/app/disconnect", {}, JSON.stringify({'name': $("#name").val()}));
    stompClient.subscribe('/topic/fiveinarow', function (greeting) {
        showGameMessage(JSON.parse(greeting.body).message);
    });

    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
    $("#namePanel").hide();
    $("#movePanel").hide();
}

function enterName() {
    $("#namePanel").hide();
    stompClient.send("/app/game", {}, JSON.stringify({'name': $("#name").val()}));
}

function showGameMessage(message) {

    console.log("showGameMessage:");
    console.log(message);

    $("#gameModel").empty().append("<tr><td>" + message + "</td></tr>");

    if(message == "Please enter a player name") {
        $("#namePanel").show();
        $("#movePanel").hide();
    } else if(message.includes("forfeited", 0)) {
        $("#movePanel").hide();
        $("#namePanel").hide();
        $("#conversation").show();
    } else {
        $("#movePanel").show();
    }

    $("#conversation").show();

}

function makeMove() {
    if(isNumeric($("#makeMove").val())) {
        stompClient.send("/app/move", {}, JSON.stringify({'move': $("#makeMove").val(), 'name': $("#name").val()}));
    } else {
        $("#gameModel").append("<br/><tr><td>" + "Please enter a valid move! (0-9)" + "</td></tr>");
    }
}

function isNumeric(num){
    return !isNaN(num)
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#enter" ).click(function() { enterName(); });
    $( "#move" ).click(function() { makeMove(); });
});