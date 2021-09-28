package com.it.dao;

import com.it.entity.Student;

import java.util.List;

/*
学生持久化层
 */
public interface StudentDao {
    //添加学生信息到学生表
    public int addStudent(Student student);

    /**
     * 根据条件获取符合条件的学生共计多少条
     *
     * @param student 查询条件
     * @return 符合条件条数
     */
    public long getCount(Student student);

    /**
     * 获取页面数据
     *
     * @param student 查询条件
     * @param start   开始位置
     * @param num     获取条数
     * @return 符合条件学生列表
     */
    public List<Student> getPageData(Student student, int start, int num);

    /**
     * 删除学生信息
     *
     * @param sid
     * @return
     */
    public int batchDelete(int sid);

    /**
     * 根据学生id查找学生信息
     *
     * @param sid
     * @return
     */
    public Student queryStudentById(int sid);

    /**
     * 根据学生id修改学生信息
     *
     * @param student
     * @return
     */
    public int updateStudentById(Student student);

}
