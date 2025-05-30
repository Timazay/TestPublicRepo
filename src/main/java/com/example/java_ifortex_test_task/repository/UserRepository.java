package com.example.java_ifortex_test_task.repository;

import com.example.java_ifortex_test_task.entity.DeviceType;
import com.example.java_ifortex_test_task.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT DISTINCT u.* " +
            "FROM users as u " +
            "JOIN sessions as s ON u.id = s.user_id " +
            "WHERE s.device_type = :deviceType ", nativeQuery = true)
    List<User> getUsersWithAtLeastOneMobileSession(Integer deviceType);

    @Query(value = "SELECT u.* FROM users as u " +
            "JOIN (" +
            "SELECT s.user_id, COUNT(s.id) AS session_count FROM sessions s " +
            "GROUP BY s.user_id " +
            "ORDER BY session_count DESC " +
            "LIMIT 1" +
            ") as max_session ON u.id=max_session.user_id", nativeQuery = true)
    User getUserWithMostSessions();
}
