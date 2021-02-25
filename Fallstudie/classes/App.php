<?php

class App
{
    private $_id;
    private $_page_controller;

    // properties
    public function getID()
    {
        return $this->_id;
    }

    public function setID($ID)
    {
        $this->_id = $ID;
    }

    public function getPageController()
    {
        return $this->_page_controller;
    }

    public function setPageController($PC)
    {
        $this->_page_controller = $PC;
    }

    // constructors
    public function __construct()
    {
        $this->setID(1);
    }

    // methods
    public function run()
    {
        $this->handleRequest();
    }

    public function handleRequest()
    {
        $_cid = null;

        if (isset($_GET["cid"]) == true) {
            $_cid = $_GET["cid"];
        }
        $this->getPageController()->output($_cid);
    }
}
