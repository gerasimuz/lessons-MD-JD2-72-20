package by.itacademy.gera.controller;

import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import by.itacademy.gera.entity.News;
import by.itacademy.gera.service.NewsService;
import by.itacademy.gera.service.ServiceException;

@Controller
@RequestMapping(value = "/news")
public class NewsController {
	private static final Logger logger = Logger.getLogger(NewsController.class);

	public static final String LOCALE = "locale";
	public static final LocalDate DATE = LocalDate.now();

	private NewsService newsService;

	@Autowired
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}


	/** starting page */

	@RequestMapping("/mainPage")
	public String mainPage() {

		return "mainPage";
	}


	/**	 Error page	 */

	@RequestMapping("/error")
	public String errorPage() {

		return "errorPage";
	}

	/** Form for add/update News.
	 *
	 * <p>In case new News - shows empty form to fill. In case updating News - shows filled form with
	 * existing data from DB.
	 *
	 * <p> KEY_CURRENT_PAGE session parameter uses for localization. In case changing localization
	 * while filling new news or updating existing news  form return to the same page or other */
	@GetMapping(value = "/showFormForAddAndUpdate")
	public String showFormForAddAndUpdate(Model model, HttpSession session) {

		News news = new News();

		session.setAttribute(SessionParams.CURRENT_PAGE, SessionParams.FORM_FOR_ADD_UPDATE);

		model.addAttribute("news", news);

		return "news-form";
	}

	/**
	 * Creates and updates News
	 * Accepts News object from Form to create new or update existing News.
	 * <p>Creates Session parameter KEY_RESULT_OPERATION with result operation message.
	 *Uses Post/Redirect/Get
	 */
	@PostMapping(value = "/createNews")
	public String createNews(@ModelAttribute("news") News news, HttpSession session) {

		if (news == null) {
			logger.error("Error creating news, news == null");
			return "redirect:/news/error";
		}

		try {
			news.setDate(DATE);
			newsService.createNews(news);

			if (!SessionParams.RESULT_OPERATION_MSG_UPDATE_SUCCESS.equals(session.getAttribute(SessionParams.OPERATION_RESULT_FOR_SESSION))) {
				session.setAttribute(SessionParams.OPERATION_RESULT_FOR_SESSION, SessionParams.RESULT_OPERATION_MSG_CREATE_SUCCESS);
			}

			session.setAttribute(SessionParams.CURRENT_PAGE, SessionParams.PAGE_WELCOME);

		} catch (ServiceException e) {
			session.setAttribute(SessionParams.OPERATION_RESULT_FOR_SESSION, SessionParams.RESULT_OPERATION_MSG_CREATE_UPDATE_FAILED);
			logger.error("Error creating news / " + e);
		}

		return "redirect:/news/welcomePage";
	}

	/**
	 * Deletes News by id.
	 * <p>
	 * Executes only from all News list "/findAllNews" page or "/findNews" page.
	 *
	 * <p>
	 * Session parameter KEY_CURRENT_PAGE  changes if delete operation executes form page "/findNews"
	 * so after successfully deletion redirect to main page, otherwise KEY_CURRENT_PAGE keeps "/findAllNews" page
	 * and redirects back to "/findAllNews" after successfully deletion.
	 *
	 * @param id      - identifies  News
	 * @param session - keeps page which redirect after successfully operation result.
	 */
	@GetMapping(value = "/deleteNews")
	public String deleteNews(@RequestParam("id") int id, HttpSession session,
							 @SessionAttribute(SessionParams.CURRENT_PAGE) String currentPage) {


		if ( !SessionParams.ACTION_FIND_ALL_NEWS.equals(currentPage)) {
			session.setAttribute(SessionParams.CURRENT_PAGE, SessionParams.PAGE_DEFAULT);
		}

		try {
			newsService.deleteNews(id);
			session.setAttribute(SessionParams.OPERATION_RESULT_FOR_SESSION, SessionParams.RESULT_OPERATION_MSG_DELETE_SUCCESS);
		} catch (ServiceException e) {
			logger.error("Error deleting news by ID / " + e);
			return "redirect:/news/error";
		}
		return "redirect:/news/welcomePage";
	}

	/**
	 * Deletes group News from "/findAllNews" page.
	 * If checkboxes not selected, delete nothing.
	 */
	@GetMapping(value = "/deleteNewsSelected")
	public String deleteNewsSelected(HttpServletRequest request) {
		String SELECT_NEWS_GROUP_DELETE = "selected_news";

		int[] selectAllNewsInt;

		try {
			String[] selectedAllNewsString = request.getParameterValues(SELECT_NEWS_GROUP_DELETE);

			selectAllNewsInt = new int[selectedAllNewsString.length];

			for (int i = 0; i < selectAllNewsInt.length; i++) {
				selectAllNewsInt[i] = Integer.parseInt(selectedAllNewsString[i]);
			}
			newsService.deleteSelectedNews(selectAllNewsInt);
		} catch (NullPointerException e) {
			logger.error("Nothing to delete because no checkbox selected / " + e);
		} catch (NumberFormatException e) {
			logger.error("Error parsing selected news, some of id is wrong / " + e);
		} catch (ServiceException e) {
			logger.error("Error group News deleting  / " + e);
		}
		return "redirect:/news/welcomePage";
	}

	/**
	 * Outputs list all news from DB
	 * <p>
	 * Session key CURRENT_COMMAND is FIND ALL NEWS page because if we execute
	 * @param model   -saves list of all News from DB
	 * @param session -keeps current page with id in case localization changes and the same page should be displayed again.
	 */
	@RequestMapping(value = "/findAllNews")
	public String findAllNews(Model model, HttpSession session) {

		List<News> news;

		try {
			news = newsService.selectAllNews();
			session.setAttribute(SessionParams.CURRENT_PAGE, SessionParams.ACTION_FIND_ALL_NEWS);
		} catch (ServiceException e) {
			logger.error("Error selecting all news  /" + e);
			return "redirect:/news/error";
		}

		model.addAttribute("news", news);

		return "output-all-news";
	}

	/**
	 * Finds news by id
	 * Executes only from all News list "/findAllNews" page
	 * <p>
	 * Session parameter KEY_CURRENT_PAGE needs if changes Locale, the same page should be displayed.
	 * @param id      - identifies  News
	 * @param model   save News to output for user in output-news.jsp page.
	 * @param session keeps current page with id in case localization changes and the same page should be displayed again.
	 */
	@RequestMapping("/findNews")
	public String findNewsById(@RequestParam("id") int id, Model model, HttpSession session) {

		News news = new News();

		try {
			news = newsService.selectNews(id);
			session.setAttribute(SessionParams.CURRENT_PAGE, SessionParams.ACTION_FIND_NEWS + "?id=" + id);
		} catch (ServiceException e) {
			logger.error("Error finding news by id / " + e);
			return "redirect:/news/error";
		}

		model.addAttribute("news", news);

		return "output-news";
	}

	/**
	 * Changes localization
	 * <p>
	 * Session parameter KEY_CURRENT_PAGE uses to return back to page from which localization changed.
	 * <p>
	 *
	 * @param locale  - new locale to change
	 * @param session keeps current page with id in case localization changes and the same page should be displayed again.
	 */
	@RequestMapping("/localization")
	public String localization(@RequestParam(LOCALE) String locale,  HttpSession session) {

		String currentPage = (String) session.getAttribute(SessionParams.CURRENT_PAGE);

		if (currentPage == null) {
			currentPage = SessionParams.PAGE_DEFAULT;
		}

		session.setAttribute(SessionParams.LOCALE, locale);

		String page = "redirect:/news/" + currentPage;

		return page;
	}

	/**
	 * Updates existing News from DB
	 * @param id      - id of News that should be updated.
	 * @param model   - save News object, transfer to JSP page Form to update.
	 * @param session - uses to know what is current page, in case changing localization and stay at the same page.
	 */
	@RequestMapping("/updateNews")
	public String updateNews(@RequestParam("id") int id, Model model, HttpSession session) {

		News news;

		try {
			news = newsService.selectNews(id);
			session.setAttribute(SessionParams.CURRENT_PAGE, SessionParams.ACTION_UPDATE_NEWS + "?id=" + id);
			session.setAttribute(SessionParams.OPERATION_RESULT_FOR_SESSION, SessionParams.RESULT_OPERATION_MSG_UPDATE_SUCCESS);
		} catch (ServiceException e) {
			logger.error("Error finding news by id / " + e);
			return "redirect:/news/error";
		}

		model.addAttribute("news", news);

		return "news-form";
	}

	/**
	 * Defines what page redirect after operation. If operation executed from '/findAllNews' -
	 * stay at the same page, otherwise redirect to main page.
	 *
	 */
	@RequestMapping("/welcomePage")
	public String welcome(@SessionAttribute(SessionParams.CURRENT_PAGE) String currentPage) {

		if (SessionParams.ACTION_FIND_ALL_NEWS.equals(currentPage)) {
			return "redirect:/news/findAllNews";
		} else {
			return "redirect:/news/mainPage";
		}
	}


}