package qx.leizige.handler;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import qx.leizige.event.StudentEvent;
import qx.leizige.module.Student;

import java.util.List;

/**
 * event事件监听处理
 */
@Slf4j
//@Component
public class StudentEventHandler {

	/**
	 * public class StudentEventHandler implements ApplicationListener<T> 可以通过实现ApplicationListener类来监听,泛型为事件类型
	 * 也可以通过 @EventListener 注解来监听，入参为事件类型
	 */


//    @EventListener

	/**
	 * 可以绑定到以下事务阶段：
	 * AFTER_COMMIT（默认）用于在事务成功完成时触发事件。
	 * AFTER_ROLLBACK – 如果事务已回滚
	 * AFTER_COMPLETION – 如果事务已完成（AFTER_COMMIT 和 AFTER_ROLLBACK 的别名）
	 * BEFORE_COMMIT 用于在事务提交之前触发事件。
	 */
	//只有当事件生产者正在运行并且即将提交的事务时，才会调用此侦听器。
	//如果没有事务正在运行，则根本不会发送事件，除非我们通过将 fallbackExecution 属性设置为 true 来覆盖它
//	@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
	public void onStudentStatusEnable(StudentEvent.Enable event) {
		List<Student> source = event.getSource();
		log.info("source : {}", JSON.toJSONString(source));
		source.forEach(v -> {
			//模拟修改状态
			v.setStatus(10);
		});
		log.info("target : {}", JSON.toJSONString(source));
	}

//	@EventListener
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
