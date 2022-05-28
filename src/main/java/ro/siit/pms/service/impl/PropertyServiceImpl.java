package ro.siit.pms.service.impl;

import java.util.List;
import java.util.UUID;

import ro.siit.pms.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import ro.siit.pms.entity.Property;
import ro.siit.pms.repository.UserRepository;
import ro.siit.pms.service.PropertyService;

@Service
public class PropertyServiceImpl implements PropertyService {

	private PropertyRepository propertyRepository;
	private UserRepository userRepository;
	
	public PropertyServiceImpl(PropertyRepository propertyRepository, UserRepository userRepository) {
		super();
		this.userRepository=userRepository;
		this.propertyRepository = propertyRepository;
	}

	@Override
	public List<Property> getAllProperties() {
		return propertyRepository.findAll();
	}

	@Override
	public List<Property> getPropertiesByUserId(UUID id) {
		return propertyRepository.findAllByUserId(id);
	}

	@Override
	public Property saveProperty(Property property, UUID userId) {
		property.setUser(userRepository.getOne(userId));
		return propertyRepository.save(property);
	}

	@Override
	public Property getPropertyById(Long id) {
		return propertyRepository.findById(id).get();
	}

	@Override
	public Property updateProperty(Property property) {
		return propertyRepository.save(property);
	}

	@Override
	public void deletePropertyById(Long id) {
		propertyRepository.deleteById(id);
	}

}
