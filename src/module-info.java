module houseManagement {
	exports application;
	exports util;
	exports gui;
	exports gui.util;
	exports model.entities.enums;
	exports model.entities;

	//requires gson2;
	requires java.desktop;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires com.fasterxml.jackson.databind;
	requires junit;
	
	opens gui;
	opens util to com.fasterxml.jackson.databind;
	opens model.entities to com.fasterxml.jackson.databind;

}