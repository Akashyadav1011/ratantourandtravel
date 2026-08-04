document.querySelector('.booking-form')?.addEventListener('submit', function (event) {
    event.preventDefault();
    alert('Your booking has been submitted successfully! We will contact you soon.');
});
setTimeout(function () {
    window.location.href =
        "https://wa.me/919415787325?text=" + encodedMessage;
},7000);