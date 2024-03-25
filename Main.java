package homeworkHiber;

import homeworkHiber.store.entities.StudentEntity;
import homeworkHiber.store.repositories.StudentRepository;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            StudentRepository studentRepository = getStudentRepository(sessionFactory);
            System.out.println(studentRepository.findById(1));
            System.out.println(studentRepository.getAllStudentsByAge(20));
            studentRepository.setStudentAge(1, 31);
            System.out.println(studentRepository.findById(1));
            studentRepository.removeStudent(1);
        }

    }

    private static StudentRepository getStudentRepository(SessionFactory sessionFactory) {
        StudentEntity anton = new StudentEntity();
        StudentEntity ivan = new StudentEntity();
        anton.setId(1);
        anton.setFirstName("Anton");
        anton.setSecondName("Buzynnikov");
        anton.setAge(28);
        ivan.setId(2);
        ivan.setFirstName("Ivan");
        ivan.setSecondName("Ivanov");
        ivan.setAge(19);
        StudentRepository studentRepository = new StudentRepository(sessionFactory);
        studentRepository.insertStudent(anton);
        studentRepository.insertStudent(ivan);
        return studentRepository;
    }
}
