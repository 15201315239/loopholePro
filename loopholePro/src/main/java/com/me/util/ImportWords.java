package com.me.util;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.me.entity.Words;

public class ImportWords extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = JDBCConnection.getJDBCConnection()
				.getConnection();
		try {

			System.out.println("成功加载MySQL驱动程序");

			int result = 0;
			// result = stmt.executeUpdate(sql);//
			// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
			if (result != -1) {
				ReadExcel read = new ReadExcel();
				// List<Words> word = read.readXls(getFile(path));
				List<Words> word = read
						.readXls(5,"C:\\Users\\Administrator\\Desktop\\新东方词汇\\TOEFL单词汇总-最新版 - 副本.xlsx");
				int index = 0;
				StringBuffer erro = new StringBuffer();
				int cur_pages = 1;
				int order_nums = 1;
				for (Words words : word) {
					int wordid = 0;

					String ss = "insert into wd_word(word,phonetic_us,pronun_us_man,server_id) values('"
							+ words.getWord()
							+ "','["
							+ words.getPronunciation().replace("'", "‘")
							+ "]'," + "'" + words.getMaleVoice() + "',1)";
					System.out.println(ss);
					try {
						result = connection.createStatement().executeUpdate(
								ss.toString());
					} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
						index++;
						erro.append(words.getWord() + " \r\n  ");
						e.printStackTrace();
						continue;
					}
					ResultSet rs = null;

					String wordSql = "SELECT MAX(id) id FROM wd_word ";
					try {
						Statement statement = connection.createStatement();
						rs = statement.executeQuery(wordSql);
						while (rs.next()) {
							wordid = rs.getInt("id");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					// 拆分单词词性
					String[] props = words.getProp().split("/");
					if (props.length > 1) {
						for (int i = 0; i < props.length; i++) {
							java.sql.Statement statement;
							String[] meanings = null;
							meanings = words.getMeaning().split("；");
							if (meanings.length < 1) {
								meanings = words.getMeaning().split(";");
							}
							String meaning = null;
							try {
								meaning = meanings[i].substring(meanings[i]
										.lastIndexOf(".") + 1);

							} catch (Exception e) {
								try {
									meaning = meanings[i];

								} catch (Exception e2) {
									meaning = meanings[0];
								}
							}

							String defSql = "insert into wd_word_def(word_id,word_prop,definition,book_tag) values("
									+ wordid
									+ ",'"
									+ props[i]
									+ "','"
									+ meaning
									+ "',"
									+ words.getBookTag()
									+ ") ";
							try {
								statement = connection.createStatement();
								statement.executeUpdate(defSql);
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					} else {
						java.sql.Statement statement;
						String defSql = "insert into wd_word_def(word_id,word_prop,definition,book_tag) values("
								+ wordid
								+ ",'"
								+ words.getProp()
								+ "','"
								+ words.getMeaning()
								+ "',"
								+ words.getBookTag() + ")";
						try {
							statement = connection.createStatement();
							statement.executeUpdate(defSql);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					// 添加近义词
					String[] syns = words.getSynonym().split(",");
					if (syns.length > 0) {
						for (String string : syns) {
							java.sql.Statement statement;
							String synsSql = "insert into wd_word_synonym(synonym,word_id) values('"
									+ string + "'," + wordid + ")";
							try {
								statement = connection.createStatement();
								statement.executeUpdate(synsSql);
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
					// 添加反义词
					String[] ants = words.getAntonym().split(",");
					System.out.println(ants.length);
					if (ants.length > 0) {
						for (String string : ants) {
							java.sql.Statement statement;
							String antsSql = "insert into wd_word_antonym(antonym,word_id) values('"
									+ string + "'," + wordid + ")";
							try {
								statement = connection.createStatement();
								statement.executeUpdate(antsSql);
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}

					//系统生成页码，4页一组
					if(cur_pages==45){
						cur_pages = 1;
					}
					//存储单词详细表
					java.sql.Statement statement;
					String defDSql = "insert into wd_handout_detail(cur_page,book_id,word_id,word,order_num,definition) values("
							+ cur_pages + ",2,"+wordid+",'"+words.getWord()+"',"+order_nums+",'"+words.getPronunciation()+"')";

					try {
						statement = connection.createStatement();
						statement.executeUpdate(defDSql);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					cur_pages++;
					order_nums++;
				}
				System.out.println(word.size());
				System.out.println("重复单词：" + index + "个.包括\r\n " + erro);
				/*
				 * sql = "select * from student"; ResultSet rs =
				 * stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值 while
				 * (rs.next()) { System.out .println(rs.getString(1) + "\t" +
				 * rs.getString(2));// 入如果返回的是int类型可以用getInt() }
				 */
			}
		} catch (SQLException e) {
			System.out.println("MySQL操作错误");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String getFile(String srcPath) {
		File dir = new File(srcPath);
		File[] files = dir.listFiles();

		String filePath = files[0].getAbsolutePath();
		return filePath;
	}

	public static void main(String[] args) {
		try {
			new ImportWords().doPost(null, null);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}