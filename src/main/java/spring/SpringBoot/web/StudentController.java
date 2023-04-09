package spring.SpringBoot.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.SpringBoot.entiry.Student;
import spring.SpringBoot.service.IStudentSrv;

/**
 * 学生信息Controller
 *
 * @author pang_gqing
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    IStudentSrv service;


    @RequestMapping("/getStudents")
    public List<Student> getStudents() {
        List<Student> list = new ArrayList<Student>();
        list = service.getStudentInfo();
        return list;
    }

//	@RequestMapping("/addStudentInfo")
//	public String addStudentInfo(@RequestParam("name")String name,@RequestParam("password")String password) {
//		Student stu = new Student();
//		stu.setName(name);
//		stu.setPassword(password);
//		service.addStudentInfo(stu);
//		return "新增的信息为："+name+",密码是："+password;
//	}
//
//	@RequestMapping("/updateStudentInfo")
//	public String updateStudentInfo(@RequestParam("name")String name,@RequestParam("password")String password) {
//		Student stu = new Student();
//		stu.setName(name);
//		stu.setPassword(password);
//		stu.setIs_valid("1");
//		service.updateStudentInfo(stu);
//		return "修改的信息为："+name+",密码是："+password;
//	}
//
//	@RequestMapping("/deleteStudentInfo")
//	public String deleteStudentInfo(@RequestParam("name")String name) {
//		Student stu = new Student();
//		stu.setName(name);
//		service.deleteStudentInfo(stu);
//		return "删除的信息为："+name;
//	}
}