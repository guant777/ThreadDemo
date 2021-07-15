package thread.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import thread.config.Resp;
import thread.service.AsyncService;


/**
 * @author GuanTao
 * @program: ThreadDemo
 * @description: 测试controller
 * @create: 2021-07-15 11:36
 **/
@RestController
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    /**
     * 多线程测试
     */
    @GetMapping("/async")
    public Resp<?> async(){
        asyncService.executeAsync();
        return Resp.success();
    }
}
