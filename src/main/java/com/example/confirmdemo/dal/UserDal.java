package com.example.confirmdemo.dal;

import com.example.confirmdemo.idal.IUserDal;
import com.example.confirmdemo.init.Response;
import com.example.confirmdemo.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * @author 25290
 */
@Service
public class UserDal {

    private final IUserDal userDal;

    @Autowired
    public UserDal(IUserDal userDal) {
        this.userDal = userDal;
    }

    public Response saveUser(User u) {
        UUID id=UUID.randomUUID();
        System.out.println(id);
        if (u.getUsername().isEmpty() || u.getPassword().isEmpty()) {
            return new Response(400, "请不要留空用户名或密码", null);
        }
        User user=new User(id, u.getUsername(), u.getPassword(),u.getDescription());

    try{
        System.out.println(user);
        userDal.save(user);
        return new Response(204, "新增成功", null);
    }catch(Exception e){
        return new Response(500, "新增失败，原因:" + e, null);
        }

    }

    public Response deleteById(UUID id) {
        try {
            userDal.deleteById(id);
            return new Response(204, "删除成功", null);
        } catch (EmptyResultDataAccessException e) {
            return new Response(404, "删除失败，因无该条数据", null);
        } catch (Exception e) {
            return new Response(500, "删除失败，原因:" + e, null);
        }
    }

    public Response updateDescribeById(UUID id, String describe) {
        try {
            userDal.updateDescribeById(id, describe);
            return new Response(204, "更新成功", null);
        } catch (EntityNotFoundException e) {
            return new Response(404, "更新失败，因无该条数据", null);
        } catch (Exception e) {
            return new Response(500, "更新失败，原因:" + e, null);
        }
    }

    public Response updatePasswordById(UUID id, String password) {
        try {
            userDal.updatePasswordById(id, password);
            return new Response(204, "更新成功", null);
        } catch (EntityNotFoundException e) {
            return new Response(404, "更新失败，因无该条数据", null);
        } catch (Exception e) {
            return new Response(500, "更新失败，原因:" + e, null);
        }
    }

    public Response findById(UUID id) {
        Optional<User> userOptional = userDal.findById(id);
        if (userOptional.isPresent()) {
            return new Response(200, "查询成功", userOptional.get());
        } else {
            return new Response(404, "查询失败，不包含该数据", null);
        }
    }

    public Response findByUsername(String username) {
        User user = userDal.findByUsername(username);
        if (user != null) {
            return new Response(200, "查询成功", user);
        } else {
            return new Response(404, "查询失败，不包含该数据", null);
        }
    }

    public Response findAll() {
        return new Response(200, "查询成功", userDal.findAll());
    }
}
