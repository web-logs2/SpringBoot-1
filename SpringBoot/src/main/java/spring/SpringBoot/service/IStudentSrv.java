package spring.SpringBoot.service;

import java.util.List;

import spring.SpringBoot.entiry.Student;
/**
 * 学生信息Srv
 * @author pang_gqing
 *
 */
public interface IStudentSrv {
//	/**
//	 * 新增学生信息
//	 * @param stu
//	 */
//	public void addStudentInfo(Student stu);
//
//	/**
//	 * 更新学生信息
//	 * @param stu
//	 * @return
//	 */
//	public int updateStudentInfo(Student stu);
//
//	/**
//	 * 删除学生信息
//	 * @param stu
//	 * @return
//	 */
//	public int deleteStudentInfo(Student stu);
//
	/**
	 * 获取学生信息ss
	 * @param
	 * @return
	 */
	public List<Student> getStudentInfo();
}
