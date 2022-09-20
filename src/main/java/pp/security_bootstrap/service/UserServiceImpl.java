package pp.security_bootstrap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pp.security_bootstrap.exception.NotFoundException;
import pp.security_bootstrap.model.User;
import pp.security_bootstrap.repo.UserRepo;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, RoleService roleService) {
        this.userRepo = userRepo;
        this.roleService = roleService;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepo.findAll();
    }


    @Override
    public User findUserById(Long id) {
        return userRepo.findById(id).orElseThrow(() ->
                new NotFoundException("User not found"));
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return user;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userRepo.save(user);
    }

    @Override
    @Transactional
    public void updateUser(Long id, User updatedUser) {
        User user = findUserById(id);
        user.setUsername(updatedUser.getUsername());
        user.setPassword(updatedUser.getPassword());

        user.getRoles().clear();
        updatedUser.getRoles().forEach(role ->
                user.getRoles().add(roleService.findRoleByName(role.getName())));
        userRepo.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepo.delete(findUserById());
    }

}
