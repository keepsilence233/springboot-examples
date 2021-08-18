package qx.leizige.event;

import qx.leizige.module.Student;

import java.util.List;

/**
 * 自定义event
 */
public class StudentEvent {

    /* status = 10*/
    public static class Enable extends BaseSpringEvent<List<Student>> {
        public Enable(List<Student> source) {
            //可以做点事情
            super(source);
            //可以做点事情
        }
    }

    /* status = 20*/
    public static class Disabled extends BaseSpringEvent<List<Student>> {
        public Disabled(List<Student> source) {
            super(source);
        }
    }

}
