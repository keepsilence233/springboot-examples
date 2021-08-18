package qx.leizige.handler;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import qx.leizige.event.StudentEvent;
import qx.leizige.module.Student;

import java.util.List;

/**
 * event事件监听处理
 */
@Slf4j
@Component
public class StudentEventHandler {

    /**
     * public class StudentEventHandler implements ApplicationListener<T> 可以通过实现ApplicationListener类来监听,泛型为事件类型
     * 也可以通过 @EventListener 注解来监听，入参为事件类型
     */


    @EventListener
    public void onStudentStatusEnable(StudentEvent.Enable event) {
        List<Student> source = event.getSource();
        log.info("source : {}", JSON.toJSONString(source));
        source.forEach(v -> {
            //模拟修改状态
            v.setStatus(10);
        });
        log.info("target : {}", JSON.toJSONString(source));
    }

    @EventListener
    public void onStudentStatusDisabled(StudentEvent.Disabled event) {
        List<Student> source = event.getSource();
        log.info("source : {}", JSON.toJSONString(source));
        source.forEach(v -> {
            //模拟修改状态
            v.setStatus(20);
        });
        log.info("target : {}", JSON.toJSONString(source));
    }

}
