package com.tm.guestbook.api.repository;

import com.tm.guestbook.api.entity.GuestBookEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestBookEntryRepository extends JpaRepository<GuestBookEntryEntity, Long> {
    @Query("SELECT u FROM GuestBookEntryEntity u WHERE u.userEntity.id = ?1")
    List<GuestBookEntryEntity> getAllGuestBookEntriesForUser(Long id);
}
