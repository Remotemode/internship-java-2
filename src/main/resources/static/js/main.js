'use strict'

const tokenKey = "token";
const wsSuffix = "/wss";
const initialTable = "/initial";
const authControllerPathPrefix = "/auth";
const authControllerPathLoginSuffix = "/logout";
const table = document.querySelector('.table-content');
const lastPriceSuffix = "_lstPrc";
const startPriceSuffix = "_strtPrc";

let ws;
let stompClient;
let symbolArr;

const redirectToStartPage = () => $.ajax({
    async: false,
    type: "GET",

    url: window.location.origin,
    success: function (res) {
        $(location).attr('href', window.location.origin);
    }
});

$('#getRates').click(function (e) {
    e.preventDefault();

    $.ajax({
        async: false,
        type: "GET",
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        url: window.location.origin + initialTable,
        success: function (res) {
            symbolArr = res;
            table.innerHTML = '';
            res.forEach(el => {
                table.innerHTML +=
                    `<tr id="${el.symbolUnit}" >
             <td>${el.symbolName}</td>
             <td>${el.symbolUnit}</td>
             <td id="${el.symbolName + "_strtPrc"}">${el.startPrice}</td>
             <td id="${el.symbolName + "_lstPrc"}">${el.lastPrice}</td>
                    </tr>`;
            });
            subscribeToChannel();
        }
    });
})

function subscribeToChannel() {
    ws = new SockJS(wsSuffix);
    stompClient = Stomp.over(ws);
    stompClient.connect({}, function (frame) {

        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/updateRates', function (res) {

            const elements = JSON.parse(res.body);
            elements.forEach(el => {
                const cellToUpdate = document.getElementById(`${el.symbolName + lastPriceSuffix}`);
                const oldValue = parseInt(cellToUpdate.innerText);
                const newValue = parseInt(el.lastPrice);

                if (newValue < oldValue) {
                    cellToUpdate.style.color = "#8B0000";
                } else if (newValue > oldValue) {
                    cellToUpdate.style.color = "#2E8B57";
                }

                const rowToUpdate = document.getElementById(`${el.symbolUnit}`);
                const firstPrice = document.getElementById(`${el.symbolName + startPriceSuffix}`).innerText;

                if (newValue < firstPrice) {
                    rowToUpdate.style.backgroundColor = "#FFC0CB";
                } else if (newValue > firstPrice) {
                    rowToUpdate.style.backgroundColor = "#ADFF2F";
                }

                cellToUpdate.innerText = newValue;
            })
        });
    });
}

$('#logout').click(function () {
    $.ajax({
        async: false,
        type: "GET",
        headers: {
            "Authorization": localStorage.getItem(tokenKey)
        },
        contentType: "application/json",
        url: window.location.origin + authControllerPathPrefix + authControllerPathLoginSuffix,
        success: function (res) {
            disconnect();
            redirectToStartPage();
        }
    });
});

function disconnect() {
    try {
        if (typeof ws !== 'undefined') {
            ws = null;
            console.log("Ws was disconnected")
        }
    } catch (e) {
        console.log("Unexpected error occurred disconnecting from WS", e);
    }
}