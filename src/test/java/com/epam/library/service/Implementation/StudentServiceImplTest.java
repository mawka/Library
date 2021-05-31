package com.epam.library.service.Implementation;

import static java.util.Optional.of;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.epam.library.LibraryApplication;
import com.epam.library.model.Student;
import com.epam.library.repository.StudentRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = LibraryApplication.class)
public class StudentServiceImplTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    public void findByIDWhenStudentIsFoundedThenReturnStudent() {
        UUID actualUUID = UUID.randomUUID();
        Student expectedStudent = new Student();
        expectedStudent.setId(actualUUID.toString());
        when(studentRepository.findById(actualUUID.toString()))
                .thenReturn(of(of(expectedStudent)).get());
        Student actualStudent = studentService.findByID(actualUUID.toString());
        assertSame(expectedStudent, actualStudent);
    }

    @Test
    public void findByIDWhenStudentItsNotFoundedThenThrowException() {
        UUID UUIDExpected = UUID.randomUUID();

        String expectedException = "Student with this id not founded!";
        exception.expect(RuntimeException.class);
        exception.expectMessage(expectedException);
        studentService.findByID(UUIDExpected.toString());
    }

    @Test
    public void saveStudentWhenSaveThenReturnBook() {
        Student expectedStudent = new Student();
        UUID expectedUUID = UUID.randomUUID();
        expectedStudent.setId(expectedUUID.toString());
        when(studentRepository.save(expectedStudent)).thenReturn(expectedStudent);
        Student actualStudent = studentService.save(expectedStudent);
        assertSame(expectedStudent, actualStudent);
    }

    @Test
    public void findAllStudentWhenFoundedThenReturnPages() {
        studentService.findAllStudents(Pageable.unpaged());
        verify(studentRepository).findAllStudents(Pageable.unpaged());
    }

    @Test
    public void deleteByIDWhenStudentFoundedThenDelete() {
        UUID expectedUUID = UUID.randomUUID();
        studentService.deleteByID(expectedUUID.toString());
        verify(studentRepository).deleteById(expectedUUID.toString());
    }

}