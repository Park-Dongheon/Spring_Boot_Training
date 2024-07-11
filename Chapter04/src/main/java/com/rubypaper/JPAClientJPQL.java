package com.rubypaper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.rubypaper.domain.Board;

public class JPAClientJPQL {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		EntityManager em = emf.createEntityManager();
		
		try {
			String jpql = "SELECT B FROM Board B WHERE B.seq < 5 ORDER BY B.seq ASC";
			TypedQuery<Board> tq = em.createQuery(jpql, Board.class);
			List<Board> list = tq.getResultList();
			
			for (Board b : list) {
				System.out.println(b);
			}
			
			String sql = "SELECT B.title FROM Board B WHERE B.seq < 5 ORDER BY B.seq ASC";
			Query q = em.createNativeQuery(sql);
			@SuppressWarnings("unchecked")
			List<String> list2 = q.getResultList();
			
			for (String s : list2) {
				System.out.println(s);
			}
			
			String sql2 = "SELECT B.title, B.content FROM Board B WHERE B.seq < 5 ORDER BY B.seq ASC";
			Query q2 = em.createNativeQuery(sql2);
			@SuppressWarnings("unchecked")
			List<Object[]> list3 = q2.getResultList();
			
			for (Object[] s : list3) {
				for(Object o : s)
					System.out.print(o.toString() + "\n");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}
	
}
