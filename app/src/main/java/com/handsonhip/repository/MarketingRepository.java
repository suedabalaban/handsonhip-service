package com.handsonhip.repository;

import com.handsonhip.model.Marketing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MarketingRepository extends JpaRepository<Marketing, Long> {
    List<Marketing> findByProductProductID(Long productID);
}