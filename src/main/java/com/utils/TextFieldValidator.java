package com.utils;

import java.util.List;

import com.view.Option;

import javafx.scene.Node;

public class TextFieldValidator {

	public static Message judgeNull(List<Node> options) {
		for(Node n:options) {
			Option o = (Option)n;
			if(o.getContent()==null||o.getContent().equals("")) {
				return new Message(false,"*"+o.getText()+"不能为空*");
			}
		}
		return new Message(true,null);		
	}
	
}
