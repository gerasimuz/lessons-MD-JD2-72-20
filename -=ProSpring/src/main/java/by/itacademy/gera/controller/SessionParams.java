package by.itacademy.gera.controller;

public class SessionParams {

	public static final String CURRENT_PAGE = "current_page";
	public static final String OPERATION_RESULT_FOR_SESSION = "result_operation";

	public static final String RESULT_OPERATION_MSG_CREATE_SUCCESS = "News has added";
	public static final String RESULT_OPERATION_MSG_CREATE_UPDATE_FAILED = "Creation failed";
	public static final String RESULT_OPERATION_MSG_DELETE_SUCCESS = "News has deleted";
	public static final String RESULT_OPERATION_MSG_UPDATE_SUCCESS = "News has updated";

	public static final String PAGE_DEFAULT = "mainPage";
	public static final String FORM_FOR_ADD_UPDATE = "showFormForAddAndUpdate";
	public static final String PAGE_WELCOME = "index";

	public static final String ACTION_UPDATE_NEWS = "updateNews";
	public static final String ACTION_FIND_ALL_NEWS = "findAllNews";
	public static final String ACTION_FIND_NEWS = "findNews";

	public static final String LOCALE = "locale";

	private SessionParams() {
	}
}