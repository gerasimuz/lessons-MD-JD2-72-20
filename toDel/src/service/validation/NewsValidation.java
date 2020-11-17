package service.validation;

import java.util.regex.Pattern;

import entity.News;

public class NewsValidation {

	public static boolean isCorrectLength(News news) {
		
		
		final int TITLE_MAX_LENGTH = 300;
		final int BRIEF_MAX_LENGTH = 2000;
		final int CONTENT_MAX_LENGTH = 2000;

		String title = news.getTitle();
		String brief = news.getBrief();
		String content = news.getContent();

	if (((title.length() > 0) && (title.length()<TITLE_MAX_LENGTH))&&
			((brief.length() > 0) && (brief.length()<BRIEF_MAX_LENGTH))&&
			((content.length() > 0) && (content.length()<CONTENT_MAX_LENGTH))) {
		return true;
	}else	
		return false;
		
	}
		
public static boolean digitIsPositive(int id) {
		
	if (id>0) {
		return true;
	}else	
		return false;
		
	}
}