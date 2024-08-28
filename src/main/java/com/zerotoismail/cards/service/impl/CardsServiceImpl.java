package com.zerotoismail.cards.service.impl;


import com.zerotoismail.cards.constants.CardsConstants;
import com.zerotoismail.cards.dto.CardsDto;
import com.zerotoismail.cards.entity.CardsEntity;
import com.zerotoismail.cards.exception.CardAlreadyExistsException;
import com.zerotoismail.cards.exception.ResourcesNotFoundException;
import com.zerotoismail.cards.mapper.CardsMapper;
import com.zerotoismail.cards.repository.CardsRepository;
import com.zerotoismail.cards.service.ICardSevice;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardSevice {

    private CardsRepository cardsRepository;


    public void createCard(String mobileNumber) {
        Optional<CardsEntity> optionalCards= cardsRepository.findByMobileNumber(mobileNumber);
        if(optionalCards.isPresent()){
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber "+mobileNumber);
        }
        cardsRepository.save(createNewCard(mobileNumber));
    }

    private CardsEntity createNewCard(String mobileNumber) {
        CardsEntity newCard = new CardsEntity();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    public CardsDto fetchCard(String mobileNumber) {
        CardsEntity cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourcesNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        return CardsMapper.mapToCardsDto(cards, new CardsDto());
    }

    public boolean updateCard(CardsDto cardsDto) {
        CardsEntity cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourcesNotFoundException("Card", "CardNumber", cardsDto.getCardNumber()));
        CardsMapper.mapToCards(cardsDto, cards);
        cardsRepository.save(cards);
        return  true;
    }

    public boolean deleteCard(String mobileNumber) {
        CardsEntity cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourcesNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        cardsRepository.deleteById(cards.getCardId());
        return true;
    }


}