package com.it.service;

import com.it.entity.Page;
import com.it.entity.Student;

/*
学生业务逻辑层
 */
public interface StudentService {
    /**
     * 添加学生信息，返回是否保存成功
     * @param student
     * @return true表示成功，false表示失败
     */
    public boolean addStudent(Student student);

    /**
     * 获取当前页的分页数据
     * @param student  获取数据条件
     * @param currentPage 当前页面
     * @return  页面数据
     */
    public Page<Student> getPageInfo(Student student, String currentPage);

    /**
     * 删除用户
     * @param ids
     * @return
     */
    public boolean batchDelete(String ids);

    /**
     * 根据学生id查询单个学生信息
     * @param sid
     * @return
     */
    public Student queryStudentById(int sid);

    /**
     * 根据学生id修改学生信息
     * @param student
     * @return
     */
    public boolean updateStudentById(Student student);
}
