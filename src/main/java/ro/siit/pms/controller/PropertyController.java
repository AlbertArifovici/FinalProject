package ro.siit.pms.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import ro.siit.pms.entity.Property;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ro.siit.pms.entity.User;
import ro.siit.pms.service.PropertyService;
import ro.siit.pms.service.model.CustomUserDetails;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
public class PropertyController {
	
	private PropertyService propertyService;

	public PropertyController(PropertyService propertyService) {
		super();
		this.propertyService = propertyService;
	}
	
	// handler method to handle list properties and return mode and view
	@GetMapping("/properties")
	public String listProperties(Model model, Principal principal) {
		UUID userUUID=getUserDetailsFromAuthentication(principal).getId();
		model.addAttribute("properties", propertyService.getPropertiesByUserId(userUUID));
		return "properties";
	}
	
	@GetMapping("/properties/new")
	public String createPropertyForm(Model model) {
		
		// create property object to hold property form data
		Property property = new Property();
		model.addAttribute("property", property);
		return "create_property";
		
	}
	
	@PostMapping("/properties")
	public String saveProperty(@ModelAttribute("property") Property property, Principal principal) {
		UUID userUUID=getUserDetailsFromAuthentication(principal).getId();
		propertyService.saveProperty(property, userUUID);
		return "redirect:/properties";
	}
	
	@GetMapping("/properties/edit/{id}")
	public String editPropertyForm(@PathVariable Long id, Model model) {
		model.addAttribute("property", propertyService.getPropertyById(id));
		return "edit_property";
	}

	@PostMapping("/properties/{id}")
	public String updateProperty(@PathVariable Long id,
			@ModelAttribute("property") 	Property property,
			Model model) {
		
		// get property from database by id
		Property existingProperty = propertyService.getPropertyById(id);
		existingProperty.setPropertyId(id);
		existingProperty.setTitle(property.getTitle());
		existingProperty.setDescription(property.getDescription());
		existingProperty.setPrice(property.getPrice());
		existingProperty.setAddress(property.getAddress());
		
		// save updated property object
			propertyService.updateProperty(existingProperty);
		return "redirect:/properties";
	}
	
	// handler method to handle delete property request
	
	@GetMapping("/properties/{id}")
	public String deleteProperty(@PathVariable Long id) {
		propertyService.deletePropertyById(id);
		return "redirect:/properties";
	}

	@GetMapping("/properties/panel")
	public String list(Model model, Principal principal) {
		List<Property> properties = this.propertyService.getAllProperties();
		model.addAttribute("properties", properties);

		return "properties/list";
	}
	private CustomUserDetails getUserDetailsFromAuthentication(Principal principal) {
		return ((CustomUserDetails)((UsernamePasswordAuthenticationToken) principal).getPrincipal());
	}
	
}
