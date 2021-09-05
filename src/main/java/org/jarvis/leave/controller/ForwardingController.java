package org.jarvis.leave.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ForwardingController {

    @RequestMapping(value={"/{path:[a-z0-9]+}"})
    public String forward() {
        return "forward:/";
    }

    @RequestMapping(value={"/panel/**"})
    public String forward2() {
        return "forward:/";
    }
}