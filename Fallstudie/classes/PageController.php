<?php

class PageController
{
    public function output($CID)
    {
        // load master template
        $_master_tpl = file_get_contents("tpls/index.html");

        // load header
        $_header_tpl = file_get_contents("tpls/header.html");

        $_master_tpl = str_replace("###HEADER###", $_header_tpl, $_master_tpl);

        // load main
        if ($CID != null) {
            $_main_tpl = file_get_contents("tpls/article.html");
        } else {
            $_main_tpl = file_get_contents("tpls/main.html");
        }
        $_master_tpl = str_replace("###MAIN###", $_main_tpl, $_master_tpl);

        // load footer
        $_footer_tpl = file_get_contents("tpls/footer.html");
        $_master_tpl = str_replace("###FOOTER###", $_footer_tpl, $_master_tpl);

        // output to browser
        print $_master_tpl;
    }
}
