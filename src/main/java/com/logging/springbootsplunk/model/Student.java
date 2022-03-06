package com.logging.springbootsplunk.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class Student {
	
	private int id;
	private String name;
	private String course;

}
