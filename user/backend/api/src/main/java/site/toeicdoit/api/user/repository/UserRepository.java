package site.toeicdoit.api.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import site.toeicdoit.api.user.model.UserModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {
    List<UserModel> findUsersByName(String name);

    Optional<UserModel> findUserByUsername(String username);
    List<UserModel> findByName(String name);
    List<UserModel> findByJob(String job);

@Query("select count(id) as count from users where  username =:username")
    Integer existsByUsername(@Param("username") String username);

//@Query("select  count(u) > 0 from users u where u.username = :username")
//Boolean   existByUsername(@Param("username") String username);


    @Modifying
    @Query("update users set token =:token where id =:id" )
    void modifiyTokenByID(@Param("id") Long id, @Param("token") String token);



}
