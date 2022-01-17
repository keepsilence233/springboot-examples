package qx.leizige.retry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RetryController {

    @Autowired
    private RetryService retryService;


    @GetMapping(value = "/retry")
    public void retry() throws Exception {
        retryService.callChannel();
    }

}
