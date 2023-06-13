package yoon.spring.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import yoon.spring.bbs.dto.SpDto;
import yoon.spring.bbs.util.Static;

// db 접근 클래스
public class SpDao {
	DataSource dataSource;
	JdbcTemplate template = null;

	// 생성자에서 DB 접속
	public SpDao() {
		try {

			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/spbbs");

		} catch (NamingException e) {
			e.printStackTrace();
		}

		template = Static.template;
	}

	// 글쓰기
	public void write(String uname, String upass, String title, String content) {

		KeyHolder keyHolder = new GeneratedKeyHolder();

		template.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

				String sql = "insert into spboard(uname, upass, title, content) value(?, ?, ?, ?)";
				PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, uname);
				pstmt.setString(2, upass);
				pstmt.setString(3, title);
				pstmt.setString(4, content);
				ResultSet rs = pstmt.getGeneratedKeys();

				if (rs.next()) {
					System.out.println("첫번째" + rs.getInt(0));
					System.out.println("두번째" + rs.getInt(1));
					groupUpdate(rs.getInt(1));
				}
				return pstmt;
			}
		}, keyHolder);

		int idx = keyHolder.getKey().intValue();
		groupUpdate(idx);
	}

	// 글 삭제하기
	public void delete(String cNum) {
		int iNum = Integer.parseInt(cNum);
		String sql = "delete from spboard where num = ?";
		template.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, iNum);
			}
		});

	}

	private void groupUpdate(int num) {
		String sql = "update spboard set s_group = ? where num = ?";
		template.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, num);
				pstmt.setInt(2, num);
			}
		});
	}

	// 답글쓰기
	public SpDto reply(String cNum) {

		int iNum = Integer.parseInt(cNum);
		String sql = "select * from spboard where num = " + iNum;
		return template.queryForObject(sql, new BeanPropertyRowMapper<SpDto>(SpDto.class));
	}

	// 답글 등록하기
	public void replyok(int s_group, int s_step, int s_indent, String uname, String upass, String title,
			String content) {
		String sql = "insert into spboard (s_group, s_step, s_indent, uname, upass, title, content) value (?,?,?,?,?,?,?)";
		template.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, s_group);
				pstmt.setInt(2, s_step + 1);
				pstmt.setInt(3, s_indent + 1);
				pstmt.setString(4, uname);
				pstmt.setString(5, upass);
				pstmt.setString(6, title);
				pstmt.setString(7, content);
			}
		});
	}

	// 글 수정하기
	public SpDto update(String cNum) {
		int iNum = Integer.parseInt(cNum);
		String sql = "select * from spboard where num = " + iNum;
		return template.queryForObject(sql, new BeanPropertyRowMapper<SpDto>(SpDto.class));
	}

	public void updateok(String num, String uname, String upass, String title, String content) {

		int inum = Integer.parseInt(num);
		String sql = "update spboard set uname=?, upass=?, title=?, content=? where num = ?";
		template.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {

				pstmt.setString(1, uname);
				pstmt.setString(2, upass);
				pstmt.setString(3, title);
				pstmt.setString(4, content);
				pstmt.setInt(5, inum);
			}
		});
	}

	// 본문 보기
	public SpDto detail(String cNum) {

		int iNum = Integer.parseInt(cNum);
		hitAdd(iNum); // 조회수 증가
		String sql = "select * from spboard where num = " + iNum;
		return template.queryForObject(sql, new BeanPropertyRowMapper<SpDto>(SpDto.class));

	}

	// 데이터를 받아서 SpDto에 담음
	public ArrayList<SpDto> list(String pg) {

		int listCount = 10;
		int page = Integer.parseInt(pg);
		int min = (page - 1) * listCount;
		String limit = " limit " + min + " , " + listCount;

		String sql = "select * from spboard order by s_group desc, s_step asc" + limit;
		return (ArrayList<SpDto>) template.query(sql, new BeanPropertyRowMapper<SpDto>(SpDto.class));
	}

	public int totalRecord() {
		String sql = "select count(*) from spboard";
		return template.queryForObject(sql, Integer.class);
	}

	public int totalRecord(String where) {
		String sql = "select count(*) from spboard where 1 and " + where;
		return template.queryForObject(sql, Integer.class);
	}

	// 문장보기 hit 업데이트
	private void hitAdd(int num) {

		String sql = "update spboard set hit = hit + 1 where num = ?";
		template.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {

				ps.setInt(1, num);

			}
		});

	}
}
