package com.rubypaper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;


@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardRepository boardRepo;

	@Override
	public List<Board> getBoardList(Board board) {
		return boardRepo.findAll();
	}

	@Override
	public void insertBoard(Board board) {
		boardRepo.save(board);
	}

	@Override
	public Board getBoard(Board board) {
		return boardRepo.findById(board.getSeq()).get();
	}

	@Override
	public void updateBoard(Board board) {
		Board findBoard = boardRepo.findById(board.getSeq()).get();

		findBoard.setTitle(board.getTitle());
		findBoard.setContent(board.getContent());
		boardRepo.save(findBoard);
	}

	@Override
	public void deleteBoard(Board board) {
		boardRepo.deleteById(board.getSeq());
	}

}
