package com.jbedu.mysql.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jbedu.mysql.dto.BoardDto;

public class BoardDao {

	String driverName = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/jsp_project";
	String username = "root";
	String password = "12345";
		
	// 게시판 글쓰기. (반환을 받아야 하나 테스트라 그냥 글 입력되고 끝나게)
	public void boardWrite(String bname, String btitle, String bcontent) {
		
		String sql = "INSERT INTO mvc_board(bname, btitle, bcontent, bhit) VALUES (?, ?, ?, 0)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, username, password);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//모든 글 리스트 보기
	public ArrayList<BoardDto> boardList() {
		
		String sql = "SELECT * FROM mvc_board ORDER BY bnum DESC";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		BoardDto boardDto = null;
		ArrayList<BoardDto> boardDtos = new ArrayList<BoardDto>();
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, username, password);
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				boardDto = new BoardDto(rs.getInt("bnum"), rs.getString("bname"), rs.getString("btitle"), rs.getString("bcontent"), rs.getString("bdate"), rs.getInt("bhit"));
				boardDtos.add(boardDto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return boardDtos;
	}
	
	public int boardDelete(String bnum) {
		
		String sql = "DELETE FROM mvc_board WHERE bnum = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int success = 0;
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, username, password);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bnum);
			
			success = pstmt.executeUpdate();  //삭제 성공->1, 실패-> 0
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return success;
	}
}
