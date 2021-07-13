'use strict'

const tokenKey = "token";
const authControllerPathPrefix = "/auth";
const authControllerPathRegistrationSuffix = "/register";
const authControllerPathLoginSuffix = "/login";
const mainPagePath = "/main";

const loginInput = $('#login-input');
const passwordInput = $('#password-input');
const loginForRegistrationInput = $('#login-for-registration-input');
const passwordForRegistrationInput = $('#password-for-registration-input');
const emailInput = $('#email-input');
const firstNameInput = $('#first-name-input');
const lastNameInput = $('#last-name-input');

const signInSignUpSwitcher = () => $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
const redirectToMainPage = () => $.ajax({
    async: false,
    type: "GET",

    url: window.location.origin + mainPagePath,
    success: function (res) {
        $(location).attr('href', window.location.origin + mainPagePath);
    }
});

$('.message a').click(function () {
    signInSignUpSwitcher();
});

$('#login').click(function (ev) {
    ev.preventDefault();

    const requestBody = JSON.stringify({
        login: loginInput.val(),
        password: passwordInput.val()
    });

    $.ajax({
        async: false,
        data: requestBody,
        type: "POST",
        contentType: "application/json",
        url: window.location.origin + authControllerPathPrefix + authControllerPathLoginSuffix,
        success: function (res) {
            const token = `Bearer ${res}`;
            localStorage.setItem(tokenKey, token);

            redirectToMainPage();
        }
    })
});

$('#registration').click(function () {
    const requestBody = JSON.stringify({
        login: loginForRegistrationInput.val(),
        password: passwordForRegistrationInput.val(),
        email: emailInput.val(),
        firstName: firstNameInput.val(),
        lastName: lastNameInput.val()
    });

    $.ajax({
        async: false,
        data: requestBody,
        type: "POST",
        contentType: "application/json",
        url: window.location.origin + authControllerPathPrefix + authControllerPathRegistrationSuffix,
        success: function (res) {
            signInSignUpSwitcher();
        }
    });
});