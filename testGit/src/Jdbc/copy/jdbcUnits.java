package Jdbc.copy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class jdbcUnits {
	private static Connection getConn() {
	    String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://192.168.1.7:3306/test";
	    String username = "sqa";
	    String password = "sqa";
	    Connection conn = null;
	    try {
	        Class.forName(driver); //classLoader,加载对应驱动
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
	private static int insert(Student student) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "insert into tb_user (user_name,password,Age) values(?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, student.getuser_name());
	        pstmt.setString(2, student.getpassword());
	        pstmt.setString(3, student.getAge());
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	private static int update(Student student) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "update tb_user set Age='" + student.getAge() + "' where user_name='" + student.getuser_name() + "'";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        i = pstmt.executeUpdate();
	        System.out.println("resutl: " + i);
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	private static Integer getAll() {
	    Connection conn = getConn();
	    String sql = "select * from tb_user";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
//	        获取所有列名
	        int col = rs.getMetaData().getColumnCount();
	        System.out.println("============================");
	        while (rs.next()) {
	            for (int i = 1; i <= col; i++) {
	                System.out.print(rs.getString(i) + "\t");
	                if ((i == 2) && (rs.getString(i).length() < 8)) {
	                    System.out.print("\t");
	                }
	             }
	            System.out.println("");
	        }
	            System.out.println("============================");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	private static int delete(String name) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "delete from tb_user where user_name='" + name + "'";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        i = pstmt.executeUpdate();
	        System.out.println("resutl: " + i);
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	public static void main(String args[]) {
		jdbcUnits.getAll();
		for (int i = 0; i < 3; i++) {
			jdbcUnits.insert(new Student("Achilles"+i, "rqrqwrq", "14"));
		}
		jdbcUnits.getAll();
/*		jdbcUnits.insert(new Student("Achilles", "rqrqwrq", "14"));
		jdbcUnits.getAll();
		jdbcUnits.update(new Student("Bean", "", "7"));
		jdbcUnits.delete("Achilles");
		jdbcUnits.getAll();*/
	}
}
