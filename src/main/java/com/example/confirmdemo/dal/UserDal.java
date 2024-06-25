package com.example.confirmdemo.dal;

import com.example.confirmdemo.idal.IUserDal;
import com.example.confirmdemo.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 25290
 */
@Service
public class UserDal {

    private final IUserDal iUserDal;

    @Autowired
    public UserDal(IUserDal userDal) {
        this.iUserDal = userDal;
    }

    public void saveUser(User u) {
        if (u.getUsername().isEmpty() || u.getPassword().isEmpty()) {
            throw new NullPointerException();
        }
        User user = new User(null, u.getUsername(), u.getPassword(), u.getDescription());
        iUserDal.save(user);
    }

    public boolean deleteById(String id) {
        if (iUserDal.existsById(id)) {
            iUserDal.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateDescribeById(String id, String description) {
        try {
            iUserDal.updateDescribeById(id, description);
            return true;
        } catch (EntityNotFoundException e) {
            return false;
        }
    }

    public boolean updatePasswordById(String id, String password) {
        try {
            iUserDal.updatePasswordById(id, password);
            return true;
        } catch (EntityNotFoundException e) {
            return false;
        }
    }

    public User findById(String id) {
        return iUserDal.findById(id).orElse(null);
    }

    public User findByUsername(String username) {
        return iUserDal.findByUsername(username);
    }

    public List<User> findAll() {
        return iUserDal.findAll();
    }
}
