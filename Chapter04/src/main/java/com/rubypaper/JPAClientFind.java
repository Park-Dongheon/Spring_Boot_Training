package com.rubypaper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.rubypaper.domain.Board;

public class JPAClientFind {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		EntityManager em = emf.createEntityManager();
		
		try {
			Board searchBoard = em.find(Board.class, 1L);	// find() : DB에서 읽어와 entityManager에 저장됨, 수정 시 DB에 수정된 데이터가 저장됨
			System.out.println("--->" + searchBoard);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		
	}
	
}
