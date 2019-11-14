package ink.icopy.verifycode.example.sort;

import java.util.Arrays;

/**
 * 自定义类要实现Comparable接口
 *
 * @author lizhengguang
 */
public class Student implements Comparable<Student> {
    private int studentId;
    private int studentGeneration;

    public Student(int studentId, int studentGeneration) {
        this.studentId = studentId;
        this.studentGeneration = studentGeneration;
    }

    @Override
    public int compareTo(Student s) {
        int result = this.studentGeneration - s.studentGeneration;
        if (result != 0) {
            return result;
        } else {
            return this.studentId - s.studentId;
        }
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getStudentGeneration() {
        return studentGeneration;
    }

    public void setStudentGeneration(int studentGeneration) {
        this.studentGeneration = studentGeneration;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentGeneration=" + studentGeneration +
                '}';
    }

    public static void main(String[] args) {
        Student[] a = new Student[5];
        a[0] = new Student(75, 2016);
        a[1] = new Student(52, 2019);
        a[2] = new Student(57, 2016);
        a[3] = new Student(220, 2014);
        a[4] = new Student(16, 2018);

        //类实例需要实现自定义Comparable
        Arrays.sort(a);

        System.out.println(Arrays.toString(a));
    }
}
