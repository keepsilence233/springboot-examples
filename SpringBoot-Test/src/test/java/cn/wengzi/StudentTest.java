package cn.wengzi;

import cn.wengzi.entity.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @author wengzi
 * @date 2019/11/9 night 22:25
 * @description 要让一个普通类变成一个单元测试类只需要在类名上加入 @SpringBootTest 和 @RunWith(SpringRunner.class) 两个注释即可。
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentTest {

    /**
     * 如果测试需要做 REST 调用，可以 @Autowire 一个 TestRestTemplate
     */
    @Autowired
    private TestRestTemplate testRestTemplate;

    /**
     * @LocalServerPort 提供了 @Value("${local.server.port}") 的代替,随机生成一个端口
     */
    @LocalServerPort
    private int port;

    private URL base;

    @Before
    public void before() throws MalformedURLException {
        String url = String.format("http://127.0.0.1:%d", port);
        //System.out.println(String.format("port is : [%d]", port));
        this.base = new URL(url);
    }

    /**
     * get请求测试
     *
     * @throws Exception
     */
    @Test
    public void get() throws Exception {
        List students = testRestTemplate.getForEntity(this.base.toString() + "/student/queryStudentAll", List.class, "").getBody();
        for (Object student : students) {
            System.out.println(student);
        }

    }

    /**
     * get 带参数请求测试
     *
     * @throws Exception
     */
    @Test
    public void get2() throws Exception {
        Student student = testRestTemplate.getForEntity(this.base.toString() + "/student/queryStudentById/{id}", Student.class, 1).getBody();
        System.out.println(student);
    }

    @Test
    public void post() throws Exception {
        Student student = new Student(6L, "qq", 26);
        testRestTemplate.postForEntity(this.base.toString() + "/student/save", student, Student.class).getBody();

        get();
    }

    @Test
    public void put() throws Exception {
        Student student = new Student(1L, "qq", 26);
        testRestTemplate.put(this.base.toString() + "/student/upd", student);

        get();
    }

    @Test
    public void delete() throws Exception {
        testRestTemplate.delete(this.base.toString() + "/student/deleteById/{id}", 3);

        get();
    }

    @After
    public void after() {
        System.err.println("结束测试...");
    }
}
