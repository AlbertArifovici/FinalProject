package ro.siit.pms.repository;

import java.util.List;
import ro.siit.pms.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface PropertyRepository extends JpaRepository<Property, Long>{

    List<Property> findAllByUserId(UUID id);
}
