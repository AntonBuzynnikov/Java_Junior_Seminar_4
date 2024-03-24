package homeworkHiber.store.repositories;

import homeworkHiber.store.entities.StudentEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private final List<StudentEntity> students;
    private final SessionFactory sessionFactory;

    public StudentRepository(SessionFactory sessionFactory) {
        this.students = new ArrayList<>();
        this.sessionFactory = sessionFactory;
    }
    public List<StudentEntity> getAllStudentsByAge(int age){
        try(Session session = sessionFactory.openSession()) {
            Query<StudentEntity> query = session.createQuery("SELECT s FROM student s WHERE age > :age",
                    StudentEntity.class);
            query.setParameter("age", age);
            students.addAll(query.getResultList());

            return students;
        }
    }
    public void insertStudent(StudentEntity student){
        try(Session session = sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            session.persist(student);
            tx.commit();
        }
    }
    public void removeStudent(int id){
        try(Session session = sessionFactory.openSession()){
            StudentEntity student = session.find(StudentEntity.class,id);
            System.out.println(student);

            Transaction tx = session.beginTransaction();
            session.remove(student);
            tx.commit();
        }
    }
    public void setStudentAge(int id, int age){
        try(Session session = sessionFactory.openSession()){
            StudentEntity student = session.find(StudentEntity.class, id);
            Transaction tx = session.beginTransaction();
            student.setAge(age);
            tx.commit();
        }
    }
    public StudentEntity findById(int id){
        try(Session session = sessionFactory.openSession()){
            StudentEntity student = session.find(StudentEntity.class,id);
            return student;
        }
    }


}
