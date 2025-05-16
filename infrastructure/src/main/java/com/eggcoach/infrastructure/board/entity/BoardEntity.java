package com.eggcoach.infrastructure.board.entity;

import java.time.LocalDateTime;

import com.eggcoach.core.domain.board.dto.BoardViewDto;
import com.eggcoach.infrastructure.account.entity.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "board")
public class BoardEntity {

	// PK
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long boardSeq;

	// 카테고리 아이디
	@ManyToOne
	@JoinColumn(name = "categoryBoardSeq")
	private CategoryBoardEntity categoryBoardEntity;

	// 유저 아이디
	@ManyToOne
	@JoinColumn(name = "user_seq")
	private UserEntity userEntity;

	// 평점
	@Column(name = "category_name")
	private String categoryName;

	// 공개 여부
	@Column(name = "lock_yn")
	private String lockYn;

	// 공지 여부
	@Column(name = "notice_yn")
	private String noticeYn;

	// 비공개 패스워드
	@Column(name = "lock_password")
	private String lockPassword;

	// 제목
	@Column(name = "title")
	private String title;

	// 내용
	@Column(name = "content")
	private String content;

	// 조회수
	@Column(name = "view_cnt")
	private Integer viewCnt;

	// 좋아요 수
	@Column(name = "favorite_cnt")
	private Integer favoriteCnt;

	// 생성 날짜
	@Column(name = "create_at")
	private LocalDateTime createAt;

	// 수정 날짜
	@Column(name = "update_at")
	private LocalDateTime updateAt;

	public BoardEntity registerBoard(
		BoardViewDto boardViewDto, CategoryBoardEntity categoryBoardEntity, UserEntity userEntity) {
		this.categoryBoardEntity = categoryBoardEntity;
		this.userEntity = userEntity;
		this.categoryName = boardViewDto.getCategoryName();
		this.lockYn = boardViewDto.getLockYn();
		this.noticeYn = boardViewDto.getNoticeYn();
		this.lockPassword = boardViewDto.getLockPassword();
		this.title = boardViewDto.getTitle();
		this.content = boardViewDto.getContent();
		this.viewCnt = 0;
		this.favoriteCnt = 0;
		this.createAt = LocalDateTime.now();
		this.updateAt = LocalDateTime.now();
		return this;
	}
}
