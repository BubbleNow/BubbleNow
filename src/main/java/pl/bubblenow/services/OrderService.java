package pl.bubblenow.services;

import org.springframework.stereotype.Service;
import pl.bubblenow.models.*;
import pl.bubblenow.repositories.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Service
public class OrderService {
    BubbleTeaRepository bubbleTeaRepository;
    OrderRepository orderRepository;

    public OrderService(
            BubbleTeaRepository bubbleTeaRepository,
            OrderRepository orderRepository
    ) {
        this.bubbleTeaRepository = bubbleTeaRepository;
        this.orderRepository = orderRepository;
    }

    public BigDecimal countPrice(Addition addition, Milk milk, Size size) {
        BigDecimal priceAddition = addition == null ?
                BigDecimal.valueOf(0) :
                addition.getPrice();

        return priceAddition
                .add(milk.getPrice())
                .add(size.getPrice());
    }

    public int create(Addition addition, Syrup syrup, Milk milk, Size size, Kind kind) {
        BubbleTea bubbleTea = new BubbleTea();

        bubbleTea.setAddition(addition);
        bubbleTea.setMilk(milk);
        bubbleTea.setSize(size);
        bubbleTea.setSyrup(syrup);
        bubbleTea.setKind(kind);
        bubbleTeaRepository.save(bubbleTea);

        Order order = new Order();
        order.setDate(new Date());
        order.setNumber(getNextNumber());
        order.setPrice(countPrice(addition, milk, size));

        order.setBubbleTea(bubbleTea);
        orderRepository.save(order);

        return order.getNumber();
    }

    public int getNextNumber() {
        Order latestOrder = orderRepository.findTopByOrderByIdDesc();

        return latestOrder == null ? 1 : latestOrder.getNumber() + 1;
    }

    public void updateToNextStatus(int id) {
        Order order = orderRepository.findById(id);

        if (order == null) {
            return;
        }

        switch (order.getStatus()) {
            case "Nowe" -> order.setStatus("W przygotowaniu");
            case "W przygotowaniu" -> order.setStatus("Gotowe do odbioru");
            case "Gotowe do odbioru" -> order.setStatus("Odebrane");
        }
        orderRepository.save(order);
    }

}
