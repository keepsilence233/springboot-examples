package qx.leizige.job;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import qx.leizige.config.XxlJobConfig;

/**
 * 商品定时上架job执行器
 */
@Component
public class ItemScheduledShelvesJob {

    private Logger logger = LoggerFactory.getLogger(XxlJobConfig.class);

    /**
     * itemScheduledShelves 需要在任务管理中创建
     *
     * @throws Exception
     */
    @XxlJob("itemScheduledShelves")
    public void execute() throws Exception {
        try {
            logger.info("xxl-job-client execute itemScheduledShelves job ...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
