package qx.leizige.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import qx.leizige.Application;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class FailTemplateTest {

    @Autowired
    private Configuration configuration;

    /**
     * 通过freemarker写一个java文件到本地然后通过类加载执行后调用方法执行
     * @throws IOException
     * @throws TemplateException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws URISyntaxException
     */
    @Test
    public void test() throws IOException, TemplateException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, URISyntaxException {
        Map<String, String> dataModel = new HashMap<>();
        dataModel.put("className", "HelloWorld");
        //加载模板
        Template template = configuration.getTemplate("${className}Test.java");

        PrintWriter writer = new PrintWriter("/Users/chinese.youth/temp1/HelloWorldTest.java");
        template.process(dataModel, writer);

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, "/Users/chinese.youth/temp1/HelloWorldTest.java");

        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        URL classes = new URL("file:///Users/chinese.youth/temp1/");
        ClassLoader custom = new URLClassLoader(new URL[]{classes}, systemClassLoader);

        // this class should be loaded from your directory
        Class<?> clazz = custom.loadClass("HelloWorldTest");
        Object obj = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("add");
        method.invoke(obj, null);

    }

}
