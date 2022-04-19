import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public class Test {


    @org.junit.Test
    public void test() {
        String args = "/opt/idp/rt/bin/flink_first_start.sh /tmp/rt_job/xingyezhengquan-ods_1649223866310.config 1G 4G 1 /opt/flink /tmp/rt_job/logs/xingyezhengquan-ods_1649223866310.log";
        List<String> strings = Arrays.asList(args.split(" "));
        String[] strings1 = strings.toArray(new String[0]);
        System.out.println(strings1);

    }

    @org.junit.Test
    public void lambdaTest() {
        IntStream stream = IntStream.builder().add(1).add(2).add(3).add(3).build();
        //stream.filter(ele -> ele != 2).forEach(System.out::println);
        System.out.println("\n");
        System.out.println(stream.distinct().reduce(Integer::sum).getAsInt());
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @ToString
    class Student {
        String name;
        int age;


    }

    public void getStudentName(Student student) {
        AtomicReference<String> a = new AtomicReference<>();
        System.out.println(Optional.ofNullable(student).map(Student::getName).orElse("unknown"));
        Optional.ofNullable(student).ifPresent(su -> a.set(su.getName()));
        //if (null != student) {
        //    System.out.println(student.getName());
        //} else {
        //    System.out.println("unknown");
        //}
    }

    @org.junit.Test
    public void studentTest() {
        Student aa = new Student("aa", 20);
        Student bb = new Student("bb", 30);
        Student cc = new Student("cc", 35);
        Student dd = null;
        Student[] students = new Student[]{aa, bb, cc, dd};
        Arrays.stream(students)
                .map(su -> Optional
                                .ofNullable(su)
                                .map(Student::getName)
                                .orElse("unknown"))
                .forEach(System.out::println);
    }
}
