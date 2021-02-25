<?php
require_once "classes/App.php";
require_once "classes/PageController.php";

//phpinfo();

$App = new App;
$App->setPageController(new PageController);
$App->run();
