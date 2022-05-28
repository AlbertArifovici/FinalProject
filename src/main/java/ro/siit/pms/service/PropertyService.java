package ro.siit.pms.service;

import java.util.List;
import java.util.UUID;

import ro.siit.pms.entity.Property;

public interface PropertyService {
	List<Property> getAllProperties();

	List<Property> getPropertiesByUserId(UUID id);

	Property saveProperty(Property property, UUID userId);
	
	Property getPropertyById(Long id);
	
	Property updateProperty(Property property);
	
	void deletePropertyById(Long id);
}
