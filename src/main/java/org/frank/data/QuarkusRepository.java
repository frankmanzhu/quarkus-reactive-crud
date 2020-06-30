package org.frank.data;

import org.frank.data.entity.Quarkus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuarkusRepository extends JpaRepository<Quarkus, Long> {
}
