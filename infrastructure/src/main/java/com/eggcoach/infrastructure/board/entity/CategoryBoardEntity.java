package com.eggcoach.infrastructure.board.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "category_board")
public class CategoryBoardEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryBoardSeq;

	@Column(name = "category_name", nullable = false)
	private String categoryName;

	@Column(name = "description")
	private String description;

	@Column(name = "display_order")
	private Integer displayOrder;

	@Column(name = "use_yn", length = 1)
	private String useYn;

	@Column(name = "creator_id")
	private String creatorId;

	@Column(name = "create_at")
	private LocalDateTime createAt;

	@Column(name = "update_at")
	private LocalDateTime updateAt;
}