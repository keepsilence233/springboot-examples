package qx.leizige.test;

import org.junit.jupiter.api.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import qx.leizige.test.module.Student;

import java.util.List;

/**
 * @author leizige
 * 2022/01/14
 */
public class SpelExpressionTest extends BaseTest {

    /**
     * Spel接口及表达式简单使用
     */
    @Test
    public void test() {

        ExpressionParser parser = new SpelExpressionParser();

        Expression expression1 = parser.parseExpression("'Hello World'");
        String stringValue = (String) expression1.getValue();
        System.out.println("stringValue = " + stringValue);

        //作为调用 JavaBean 属性的示例，可以调用 String 属性"Bytes"
        Expression expression2 = parser.parseExpression("'Hello World'.bytes");   // invokes 'getBytes()'
        byte[] byteValue = (byte[]) expression2.getValue();
        System.out.println("byteValue = " + byteValue);

        //SpEL 还支持使用标准"点"表示法的嵌套属性,即 prop1.prop2.prop3 和属性值的设置
        Expression expression3 = parser.parseExpression("'Hello World'.bytes.length");   // invokes 'getBytes().length'
        Integer lengthValue = (Integer) expression3.getValue();
        System.out.println("lengthValue = " + lengthValue);

        //可以调用String的构造函数,而不是使用字符串文字
        Expression expression4 = parser.parseExpression("new String('Hello World').toUpperCase()");
        String functionValue = expression4.getValue(String.class);
        System.out.println("functionValue = " + functionValue);

        //List列表
        Expression expression5 = parser.parseExpression("{1,2,3,4,5,6,7,8,9,10}");
        @SuppressWarnings("all")
        List<Integer> listValue = expression5.getValue(List.class);
        System.out.println("listValue = " + listValue);

        //List<List> 列表
        Expression expression6 = parser.parseExpression("{{'a','b'},{'c','d'}}");
        List<List> listlOfLists = (List) expression6.getValue(expression6);
        System.out.println("List<List> = " + listlOfLists);

        //三元表达式
        Expression expression7 = parser.parseExpression("2>1 ? 666 : 000");
        Integer value = expression7.getValue(Integer.class);
        System.out.println("value = " + value);

    }

    /**
     * 安全表达式
     */
    @Test
    public void safeOperate() {
        // 防npe写法, xx == null ? null : xx.yy  => xx?.yy
        ExpressionParser parser = new SpelExpressionParser();
        Student student = new Student(null, 18);

        String name = parser.parseExpression("name?.length()").getValue(student, String.class);
        System.out.println("safeOperate-before: " + name);

        student.setName("李四");
        name = parser.parseExpression("name?.length()").getValue(student, String.class);
        System.out.println("safeOperate-after: " + name);
    }


    /**
     * 使用配置的评估上下文，但仍然在每次调用时提供不同的根对象getValue
     */
    @Test
    public void variable1() {
        //指定表达式分析器
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("('Hello' + ' World').concat(#end)");

        //构建表达式评估(求值)上下文环境,也需要指定环境中相关的变量
        EvaluationContext context = new StandardEvaluationContext();    //评估上下文接口
        context.setVariable("end", "!");

        //指定要将表达式评估(求值)结果对象转换成的目标类型
        String value = expression.getValue(context, String.class);
        System.out.println(value);
    }

    /**
     * 同 variable1
     */
    @Test
    public void variable2() {
        ExpressionParser parser = new SpelExpressionParser();
        Student student = new Student("张三", 23);
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("student", student);

        String name = parser.parseExpression("#student.getName()").getValue(context, String.class);
        System.out.println("variable name: " + name);

        Integer age = parser.parseExpression("#student.age").getValue(context, Integer.class);
        System.out.println("variable age: " + age);
    }


    @Test
    public void template() {
        // 模板，混合字面文本与表达式，使用 #{} 将表达式包裹起来
        ExpressionParser parser = new SpelExpressionParser();
        String randomPhrase = parser.parseExpression("random number is #{T(java.lang.Math).random()}",
                ParserContext.TEMPLATE_EXPRESSION).getValue(String.class);
        System.out.println("template: " + randomPhrase);
    }


}
