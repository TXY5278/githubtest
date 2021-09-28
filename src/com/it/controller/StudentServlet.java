package com.it.controller;

import com.it.entity.Page;
import com.it.entity.Student;
import com.it.service.StudentService;
import com.it.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

@MultipartConfig//文件上传必须在类上加此注解，否则获取不到提交数据
@WebServlet("*.do")
public class StudentServlet extends BaseServlet {
    //添加学生信息
    public void addStu(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //获取参数
        String sname = req.getParameter("sname");
        String gender = req.getParameter("gender");
        String sbir = req.getParameter("sbir");
        String[] hobbies = req.getParameterValues("hobby");
        //头像只保留文件名到数据库
        //获取上传组件
        Part part = req.getPart("file");
        //1.获取文件上传名称，该方法仅限tomcat8.5以后版本
        String fileName = part.getSubmittedFileName();
        //8.5以前String header = part.getHeader("content-Disposition");
        //2.判断是否有上传文件，如果有则添加文件到文件服务器
        String name = "";//文件名称
        if (!"".equals(fileName)) {
            //防止文件名重复，使用UUID生成新的文件名
            UUID uuid = UUID.randomUUID();
            //拼接成新的文件名称
            name = uuid.toString() + fileName;
            //将文件保存到指定位置
            String path = "D:\\idea_work\\upload";
            File f = new File(path);
            //判断指定位置文件夹是否存在，不存在就创建
            if (!f.exists()) {
                f.mkdirs();
            }
            // File.separator代表  \\或者/  分割符
            part.write(f.getAbsolutePath() + File.separator + name);

        }
        Student student = new Student(null, sname, gender, sbir, Arrays.toString(hobbies), name);
        //交给业务逻辑层
        StudentService studentService = new StudentServiceImpl();
        boolean b = studentService.addStudent(student);
        if (b) {
            resp.sendRedirect("queryStu.do");
        } else {
            req.getRequestDispatcher("error.html").forward(req, resp);
        }
    }

    //查询并显示学生信息加分页
    public void queryStu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sname = req.getParameter("sname");
        String gender = req.getParameter("gender");
        String currentPage = req.getParameter("currentPage");
        //数据的封装
        Student student = new Student(sname, gender);
        StudentService studentService = new StudentServiceImpl();
        Page<Student> pageInfo = studentService.getPageInfo(student, currentPage);
        //页面数据的传递
        req.setAttribute("page", pageInfo);
        req.setAttribute("student", student);
        req.getRequestDispatcher("queryStu.jsp").forward(req, resp);
    }

    //删除学生信息
    public void batchDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String ids = req.getParameter("ids");
        StudentService studentService = new StudentServiceImpl();
        boolean b = studentService.batchDelete(ids);
        if (b) {
            resp.sendRedirect("queryStu.do");
        } else {
            req.getRequestDispatcher("error.html").forward(req, resp);
        }

    }
    //根据学生id查找学生信息并回显给修改页面
    public void updateJsp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sid = Integer.parseInt(req.getParameter("sid"));
        StudentService studentService = new StudentServiceImpl();
        Student student = studentService.queryStudentById(sid);
        req.setAttribute("student", student);
        req.getRequestDispatcher("updateStu.jsp").forward(req, resp);
    }

    //修改学生信息
    public void updateStu(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // 获取参数
        String sid = req.getParameter("sid");
        String sname = req.getParameter("sname");
        String gender = req.getParameter("gender");
        String sbir = req.getParameter("sbir");
        String[] hobby = req.getParameterValues("hobby");
        String oldPhoto = req.getParameter("oldPhoto");
        // 头像,只保留文件的名字到数据库
        // 获取上传的组件
        Part part = req.getPart("file");
        // 获取上传文件的名称,注意该方法仅限于他Tomcat8.5 以后的版本
        String filename = part.getSubmittedFileName();
        if (!"".equals(filename)) {
            // 为了防止文件名重复,使用UUID 来生成新的文件名
            UUID uuid = UUID.randomUUID();
            oldPhoto = uuid.toString() + filename;
            // 将文件保存到指定的位置
            String path = "D:\\idea_work\\upload";
            File f = new File(path);
            if (!f.exists()) { // 判断指定磁盘位置的文件夹是否存在
                f.mkdirs(); // 不存在就创建
            }
            // File.separator  磁盘分隔符对应常量
            // 将文件上传到指定的路径下
            part.write(f.getAbsolutePath() + File.separator + oldPhoto);
        }
        // 学生信息的封装
        Student student = new Student(Integer.parseInt(sid), sname, gender, sbir, Arrays.toString(hobby), oldPhoto);
        // 交给业务逻辑层处理
        StudentService ss = new StudentServiceImpl();
        boolean bo = ss.updateStudentById(student);

        // 控制页面的跳转
        if (bo) {
            resp.sendRedirect("queryStu.do");
        } else {
            // 修改失败跳错误页面
            resp.sendRedirect("error.html");
        }

    }
}
