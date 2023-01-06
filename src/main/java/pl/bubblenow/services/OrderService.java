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

    public BigDecimal countPrice(Addition addition, Base base, Size size) {

        BigDecimal priceAddition;

        if (addition == null) {
            priceAddition = BigDecimal.valueOf(0);
        } else {
            priceAddition = addition.getPrice();
        }

        BigDecimal priceBase = base.getPrice();
        BigDecimal priceSize = size.getPrice();

        return priceAddition.add(priceBase).add(priceSize);
    }

    public Order create(Addition addition, Syrup syrup, Base base, Size size, Kind kind) {
        BubbleTea bubbleTea = new BubbleTea();

        bubbleTea.setAddition(addition);
        bubbleTea.setBase(base);
        bubbleTea.setSize(size);
        bubbleTea.setSyrup(syrup);
        bubbleTea.setKind(kind);
        bubbleTeaRepository.save(bubbleTea);

        Order order = new Order();
        order.setDate(new Date());
        order.setNumber(getNextNumber());
        order.setPrice(countPrice(addition, base, size));

        order.setBubbleTea(bubbleTea);
        orderRepository.save(order);

        return order;
    }

    public int getNextNumber() {
        int latestNumber = orderRepository.findTopByOrderByIdDesc().getNumber();

        return latestNumber >= 100 ? 1 : latestNumber + 1;
    }

}
