package com.spring.app.demovaadinapplication;


import com.spring.app.demovaadinapplication.vaadin.DesignerViewDesign;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DemoVaadinApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoVaadinApplication.class, args);
	}

	/** example vaadin **/
	@Service
	static class MyService{

		public String getHallo(){
			return "Hallo !";
		}
	}

	@Theme("valo")
	@SpringUI
	public static class MyUI extends UI {

		@Autowired
		DesignerView designerView;

		@Override
		protected void init(VaadinRequest request) {
			setContent(designerView);
		}
	}

	@SpringComponent
	@UIScope
	public static class DesignerView extends DesignerViewDesign {

		@Autowired
		MyService myService;

		@PostConstruct
		public void init(){
			messageFromService.setValue(myService.getHallo());
		}
	}
}
