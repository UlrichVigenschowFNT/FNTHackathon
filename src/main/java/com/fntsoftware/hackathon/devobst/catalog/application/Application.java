package com.fntsoftware.hackathon.devobst.catalog.application;

import javax.inject.Named;
import javax.ws.rs.ApplicationPath;

@ApplicationPath(Application.APPLICATION_PATH)
@Named
public class Application extends javax.ws.rs.core.Application {
	
	public static final String APPLICATION_PATH = "rs";
	
}
