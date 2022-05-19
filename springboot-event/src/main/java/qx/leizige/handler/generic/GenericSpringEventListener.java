package qx.leizige.handler.generic;

import java.util.List;

import javax.validation.constraints.NotNull;

import qx.leizige.event.generic.GenericSpringEvent;
import qx.leizige.module.Student;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


/**
 * @author leizige
 */
public class GenericSpringEventListener {

	//	@Component
	public static class GenericSpringStudentEventListener implements ApplicationListener<GenericSpringEvent<Student>> {
		@Override
		public void onApplicationEvent(@NotNull GenericSpringEvent<Student> event) {
			System.out.println("Received spring generic event - " + event.getWhat());
		}
	}


	@Component
	public static class AnnotationDrivenEventListener {
		@EventListener(condition = "#event.success")
		public void handleSuccessful(GenericSpringEvent<List<Student>> event) {
			System.err.println("Handling generic event (conditional).");
		}
	}

}
