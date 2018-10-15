package main.java.jabc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

/**
 * @ClassName JdbcTest
 * @Desctription TODO
 * @Author sapientia
 * @Date 2018/10/15 15:01
 */
public class JdbcTest {
  /**
   * jdbc 驱动名及url地址,mybatis数据库连接
   */
  static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
  /**
   * 连接地址
   */
  static  final String DB_URL="jdbc:mysql://localhost:3306/jdbc";
  /**
   * 数据库账号
   */
  static final String USER="root";
  /**
   * 数据库密码
   */
  static  final String PASS="1234";

  public static void main(String[] args) {
    Connection conn = null;
    Statement stmt = null;
    try{
       //注册 JDBC 驱动
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("连接数据库");
      //打开链接
      conn = DriverManager.getConnection(DB_URL,USER,PASS);
// 执行查询
      System.out.println("实例化Statement对象");
      stmt = conn.createStatement();
      String sql;
      sql = "SELECT id, test1, test2 FROM jdbctest";
      ResultSet rs = stmt.executeQuery(sql);
// 展开结果集数据库
      while(rs.next()){
// 通过字段检索
        int id = rs.getInt("id");
        String test1 = rs.getString("test1");
        String test2 = rs.getString("test2");
// 输出数据
        System.out.print("ID: " + id);
        System.out.print(", test1: " + test1);
        System.out.print(", test2: " + test2);
        System.out.print("\n");
      }
// 完成后关闭
      rs.close();
      stmt.close();
      conn.close();
    }catch(SQLException se){
// 处理 JDBC 错误
      se.printStackTrace();
    }catch(Exception e){
// 处理 Class.forName 错误
      e.printStackTrace();
    }finally{
// 关闭资源
      try{
        if(stmt!=null) {
          stmt.close();
        }
      }catch(SQLException se2){
      }
      try{
        if(conn!=null) {
          conn.close();
        }
      }catch(SQLException se){
        se.printStackTrace();
      }
    }
    System.out.println("OK");
  }
}