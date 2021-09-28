package com.it.dao.impl;

import com.it.dao.StudentDao;
import com.it.entity.Student;
import com.it.util.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    private  QueryRunner qr = new QueryRunner(DruidUtils.getDatasource());
    @Override
    public int addStudent(Student student) {
        String sql="insert into student values(0,?,?,?,?,?)";
        int row = 0;
        try {
            row = qr.update(sql, student.getSname(), student.getGender(), student.getSbir(), student.getHobby(), student.getPhoto());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }



    private String getSql(Student student, StringBuilder sb) {
        String sname=student.getSname();
        if(sname!=null && sname.trim()!=""){
            sb.append(" and sname like '%"+sname+ "%'");
        }
        String gender=student.getGender();
        if(gender!=null && !gender.equals("-1")&&gender.trim()!=""){
            sb.append(" and gender ='"+gender+"'");
        }
        return sb.toString();
    }

    @Override
    public long getCount(Student student) {
        StringBuilder sb=new StringBuilder("select count(*) from student where 1=1");
        String sql = getSql(student, sb);
        System.out.println("sql = " + sql);
        long l = 0;
        try {
            l = qr.query(sql, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return l;
    }
    @Override
    public List<Student> getPageData(Student student, int start, int num) {
        StringBuilder sb=new StringBuilder("select * from student where 1=1");
        String sql=getSql(student,sb);
        String sql1 = sql.concat(" limit ?,?");
        System.out.println("sql1 = " + sql1);
        List<Student> stuList = null;
        try {
            stuList = qr.query(sql1, new BeanListHandler<>(Student.class), start, num);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stuList;
    }

    @Override
    public int batchDelete(int sid) {
        String sql="delete from student where sid=?";
        int row = 0;
        try {
            row = qr.update(sql, sid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Student queryStudentById(int sid) {
        String sql="select * from student where sid=?";
        Student student = null;
        try {
            student = qr.query(sql, new BeanHandler<>(Student.class), sid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public int updateStudentById(Student student) {
        String sql="update student set sname=?, gender=?, sbir=?, hobby=?, photo=? where sid=?";
        int row = 0;
        try {
            row = qr.update(sql, student.getSname(), student.getGender(), student.getSbir(), student.getHobby(), student.getPhoto(), student.getSid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;

    }
}
