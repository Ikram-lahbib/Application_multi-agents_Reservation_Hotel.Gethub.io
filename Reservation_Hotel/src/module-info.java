module Reservation_Hotel {
	requires transitive javafx.graphics;
	requires transitive javafx.controls;
	requires transitive javafx.fxml;
	requires transitive jade;
	requires javafx.base;
	requires java.desktop;
	requires jdk.hotspot.agent;
	exports agents;
	exports Container;
	exports Gui;
}