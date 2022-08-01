package vttp2002.ssf.day15_redis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = { "/", "" })
public class GreetingsController {

  @Autowired
  private RedisTemplate<String, Object> redisTemplate;

  @GetMapping
  public String getGreetings(Model model) {
    ValueOperations<String, Object> ops = redisTemplate.opsForValue();
    Object greetings = ops.get("greetings");
    model.addAttribute("hello", greetings.toString());
    return "index";
  }

  // @GetMapping
  // public String getGreetings(Model model) {
  // String hello = "hello, world";
  // model.addAttribute("hello", hello);
  // return "index";
  // }

  @PostMapping
  String postGreeting(@RequestBody MultiValueMap<String, String> form, Model model) {
    String hello = form.getFirst("hello");
    model.addAttribute("hello", hello);
    return "index";
  }

}
