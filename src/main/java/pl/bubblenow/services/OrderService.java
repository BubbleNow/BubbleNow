package pl.bubblenow.services;

import org.springframework.stereotype.Service;
import pl.bubblenow.models.*;
import pl.bubblenow.repositories.*;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class OrderService {

    AdditionRepository additionRepository;
    BaseRepository baseRepository;
    SizeRepository sizeRepository;
    BubbleTeaRepository bubbleTeaRepository;
    SyrupRepository syrupRepository;
    KindRepository kindRepository;
    OrderRepository orderRepository;

    public OrderService(AdditionRepository additionRepository,
                        BaseRepository baseRepository,
                        SizeRepository sizeRepository,
                        BubbleTeaRepository bubbleTeaRepository,
                        SyrupRepository syrupRepository,
                        KindRepository kindRepository,
                        OrderRepository orderRepository) {
        this.additionRepository = additionRepository;
        this.baseRepository = baseRepository;
        this.sizeRepository = sizeRepository;
        this.bubbleTeaRepository = bubbleTeaRepository;
        this.syrupRepository = syrupRepository;
        this.kindRepository = kindRepository;
        this.orderRepository = orderRepository;
    }

    public BigDecimal countPrice(Integer additionId, int baseId, int sizeId) {

        BigDecimal priceAddition;

        if (additionId == null) {
            priceAddition = BigDecimal.valueOf(0);
        } else {
            priceAddition = additionRepository.findById(additionId.intValue()).getPrice();
        }

        BigDecimal priceBase = baseRepository.findById(baseId).getPrice();
        BigDecimal priceSize = sizeRepository.findById(sizeId).getPrice();

        return priceAddition.add(priceBase).add(priceSize);
    }

    public String createNew(int additionId, int syrupId, int baseId, int sizeId, int kindId){
        Syrup syrup = syrupRepository.findById(syrupId);
        Addition addition = additionRepository.findById(additionId);
        Base base = baseRepository.findById(baseId);
        Size size = sizeRepository.findById(sizeId);
        Kind kind = kindRepository.findById(kindId);

        BubbleTea bubbleTea = new BubbleTea();

        bubbleTea.setAddition(addition);
        bubbleTea.setBase(base);
        bubbleTea.setSize(size);
        bubbleTea.setSyrup(syrup);
        bubbleTea.setKind(kind);
        bubbleTeaRepository.save(bubbleTea);

        Order order = new Order();

        order.setDate(new Date());
        order.setNumber(69);
        order.setPrice(countPrice(additionId, baseId, sizeId));
        order.setBubbleTea(bubbleTea);
        orderRepository.save(order);

        return "Twoje zamówienie otrzymało numer:" + order.getNumber();
    }



}
