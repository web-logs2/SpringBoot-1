package spring.SpringBoot.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.SpringBoot.entiry.Student;
import spring.SpringBoot.mapper.StudentMapper;
import spring.SpringBoot.service.IStudentSrv;

@Service
public class StudentSrvImpl implements IStudentSrv {
	
//	@Autowired
//	StudentMapper mapper;
//
//	@Override
//	public void addStudentInfo(Student stu) {
//		mapper.addStudentInfo(stu);
//	}
//
//	@Override
//	public int updateStudentInfo(Student stu) {
//		return mapper.updateStudentInfo(stu);
//	}
//
//	@Override
//	public int deleteStudentInfo(Student stu) {
//		return mapper.deleteStudentInfo(stu);
//	}

	@Override
	public List<Student> getStudentInfo() {
//		return mapper.getStudentInfo();
		List<Student> students = new ArrayList<Student>();
		Student student = new Student();
		student.setName("lixiaokang");
		student.setPassword("123456");
		student.setIs_valid("yes");
		students.add(student);
		return students;
	}

}
