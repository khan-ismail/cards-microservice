package com.zerotoismail.cards.repository;

import com.zerotoismail.cards.dto.CardsDto;
import com.zerotoismail.cards.entity.CardsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardsRepository extends JpaRepository<CardsEntity, Long> {

    Optional<CardsEntity> findByMobileNumber(String mobileNumber);

    Optional<CardsEntity> findByCardNumber(String cardNumber);

}
