package ru.geekbrains.winter.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.winter.market.core.entities.Product;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IdentityMap {

    private Map<Long, Optional<Product>> hash = new IdentityHashMap<>();


    public Optional<Product> isInto(Long key) throws Exception {
        if (hash.containsKey(key)){
            return hash.get(key);
        } else return null;
    }

    public void add(Optional<Product> product) {
        hash.put(product.get().getId(),product);
    }


}
