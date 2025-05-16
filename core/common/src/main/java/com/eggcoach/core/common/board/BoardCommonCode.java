package com.eggcoach.core.common.board;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BoardCommonCode {

	BOARD_NOT_FOUND("BOARD_NOT_FOUND", "게시글을 찾을 수 없습니다."),
	PERMISSION_DENIED("PERMISSION_DENIED", "게시글을 삭제할 권한이 없습니다."),
	DELETED_SUCCESS("DELETED_SUCCESS", "게시글이 성공적으로 삭제되었습니다."),
	DELETE_FAILED("DELETE_FAILED", "게시글 삭제에 실패했습니다.");

	private final String code;

	private final String message;
}
