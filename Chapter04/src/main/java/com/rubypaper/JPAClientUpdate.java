package com.rubypaper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.domain.Board;

public class JPAClientUpdate {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		EntityManager em = emf.createEntityManager();
		
		// Transaction 생성
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			Board searchBoard = em.find(Board.class, 1L);	// find() : DB에서 읽어와 Persistence Context 에 저장됨, 수정 시 DB에 수정된 데이터가 저장됨
			System.out.println("--->" + searchBoard);			
			
			searchBoard.setTitle("수정된 타이틀");
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		
	}
}
