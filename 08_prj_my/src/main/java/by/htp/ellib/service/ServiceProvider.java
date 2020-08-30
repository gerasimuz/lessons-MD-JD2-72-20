package by.htp.ellib.service;

import by.htp.ellib.service.impl.ClientServiceImpl;
import by.htp.ellib.service.impl.LibraryServiceImpl;

public class ServiceProvider {
	
	private static final ServiceProvider instance = new ServiceProvider();
	
	private ServiceProvider() {}
	
	private ClientService clientService = new ClientServiceImpl();
	private LibraryService libService = new LibraryServiceImpl();
	
	
	public ClientService getClientService() {
		return clientService;
		
	}
	
	public LibraryService getLiraryService() {
		return libService;
	}
	
	public static ServiceProvider getInstance() {
		return instance;
		
	}
}
