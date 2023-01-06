package pl.bubblenow.services;

import org.springframework.stereotype.Service;
import pl.bubblenow.repositories.*;

import java.math.BigDecimal;

@Service
public class OrderService {

    AdditionRepository additionRepository;
    BaseRepository baseRepository;
    SizeRepository sizeRepository;

    public OrderService(AdditionRepository additionRepository,
                        BaseRepository baseRepository,
                        SizeRepository sizeRepository) {
        this.additionRepository = additionRepository;
        this.baseRepository = baseRepository;
        this.sizeRepository = sizeRepository;
    }

    public double countPrice(Integer addition_id, int base_id, int size_id) {

        BigDecimal price_addition;

        if (addition_id == null) {
            price_addition = BigDecimal.valueOf(0);
        } else {
            price_addition = additionRepository.findById(addition_id.intValue()).getPrice();
        }

        BigDecimal price_base = baseRepository.findById(base_id).getPrice();
        BigDecimal price_size = sizeRepository.findById(size_id).getPrice();

        return price_addition.intValue() + price_base.intValue() + price_size.intValue();
    }
}
