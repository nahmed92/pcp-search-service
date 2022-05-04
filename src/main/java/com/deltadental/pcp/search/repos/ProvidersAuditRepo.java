package com.deltadental.pcp.search.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deltadental.pcp.search.entities.ProvidersAuditEntity;

@Repository
@Transactional
public interface ProvidersAuditRepo extends JpaRepository<ProvidersAuditEntity, Integer> {

}
