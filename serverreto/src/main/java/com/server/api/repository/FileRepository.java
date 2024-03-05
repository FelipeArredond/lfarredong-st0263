package com.server.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Integer> {
    public Optional<FileEntity> findByFileName(String fileName);
    @Transactional
    @Modifying
    @Query("DELETE FROM FileEntity i WHERE i.peerName = :peerName")
    void deleteAllByPeerName(String peerName);
}
