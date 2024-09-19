package ru.arkhipov.MyFirstTestAppSpringBoot.hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class HelloController {
    ArrayList<String> mass = new ArrayList<String>();
    HashMap<Integer,String> map = new HashMap<>();

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name){
        return String.format("Hello, %s!", name);
    }
    @GetMapping("/update-array")
    public void updateArrayList(@RequestParam(value = "s", defaultValue = "One") String s){
        mass.add(s);
    }
    @GetMapping("/show-array")
    public String showArrayList(){
        return String.format(mass.toString());
    }
    @GetMapping("/update-map")
    public void updateHashMap(@RequestParam(value = "index", defaultValue = "Two") int index, @RequestParam(name = "param", defaultValue = "Three") String param){
        map.put(index,  param);
    }
    @GetMapping("/show-map")
    public String showHashMap(){
        return String.format(map.toString());
    }
    @GetMapping("/show-all-lenght")
    public String showAllLeght(){
        return String.format("Leght mass %s and map %s", mass.size(), map.size());
    }
}
