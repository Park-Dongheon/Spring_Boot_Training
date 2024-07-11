package com.rubypaper;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.domain.Board;

public class JPAClient {
	public static void main(String[] args) {
		// EntityManager 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		EntityManager em = emf.createEntityManager();	// entityManager : 데이터베이스
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			for (int i = 1; i <= 10; i++) {
				em.persist(Board.builder()	// persist() : 메모리에 저장
						.title("JPA 제목" + i)
						.writer("Writer" + i)
						.content("Content" + i)
						.createDate(new Date())
						.cnt(0L)
						.build());				
			}
			
			tx.commit();	// DB에 저장
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}
}
