package com.example.confirmdemo.idal;


import com.example.confirmdemo.model.User;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author 25290
 */
@Repository
@Table(name = "confirm_demo")
public interface IUserDal extends JpaRepository<User, String> {

    /**
     * 新增用户
     *
     * @param user 用户实体
     * @return 返回添加的用户
     */
    <S extends User> S save(S user);

    /**
     * 删除用户
     *
     * @param id id
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query("DELETE FROM User u WHERE u.id = :id")
    void deleteById(@Param("id") UUID id);

    /**
     * 修改密码
     *
     * @param id       id
     * @param password 密码
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.id = :id")
    void updatePasswordById(@Param("id") UUID id, @Param("password") String password);

    /**
     * 修改个人信息
     *
     * @param id       id
     * @param description 描述
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query("UPDATE User u SET u.description = :description WHERE u.id = :id")
    void updateDescribeById(@Param("id") UUID id, @Param("description") String description);


    /**
     * 根据id查找
     *
     * @param id id
     * @return 返回用户列表
     */
    Optional<User> findById(UUID id);

    /**
     * 根据姓名查找
     *
     * @param username 用户名
     * @return 返回用户
     */
    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findByUsername(@Param("username") String username);

    /**
     * 查找所有
     *
     * @return 用户列表
     */
    @Override
    List<User> findAll();
}
