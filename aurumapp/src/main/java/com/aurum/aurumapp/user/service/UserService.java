package com.aurum.aurumapp.user.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aurum.aurumapp.exceptions.BadCredentialsException;
import com.aurum.aurumapp.exceptions.ResourceNotFoundException;
import com.aurum.aurumapp.user.model.User;
import com.aurum.aurumapp.user.repository.UserRepository;
import com.aurum.aurumapp.wallet.model.Wallet;
import com.aurum.aurumapp.wallet.repository.WalletRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    @Transactional
    public User createUser(User user) {
        var wallet = new Wallet(0, user);
        user.setWallet(walletRepository.save(wallet));
        return userRepository.save(user);
    }

    @Transactional
    public User getUserById(long id) {
        var user = userRepository.findById(id);
        if (user.isPresent())
            return user.get();
        throw new ResourceNotFoundException("User not found.");
    }

    @Transactional
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User authenticate(User user) {
        Optional<User> userReturned = userRepository.findByEmail(user.getEmail());
        if(!userReturned.isPresent() || !userReturned.get().getPassword().equals(user.getPassword())){
            throw new BadCredentialsException("User not found.");
        }else return userReturned.get();
    }
}
