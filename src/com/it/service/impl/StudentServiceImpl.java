package com.it.service.impl;

import com.it.dao.StudentDao;
import com.it.dao.impl.StudentDaoImpl;
import com.it.entity.Page;
import com.it.entity.Student;
import com.it.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    //创建dao层对象
    private StudentDao studentDao = new StudentDaoImpl();

    @Override
    public boolean addStudent(Student student) {
        int i = studentDao.addStudent(student);
        return i > 0;

    }

    @Override
    public Page<Student> getPageInfo(Student student, String currentPage) {
        //获取符合条件的总条数
        long count = studentDao.getCount(student);
        //获取页面数据
        int initSize=5;
        //当前页的逻辑处理
        int current=1;
        if(currentPage!=null&&currentPage.trim()!=""){
            current=Integer.parseInt(currentPage);
        }
        int start=(current-1)*initSize;
        //将数据封装成page对象
        List<Student> pageData = studentDao.getPageData(student, start, initSize);
        Page<Student> studentPage = new Page<>(initSize, (int) count,current,pageData);
        return studentPage;
    }



    @Override
    public boolean batchDelete(String ids){
        int num=0;
        if(ids!=null&&ids.trim()!=""){
            String[] sids = ids.split(",");
            for (String sid : sids) {
                int id=Integer.parseInt(sid);
                int i = studentDao.batchDelete(id);
                num+=i;
            }
        }
        System.out.println("删除学生条数"+num);
        return num>0;
    }

    @Override
    public Student queryStudentById(int sid) {
      Student student =  studentDao.queryStudentById(sid);
      return student;
    }

    @Override
    public boolean updateStudentById(Student student) {
      int row= studentDao.updateStudentById(student);
      return row>0;
    }

}
