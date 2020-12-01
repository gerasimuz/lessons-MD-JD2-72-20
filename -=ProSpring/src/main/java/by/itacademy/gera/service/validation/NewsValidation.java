package by.itacademy.gera.service.validation;

import by.itacademy.gera.entity.News;
import org.apache.log4j.Logger;

public class NewsValidation {
    private static final Logger logger = Logger.getLogger(NewsValidation.class);

    public static final int AUTHOR_LENGTH = 50;
    public static final int BRIEF_LENGTH = 200;
    public static final int CONTENT_LENGTH = 1000;


    /** Validate, that NEWS have all filled fields, but not longer that max size */

    public static boolean isNoteEmptyField(News news) {

        if (news == null) {
            logger.error("Error, empty News in Validation");
            return true;

        } else if (news.getAuthor().isEmpty() || news.getBrief().isEmpty()
                || news.getContent().isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isCorrectLength(News news) {

        if (news == null) {
            logger.error("Error, news == null");
            return true;

        } else if (news.getAuthor().length() > AUTHOR_LENGTH
                || news.getBrief().length() > BRIEF_LENGTH
                || news.getContent().length() > CONTENT_LENGTH) {
            return true;
        }
        return false;
    }

}

//          --- one more validation method
//           public static final String NOTFILLED = "";
//           else if (news.getAuthor().equals(NOTFILLED) || news.getBrief().equals(NOTFILLED)
//                    || news.getContent().equals(NOTFILLED)) {
