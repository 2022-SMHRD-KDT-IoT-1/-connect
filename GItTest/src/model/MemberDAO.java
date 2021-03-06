package model;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MemberDAO {
	// 기존의 dao 모든 메서드에서 connection 생성 --> psmt 생성
		// SqlSessionFactory : connection을 미리 만들어둠 --> 사용할 때 빌려가기만
		private static SqlSessionFactory sqlSessionFactory;
		
		// 초기화 블럭 --> 기본적으로 생성자가 실행되기 직전
		// static 초기화 블럭 --> static 변수들이 메모리에 올라간 순간
		static {
			try {
				String resource = "Mapper/config.xml";
				InputStream inputStream = Resources.getResourceAsStream(resource);
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);					
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// =================================================================
		
		
		public int join(MemberVO vo) {
			// 1. SQLSession 만들기
			// 매개변수로 boolean --> autocommit의 사용유무
			SqlSession session = sqlSessionFactory.openSession(true);		
			// insert, delete, update는 이름 똑같음
			// insert("id값", 매개변수)
			// mapper.xml에서 resultType(반환타입)을 정의하지 않아도 자동으로 int로 리턴
			// Select문의 경우 : 1개 --> selectOne("id값", 매개변수), selectList("id값", 매개변수)
			int cnt = session.insert("joinService", vo);		
			// 커밋
			//session.commit();		
			// 세션 닫기
			session.close();
			return cnt;
		}
		// === join ===
		
		
		
		public MemberVO login(MemberVO vo) {
			// 1. sqlSession
			SqlSession session = sqlSessionFactory.openSession();
			// 2. Mapper에 정의해둔 sql문 사용
			MemberVO uvo = session.selectOne("loginService", vo);	
			// 3. session 닫기
			session.close();
			return uvo;
			
		}
		// === login ===
		
		
		public int update(MemberVO vo) {
			SqlSession session = sqlSessionFactory.openSession(true);	
			int cnt = session.update("updateService", vo);
			session.close();
			return cnt;
		}
		// === update ===
		
		
		
		public int delete(String mb_id) {
			SqlSession session = sqlSessionFactory.openSession(true);
			int cnt = session.delete("deleteService", mb_id);
			session.close();
			return cnt;
			// 영재
		}
		// === delete ===
		
		
		public MemberVO idCheck(String mb_id) {
			SqlSession session = sqlSessionFactory.openSession();
			MemberVO vo = session.selectOne("idCheck", mb_id);
			session.close();
			return vo;
		}
}
