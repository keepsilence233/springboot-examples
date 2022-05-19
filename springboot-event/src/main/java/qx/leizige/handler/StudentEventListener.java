package qx.leizige.handler;

import java.util.List;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import qx.leizige.event.StudentEvent;
import qx.leizige.module.Student;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * event事件监听处理
 */
@Slf4j
public class StudentEventListener {

//	@Component
	public static class StudentEventEnableListener implements ApplicationListener<StudentEvent.Enable> {
		@Override
		public void onApplicationEvent(StudentEvent.Enable event) {
			List<Student> source = event.getSource();
			log.info("source : {}", JSON.toJSONString(source));
			source.forEach(v -> {
				//模拟修改状态
				v.setStatus(10);
			});
			log.info("target : {}", JSON.toJSONString(source));
		}
	}


//	@Component
	public static class StudentEventDisableListener implements ApplicationListener<StudentEvent.Disabled> {
		@Override
		public void onApplicationEvent(StudentEvent.Disabled event) {
			List<Student> source = event.getSource();
			log.info("source : {}", JSON.toJSONString(source));
			source.forEach(v -> {
				//模拟修改状态
				v.setStatus(20);
			});
			log.info("target : {}", JSON.toJSONString(source));
		}
	}
}
