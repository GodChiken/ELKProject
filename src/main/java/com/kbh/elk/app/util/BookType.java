package com.kbh.elk.app.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum BookType implements BaseEnum{
	HISTORY("역사"),
	STUDY("학습"),
	BUSINESS("사업"),
	KIDS("어린이"),
	COMIC("만화"),
	PROGRAMMING("프로그래밍");

	private String name;

}
