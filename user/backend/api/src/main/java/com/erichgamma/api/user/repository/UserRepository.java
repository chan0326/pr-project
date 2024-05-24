package com.erichgamma.api.user.repository;

import com.erichgamma.api.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.StyledEditorKit;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findUsersByName(String name);

    Optional<User> findUserByUsername(String username);
    List<User> findByName(String name);
    List<User> findByJob(String job);

@Query("select count(id) as count from users where  username =:username")
    Integer existsByUsername(@Param("username") String username);

//@Query("select  count(u) > 0 from users u where u.username = :username")
//Boolean   existByUsername(@Param("username") String username);


    @Modifying
    @Query("update users set token =:token where id =:id" )
    void modifiyTokenByID(@Param("id") Long id, @Param("token") String token);



}
