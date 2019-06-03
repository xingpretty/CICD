package xing.start.game.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xing.start.game.Utils.ImageHash;
import xing.start.game.domain.User;
import xing.start.game.repository.UserRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class IndexController {
    private static final String NAME = "主控制器";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ImageHash imageHash;
//    //全局日志
//    @Autowired
//    Logger LOGGER;

   private final static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/")
    private String index(HttpServletResponse response) throws Exception {

//        User user = new User();
//        user.setName("新来的");
//        user.setCreateDate(new Date());
//        user.setUpdateDate(new Date());
//        userRepository.save(user);
//        return new User().toString();
        LOGGER.debug("homepage redirect");
        response.sendRedirect("/list");
        return "redirect:/list";
    }

    @GetMapping("/list")
    private List list() {
        LOGGER.debug("debuging test");
//        LOGGER.info("列出所有User");
        long start = System.currentTimeMillis();
//        List list = userRepository.findAll();
        Pageable pageable = PageRequest.of(1, 100);
        Page<User> page = userRepository.findAll(pageable);
        long end = System.currentTimeMillis();
        LOGGER.debug((end - start)+"ms");
        return page.getContent();
    }

    @GetMapping("/addDefault")
    private User add() {
        User user = new User();
        user.setName("新来的");
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        userRepository.save(user);
        return user;
    }

    @GetMapping("/addOneHundred")
    private Map addOneHundred(@RequestParam(value = "toadd", required = false) Integer toadd) {
        if (toadd == null) {
            toadd = 100;
        }
        int count = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < toadd; i++) {
            User user = new User();
            user.setName("100-fighters");
            user.setCreateDate(new Date());
            user.setUpdateDate(new Date());
            if (userRepository.save(user) != null) {
                count++;
            }
        }
        long cost = System.currentTimeMillis() - start;
        LOGGER.debug(cost + "ms");
        Map<String,Object> map = new HashMap<>();
        LOGGER.debug("toadd:{}", toadd);
        map.put("count", count);
        map.put("time-cost", cost);
        return map;
    }

    @GetMapping("imageHash")
    public String getImageHash() {
        return imageHash.hashImage();
    }
}
