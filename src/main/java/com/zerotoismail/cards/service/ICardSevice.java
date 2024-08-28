package com.zerotoismail.cards.service;

import com.zerotoismail.cards.dto.CardsDto;
import org.springframework.stereotype.Repository;

public interface ICardSevice {

        void createCard(String mobileNumber);

        CardsDto fetchCard(String mobileNumber);

        boolean updateCard(CardsDto cardsDto);

        boolean deleteCard(String mobileNumber);
}