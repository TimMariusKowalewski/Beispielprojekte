let _topmeldungen = ['&nbsp;<i class="fas fa-exclamation-triangle"></i>&nbsp;&nbsp;<b>28.09.2020:</b> Bombenentschärfung am Essener Hauptbahnhof </span>&nbsp;', '&nbsp;<i class="fas fa-exclamation-triangle"></i>&nbsp;&nbsp;<b>29.09.2020:</b> Start der Ruhrolympiade</span>&nbsp;',
'&nbsp;<i class="fas fa-exclamation-triangle"></i>&nbsp;&nbsp;<b>30.09.2020:</b> Bauarbeiten in Esser U-Bahnnetz</span>&nbsp;'];

let _topmeldungen_idx = 0;

function laTopMeldungOnClick() {
    if(_topmeldungen_idx == 0) {
        _topmeldungen_idx = _topmeldungen.length - 1;
    } else {
        _topmeldungen_idx--;
    }

    _elemTopMeldung.innerHTML = _topmeldungen[_topmeldungen_idx];
    //alert(_topmeldungen_idx);
}

function raTopMeldungOnClick() {
    if(_topmeldungen_idx == _topmeldungen.length - 1) {
        _topmeldungen_idx = 0;
    } else {
        _topmeldungen_idx++;
    }

    _elemTopMeldung.innerHTML = _topmeldungen[_topmeldungen_idx];
    //alert(_topmeldungen_idx);
}

let _laTopMeldung = document.getElementById("TopMeldungLeftArrow");
let _raTopMeldung = document.getElementById("TopMeldungRightArrow");
let _elemTopMeldung = document.getElementById("aktuelleTopMeldung");

_elemTopMeldung.innerHTML = _topmeldungen[_topmeldungen_idx];

_laTopMeldung.addEventListener("click", laTopMeldungOnClick);
_raTopMeldung.addEventListener("click", raTopMeldungOnClick);

setInterval(raTopMeldungOnClick, 3000);