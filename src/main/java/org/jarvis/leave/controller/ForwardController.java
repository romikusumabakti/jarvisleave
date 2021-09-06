package org.jarvis.leave.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ForwardController {

    @RequestMapping(value={"/{path:[a-z0-9]+}", "/panel/**"})
    public String forward() {
        return "forward:/";
    }
}