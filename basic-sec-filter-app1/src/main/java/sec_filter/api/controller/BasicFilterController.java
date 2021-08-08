package sec_filter.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BasicFilterController {


        @GetMapping("test/")
        @ResponseBody
        public String test(){
            return "basic security filter test";
        }


}
