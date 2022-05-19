package qx.leizige;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit4.SpringRunner;

import qx.leizige.event.StudentEvent;
import qx.leizige.event.generic.GenericSpringEvent;
import qx.leizige.module.Student;

import java.util.List;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTest {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	private List<Student> source;

	@Before
	public void before() {
		this.source = Student.build();
	}


	@Test
	public void onStudentStatusEnable() {
		//业务代码
//        applicationEventPublisher.publishEvent(new StudentEvent.Enable(source));
		applicationEventPublisher.publishEvent(new GenericSpringEvent<List<Student>>(source, true));
		//业务代码
	}


	@Test
	public void onStudentStatusDisabled() {
		//业务代码
		applicationContext.publishEvent(new StudentEvent.Disabled(source));
		//业务代码
	}
}
