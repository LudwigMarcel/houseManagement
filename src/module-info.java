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
	requires transitive javafx.controls;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	requires com.fasterxml.jackson.databind;
	requires junit;
	requires com.fasterxml.jackson.datatype.jsr310;
	
	opens gui;
	opens util to com.fasterxml.jackson.databind;
	opens model.entities to com.fasterxml.jackson.databind;

}