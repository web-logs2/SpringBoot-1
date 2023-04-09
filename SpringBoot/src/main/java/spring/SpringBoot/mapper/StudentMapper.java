package spring.SpringBoot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import spring.SpringBoot.entiry.Student;

/**
 * 学生信息管理Mapper
 * @author pang_gqing
 *
 */
//@Mapper
public interface StudentMapper {
	/**
	 * 新增学生信息
	 * @param stu
	 */
	public void addStudentInfo(Student stu);
	
	/**
	 * 更新学生信息
	 * @param stu
	 * @return
	 */
	public int updateStudentInfo(Student stu);
	
	/**
	 * 删除学生信息
	 * @param stu
	 * @return
	 */
	public int deleteStudentInfo(Student stu);
	
	/**
	 * 获取学生信息
	 * @param stu
	 * @return
	 */
	public List<Student> getStudentInfo();
}