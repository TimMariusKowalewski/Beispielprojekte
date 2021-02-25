<?php

class MySQL {
    private $link;

    function connect() {
        $this->link = mysqli_connect("localhost", "root", "", "test") or die("Keine Verbindung möglich: " . mysqli_error($this->link));
    }

    function disconnect() {
        mysqli_close($this->link);
    }

    function query($SQL) {
        return mysqli_query($this->link, $SQL); // or die("Anfrage fehlgeschlagen: " . mysqli_error($this->link));
    }

}

$_mysql = new MySQL;
$_mysql->connect();
$_result = $_mysql->query("SELECT * FROM test1");

while ($line = mysqli_fetch_array($_result, MYSQLI_ASSOC)) {
    foreach ($line as $line) {
        echo $line;
    }
}

$_mysql->disconnect();  